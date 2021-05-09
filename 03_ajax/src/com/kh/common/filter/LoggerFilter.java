package com.kh.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


public class LoggerFilter implements Filter {


	public void destroy() {
		System.out.println("LoggerFilter의 destroy 호출!");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//1. servlet 호출전
		System.out.println("\n==================================");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		//getRequestURI는 ServletRequest에는 없지만 HttpServletRequest에는 있기 때문에...
		String uri = httpReq.getRequestURI();
		System.out.println(uri);
		System.out.println("\n----------------------------------");
		
		//다음 filterChain객체를 호출
		//다음 filter객체가 존재하지 않으면, servlet 호출
		chain.doFilter(request, response);
		
		//2. servlet/jsp 처리 이후
		System.out.println("\n__________________________________");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoggerFilter의 init 호출!");
	}

}
