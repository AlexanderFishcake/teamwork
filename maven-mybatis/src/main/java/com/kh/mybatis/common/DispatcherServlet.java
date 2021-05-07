package com.kh.mybatis.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.exception.ControllerNotFoundException;
import com.kh.mybatis.common.exception.MethodNotAllowedException;


/**
 * Servlet implementation class DispatcherServlet
 * 
 * Servlet의 LifeCycle
 * 객체생성 - init - service - doGet/doPost - destroy
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, AbstractController> urlControllerMap = new HashMap<>();

	/**
	 * Servlet생성시 최초 1회 실행
	 * 사용자 요청주소 - controller객체를 binding할 수 있는 정보를 가진 map객체 생성
	 * 
	 * 	/student/selectOneStudent.do -> SelectOneStudentController
	 * 	/student/insertStudent.do -> InsertStudentController
	 */
	public void init(ServletConfig config) throws ServletException {
		Properties prop = new Properties();
		String fileName = DispatcherServlet.class
											.getResource("/url-command.properties")
											.getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//url-pattern : String - Controller객체:AbstractController
		Set<String> keys = prop.stringPropertyNames();
		for(String key : keys) {
			String value = prop.getProperty(key);	//클래스명
			//Controller 객체화 : new SomeClass();
			try {
				//java reflection api
				//클래스객체 : 해당클래스의 구성정보(필드,메소드,접근제한자 모두 존재)
				Class clazz = Class.forName(value);
				AbstractController controller = (AbstractController) clazz.newInstance();
				//urlControllerMap에 추가
				urlControllerMap.put(key, controller);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("urlControllerMAp@init = "+urlControllerMap);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청주소 가져오기
		String uri = request.getRequestURI();	//	/maven-mybatis/student/insertStudent.do
		int beginIndex = request.getContextPath().length();
		String url = uri.substring(beginIndex);	//	/student/insertStudent.do
		
		//2. controller객체 호출
		AbstractController controller = urlControllerMap.get(url);
		if(controller == null)
			throw new ControllerNotFoundException(url + "에 해당하는 controller가 존재하지 않습니다.");
		
		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : 
			viewName = controller.doGet(request, response); 
			break;
		case "POST" : 
			viewName = controller.doPost(request, response); 
			break;
		default: throw new MethodNotAllowedException(method); 
		}
		
		//3. jsp forwarding 또는 redirect처리
		if(viewName != null) {
			if(viewName.startsWith("redirect:")) {
				//redirect
				String location = request.getContextPath() + viewName.replace("redirect:", "");
				response.sendRedirect(location);
			}
			else {
				//jsp forwarding
				final String prefix = "/WEB-INF/views/";
				final String suffix = ".jsp";
				request.getRequestDispatcher(prefix+viewName+suffix)
						.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
