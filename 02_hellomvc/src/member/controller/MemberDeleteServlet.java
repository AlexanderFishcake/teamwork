package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		System.out.println("memberId : "+memberId);
		
		int result = memberService.deleteMember(memberId);
		System.out.println("처리결과 = "+result);
		
		HttpSession	session = request.getSession();
		
		if(result==1) {
			//성공
//			session.setAttribute("msg", "회원탈퇴 성공했습니다.");
			response.sendRedirect(request.getContextPath()+"/member/Logout");
		}
		else if(result==0){
			//실패
			session.setAttribute("msg", "회원탈퇴 실패했습니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}
