package com.kh.ajax.homework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.homework.model.service.PhoneService;
import com.kh.ajax.homework.model.vo.Phone;

/**
 * Servlet implementation class HomeworkServlet
 */
@WebServlet("/phone")
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhoneService phoneService = new PhoneService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값
		String pname = request.getParameter("pname");
		int amount = Integer.parseInt(request.getParameter("amount"));
		System.out.println(pname+" : "+amount);
		//2. 업무로직
		Phone phone = new Phone();
		phone.setPname(pname);
		phone.setAmount(amount);
		int result = phoneService.insertPhone(phone);
		
		String orderby = "";
		orderby = (pname!="") ? "pdate" : "amount";
		List<Phone> list = phoneService.getPhonelist(orderby);
		System.out.println(list);
		
		//3. json을 문자열로 + 응답메시지
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		//System.out.println(jsonStr);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
