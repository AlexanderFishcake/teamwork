package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	/**
	 * 회원가입페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
				.forward(request,response);
	}

	/**
	 * 회원가입 처리 - db에 저장
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//2. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		String password = MvcUtils.getSha512(request.getParameter("password"));
		String memberName = request.getParameter("memberName");
		String birthdayStr = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String[] hobbyStr = request.getParameterValues("hobby");		
		
		System.out.println("memberId@servlet = "+memberId);
		System.out.println("password@servlet = "+password);
		System.out.println("memberName@servlet = "+memberName);
		System.out.println("birthday@servlet = "+birthdayStr);
		System.out.println("email@servlet = "+email);
		System.out.println("phone@servlet = "+phone);
		System.out.println("address@servlet = "+address);
		System.out.println("gender@servlet = "+gender);
		System.out.print("hobby@servlet = ");
		for(int i=0;i<hobbyStr.length;i++) {
			System.out.print(hobbyStr[i]+", ");
		}

		//3. 업무로직 : member객체를 만들어서 insertMember 실행.			
		//birthday date로 처리. hobby 문자열로 처리. enroll_date yy/mm/dd로 처리.
		Date birthday = Date.valueOf(birthdayStr);
		Date enrollDate = Date.valueOf(LocalDate.now());
		String hobby="";
		for(int i=0;i<hobbyStr.length;i++) {
			//마지막 배열이면
			if(i==hobbyStr.length-1)
				hobby+=hobbyStr[i];
			else
				hobby+=hobbyStr[i]+",";
		}
		
		Member member = new Member(memberId, password, memberName, null, gender, birthday, email, phone, address, hobby, enrollDate);
		int result = memberService.insertMember(member);
		System.out.println("처리결과 = "+result);
		
		//가입 성공/실패여부 판단
		//1. 가입 성공: result==1
		//2. 가입 실패 : result==0 || result==null
		
		HttpSession	session = request.getSession();
		
		if(result==1) {
			//가입 성공
			session.setAttribute("msg", "회원가입 성공했습니다.");
			//리다이렉트 : url변경
		}
		else if(result==0){
			//가입 실패
			session.setAttribute("msg", "회원가입 실패했습니다.");
		}
		response.sendRedirect(request.getContextPath());
				
	}

}
