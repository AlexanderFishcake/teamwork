package com.kh.test.model;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestService {
	private static TestDao testDao = new TestDao();
	
	public static Connection getConnection() {
		Connection conn = null;		
		String url = "jdbc:oracle:thin:@192.168.10.3:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			//2. Connection객체 생성 url, user, password
			conn = DriverManager.getConnection(url, user, password);
			//2-1. 자동커밋 false 설정
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static List<Test> selectList(){
		Connection conn = getConnection();
		List<Test> list = new ArrayList<>();
		try {
			 list= testDao.selectList(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}
}
