package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberDao {
		
	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class	//클래스 객체
				.getResource("/sql/member/member-query.properties") //url객체
				.getPath(); //String객체 : 절대경로
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Member selectId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOne");
		ResultSet rset=null;
		Member member = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, memberId);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
//				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");				
				String gender = rset.getString("gender");
				Date birtyday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birtyday, email, phone, address, hobby, enrollDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(생성역순 rset - pstmt)
		close(rset);
		close(pstmt);
		
		return member;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole());
			pstmt.setString(5, member.getGender());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getHobby());	
			pstmt.setDate(11, member.getEnrollDate());				
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(pstmt)
		close(pstmt);
		
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getMemberId());
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(pstmt)
		close(pstmt);
		
		return result;
	}

	public int deleteId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, memberId);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(pstmt)
		close(pstmt);
		
		return result;
	}

	public int updatePassword(Connection conn, String memberId, String newPassword) {
		PreparedStatement pstmt = null;
		//update member set password=? where member_id=?
		String sql = prop.getProperty("updatePassword");
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, newPassword);
			pstmt.setString(2, memberId);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(pstmt)
		close(pstmt);
		
		return result;
	}

	public List<Member> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectList");
		ResultSet rset=null;
		List<Member> list = new ArrayList<Member>();
		Member member = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);

			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");				
				String gender = rset.getString("gender");
				Date birtyday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birtyday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(생성역순 rset - pstmt)
		close(rset);
		close(pstmt);
		
		return list;
	}

	public List<Member> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectPagedList");
		ResultSet rset=null;
		List<Member> list = new ArrayList<Member>();
		Member member = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");				
				String gender = rset.getString("gender");
				Date birtyday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birtyday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(생성역순 rset - pstmt)
		close(rset);
		close(pstmt);
		
		return list;
	}

	public int updateMemberRole(Connection conn, String memberId, String memberRole) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemberRole");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberRole);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}

	public List<Member> searchMember(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("searchMember");
		
		switch(param.get("searchType")) {
		case "memberId" : query+= " member_id like '%"+param.get("searchKeyword")+"%'"; break;
		case "memberName" : query+= " member_name like '%"+param.get("searchKeyword")+"%'"; break;
		case "gender" : query+= " gender ='"+param.get("searchKeyword")+"'"; break;
		}
		System.out.println(query);
		//select * from member where member_id like %a%
		//select * from member where member_name like %동%
		//select * from member where gender = 'M'
		ResultSet rset=null;
		List<Member> list = new ArrayList<Member>();
		Member member = null;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");				
				String gender = rset.getString("gender");
				Date birtyday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birtyday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(생성역순 rset - pstmt)
		close(rset);
		close(pstmt);
		
		return list;
	}

	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMemberCount");
		int count = 0;		
		ResultSet rset=null;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				count= rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rset);
		close(pstmt);
		
		return count;
	}

	public List<Member> searchMember(Connection conn, Map<String, String> param, int start, int end) {
		PreparedStatement pstmt = null;
		String queryFront = prop.getProperty("searchPagedMemberFront");
		String queryBack = prop.getProperty("searchPagedMemberBack");
		String queryAll="";
		
		switch(param.get("searchType")) {
		case "memberId" : queryFront+= " member_id like '%"+param.get("searchKeyword")+"%'"; break;
		case "memberName" : queryFront+= " member_name like '%"+param.get("searchKeyword")+"%'"; break;
		case "gender" : queryFront+= " gender ='"+param.get("searchKeyword")+"'"; break;
		}
		/*
		 * searchPagedMemberFront = select * from( select row_number() over(order by enroll_date desc) rnum, M.* from ( select * from member where
		 * searchPagedMemberBack = ) M ) M where rnum between ? and ?
		 */
		queryAll = queryFront + queryBack;
		System.out.println(queryAll);
		
		ResultSet rset=null;
		List<Member> list = new ArrayList<Member>();
		Member member = null;
		try {
			pstmt = conn.prepareStatement(queryAll);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");				
				String gender = rset.getString("gender");
				Date birtyday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birtyday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//5. 자원반납(생성역순 rset - pstmt)
		close(rset);
		close(pstmt);
		
		return list;
	}

	public int searchMemberCount(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		String query = "select count(*) from member where";
		
		switch(param.get("searchType")) {
		case "memberId" : query+= " member_id like '%"+param.get("searchKeyword")+"%'"; break;
		case "memberName" : query+= " member_name like '%"+param.get("searchKeyword")+"%'"; break;
		case "gender" : query+= " gender ='"+param.get("searchKeyword")+"'"; break;
		}
		prop.getProperty("selectMemberCount");
		int count = 0;		
		ResultSet rset=null;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				count= rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rset);
		close(pstmt);
		
		return count;
	}



}
