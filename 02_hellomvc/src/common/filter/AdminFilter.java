package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({"/admin/memberList","/admin/memberRoleUpdate","/admin/memberFinder"})
public class AdminFilter implements Filter {
	MemberService memberService = new MemberService();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//관리자의 로그인만 허용하기
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");

		if(loginMember == null) {
			session.setAttribute("msg", "로그인 후 사용할 수 있습니다.");
			httpRes.sendRedirect(httpReq.getContextPath());
			return;
		}
	
		else if(memberService.ADMIN_ROLE.equals(loginMember.getMemberRole())) {
			// pass the request along the filter chain	
			chain.doFilter(request, response);			
		}
		else {
			session.setAttribute("msg", "관리자만 사용할 수 있습니다.");
			httpRes.sendRedirect(httpReq.getContextPath());
			return;
		}
	}

}
