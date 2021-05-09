package com.kh.test.model;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDao {
	
	
	public TestDao() {
	}

	public List<Test> selectList(Connection conn){
		List<Test> list = new ArrayList<>();
		Test test = null;
		PreparedStatement pstmt = null;
		String sql = "select * from test";
		ResultSet rset=null;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				test = new Test();
				test.setSeq(rset.getInt("seq"));
				test.setWriter(rset.getString("writer"));
				test.setTitle(rset.getString("title"));
				test.setContent(rset.getString("content"));
				test.setRegDate(rset.getDate("regDate"));
				
				list.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rset);
		close(pstmt);
		
		return list;
	}
}
