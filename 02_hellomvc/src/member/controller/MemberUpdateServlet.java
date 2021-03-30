package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession	session = request.getSession();
		
		//1. encoding처리
		request.setCharacterEncoding("UTF-8");
		//2. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
//		String password = request.getParameter("password");
		//password를 기존 loginMember에서 가져옴
		Member loginMember = (Member)session.getAttribute("loginMember");
		String password = loginMember.getPassword();
		
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
		
		Member member = new Member(memberId, password, memberName, null, gender, birthday, email, phone, address, hobby, null);
		int result = memberService.updateMember(member);
		System.out.println("처리결과 = "+result);
		
		//성공/실패여부 판단
		//1. 성공: result==1
		//2. 실패 : result==0 || result==null
		
		
		
		if(result==1) {
			//성공
			session.setAttribute("msg", "회원정보 수정 성공했습니다.");
			//세션의 정보도 갱신. 이게 있어야 수정된 정보가 반영됨.
			session.setAttribute("loginMember", memberService.selectOne(memberId));
		}
		else if(result==0){
			//실패
			session.setAttribute("msg", "회원정보 수정 실패했습니다.");
		}
		response.sendRedirect(request.getContextPath() + "/member/memberView");
				
	}


}
