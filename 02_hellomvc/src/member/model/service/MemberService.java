package member.model.service;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public static final String MEMBER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	
	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		Member member = memberDao.selectId(conn,memberId);
		//6. 트랜잭션처리(DML) commit/rollback		
		if(member!=null) commit(conn);
		else rollback(conn);
		//7. 자원반납(conn)
		close(conn);
		return member;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		//상수 집어넣기
		member.setMemberRole(MEMBER_ROLE);
		int result = memberDao.insertMember(conn,member);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(conn,member);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = memberDao.deleteId(conn,memberId);	
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public int updatePassword(String memberId, String newPassword) {
		Connection conn = getConnection();
		int result = memberDao.updatePassword(conn,memberId,newPassword);	
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}



}
