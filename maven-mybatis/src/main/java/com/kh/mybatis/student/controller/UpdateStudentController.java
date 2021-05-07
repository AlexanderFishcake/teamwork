package com.kh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;

public class UpdateStudentController extends AbstractController {
	
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 사용자입력값
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		//2. 업무로직
		Map<String, Object> student = new HashMap<>();
		
		student.put("no", no);
		student.put("name", name);
		student.put("tel", tel);
		
		int result = 0;
		try {
			result = studentService.updateStudentMap(student);
			request.getSession().setAttribute("msg", "학생 정보 수정 성공!");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		//3. jsp 위임
		return "redirect:/student/selectOne.do?no="+no;
	}
	
	
	
	
}
