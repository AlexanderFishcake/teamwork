<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String life = "is very short!";
	String movie = "노인을 위한 나라는 없다.";

	pageContext.setAttribute("life", life);
	pageContext.setAttribute("movie", movie);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>El Basics</title>
</head>
<body>
	<h1>El Basics</h1>
	<%-- 
		Scope쪽을 생략해도 작동하는데
		scope 생략시, pageScope - requestScope - sessionScope - applicationScope 순으로 검색함.
		즉. pageScope에 book이 있다면 session의 book은 무시된다.
	 --%>
	<p>${pageScope.life }</p>
	
	<p>${requestScope.coffee }</p>
	<p>${requestScope.serverTime }</p>
	<p>${requestScope.honggd }</p>
	<p>${requestScope.honggd.id }</p>
	<p>${requestScope.honggd.name }</p>
	<p>${requestScope.honggd.gender }</p>
	<p>${requestScope.honggd.age }</p>
	<p>${requestScope.honggd.married }</p>
	
	<p>${sessionScope.book }</p>
	<p>${applicationScope.movie }</p>
	<p>${movie }</p>
	<%-- 
		EL은 NullPointerException을 유발하지 않는다.
		null인 경우에는 ""출력 
		대신 vo의 잘못된 필드 접근은 오류난다. ex)honggd.nickname
	--%>
	<p>[${cow.run}]</p>
	
	<h2>list</h2>
	<p>${list }</p>
	<p>${list[0] }</p>
	<p>${list[1] }</p>
	<p>${list[2] }</p>
	<p>${list[3] }</p>
	
	<h1>map</h1>
	<p>${map }</p>
	<p>${map.language}</p>
	<p>${map['Dr.zang']}</p>
	<p>${map['Dr.zang'].name}</p>
	<p>${map['Dr.zang']["name"] }</p>
	
	<h1>Param</h1>
	<p>${param.pname }</p>
	<p>${param.pcount }</p>
	<p>${paramValues.option[0] }</p>
	<p>${paramValues.option[1] }</p>
	
	<h1>cookie</h1>
	<p>${cookie.JSESSIONID }</p>
	<p>${cookie.JSESSIONID.value }</p>
	
	<h1>header</h1>
	<p>${header.accept }</p>
	<p>${header['user-agent']}</p>
	
	<h1>pageContext</h1>
	<!-- 
		getPage()
		getRequest()
			getMethod(): GET | POST
			getContextPath(): /action
		getResponse()
		getSession()
		getServletContext()
		getErrorData()
	 -->
	<p>${pageContext.request.method }</p>
	<p>${pageContext.request.contextPath }</p>
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>