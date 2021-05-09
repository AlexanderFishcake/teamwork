package com.kh.ajax.homework.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.ajax.homework.model.vo.Phone;

import static com.kh.common.filter.JDBCTemplate.close;

public class PhoneDao {	
	private Properties prop = new Properties();

	public PhoneDao() {
//		String fileName = PhoneDao.class	//클래스 객체
//				.getResource("/sql/board/board-query.properties") //url객체
//				.getPath(); //String객체 : 절대경로
//
//		try {
//			prop.load(new FileReader(fileName));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public List<Phone> getPhonelist(Connection conn, String orderby) {
		PreparedStatement pstmt = null;
		String sql = "select * from (select * from smartphone order by ? desc) S where rownum between 1 and 5";
		ResultSet rset=null;
		List<Phone> list = new ArrayList<>();
		Phone phone = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderby);
			System.out.println(orderby);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				phone = new Phone();
				phone.setPname(rset.getString("pname"));		
				phone.setAmount(rset.getInt("amount"));
				phone.setPdate(rset.getDate("pdate"));
				
				list.add(phone);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertPhone(Connection conn, Phone phone) {
		PreparedStatement pstmt = null;
		String sql = "insert into smartphone values(?, ?, default)";
		int result = 0;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone.getPname());
			pstmt.setInt(2, phone.getAmount());
			
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
