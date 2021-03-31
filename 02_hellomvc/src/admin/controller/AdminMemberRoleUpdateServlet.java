package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		String memberId = (String) request.getParameter("memberId");
		String memberRole = (String) request.getParameter("memberRole");
		System.out.println(memberId+ " : "+memberRole);
		//2. 업무 로직
		int result = memberService.updateMemberRole(memberId,memberRole);
		
		//3. 리다이렉트 및 사용자 피드백
		String msg = result>0 ? "권한수정 성공했습니다." : "권한수정 실패했습니다.";

		request.getSession().setAttribute("msg", msg);
		// /mvc/admin/memberList
		response.sendRedirect(request.getContextPath() + "/admin/memberList");
	}

}
