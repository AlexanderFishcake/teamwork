<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String main = request.getParameter("main_menu");
	String side = request.getParameter("side_menu");
	String drink = request.getParameter("drink_menu");

 	int sum = (int)request.getAttribute("sum"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	span#main_menu{
		color : blue;
		font-size: x-large;
	}
	span#side_menu{
		color : purple;
		font-size: large;
	}
	span#drink_menu{
		color : green;
	}
	span#sum{
		color : brown;
		font-size : xx-large;
	}
</style>	
</head>
<body>
	<h2>감사합니다.</h2>
	<span id="main_menu"><%=main %></span>,
	<span id="side_menu"><%=side %></span>,
	<span id="drink_menu"><%=drink %></span>을/를 주문하셨습니다. <br/>
	총 결제금액은 <span id="sum"><%=sum %>원</span>입니다.
</body>
</html>