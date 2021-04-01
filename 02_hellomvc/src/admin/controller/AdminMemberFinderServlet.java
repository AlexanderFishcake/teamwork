package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 : 현재페이지 cPage
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 처리 코드 없음. 기본값 1 유지.
		}
		int end = cPage * numPerPage;
		int start = (cPage-1)*numPerPage + 1;
		//1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet = "+param);
		//2. 업무로직
		List<Member> list = memberService.searchMember(param, start, end);
		System.out.println("list@servlet = "+list);
		
		//3. pagebar영역 작업
		List<Member> listOrigin = memberService.searchMember(param);
		int totalContents = listOrigin.size(); 
		System.out.println("list 카운트값 : "+totalContents);		
		///mvc/admin/memberFinder?searchType=memberId&searchKeyword=a
		String url = request.getRequestURI()+"?searchType="+searchType+"&searchKeyword="+searchKeyword;
		String pageBar = MvcUtils.getPageBar(
					cPage,
					numPerPage,
					totalContents,
					url
				);
		
		//4. jsp에 html응답메세지 작성 위임
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
				.forward(request, response);
	}

}
