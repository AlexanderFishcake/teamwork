package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * web.xml에 등록된 순서대로 처리됨.
 * web.xml이 @WebFilter보다 우선처리됨.
 * @WebFilter 순서를 정할 수 없기 때문에 순서가 중요한 filter는 web.xml에 써야한다. 
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//인코딩처리
		request.setCharacterEncoding("utf-8");
		System.out.println("[utf-8] encoding 처리함.");

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}


}
