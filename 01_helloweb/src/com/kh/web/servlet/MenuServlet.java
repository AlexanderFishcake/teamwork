package com.kh.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MenuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String main = request.getParameter("main_menu");
		String side = request.getParameter("side_menu");
		String drink = request.getParameter("drink_menu");
		
		int main_price=0;
		int side_price=0;
		int drink_price=0;
		int sum=0;
		
		if(main.equals("한우버거"))
			main_price=5000;
		else if(main.equals("밥버거"))
			main_price=4500;
		else if(main.equals("치즈버거"))
			main_price=4000;
		
		if(side.equals("감자튀김"))
			side_price=1500;
		else if(side.equals("어니언링"))
			side_price=1700;
		
		if(drink.equals("콜라"))
			drink_price=1000;
		else if(drink.equals("사이다"))
			drink_price=1000;
		else if(drink.equals("커피"))
			drink_price=1500;
		else if(drink.equals("밀크쉐이크"))
			drink_price=2500;
		
		sum=main_price+side_price+drink_price;
		
		request.setAttribute("sum", sum);
		
		RequestDispatcher reqDispatcher 
			= request.getRequestDispatcher("/menu/menuEnd.jsp");
		reqDispatcher.forward(request, response);
	}

}
