package board.model.dao;

import static common.JDBCTemplate.close;

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
import java.util.Properties;

import board.model.exception.BoardException;
import board.model.vo.Attachment;
import board.model.vo.Board;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao() {
		//board-query.properties의 내용 읽어와서 prop에 저장
		//resources/sql/board-query.properties가 아니라
		//WEB-INF/classes/sql/board-query.properties에 접근해야 함.
		
		String fileName = BoardDao.class	//클래스 객체
				.getResource("/sql/board/board-query.properties") //url객체
				.getPath(); //String객체 : 절대경로

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectList");
		ResultSet rset=null;
		List<Board> list = new ArrayList<Board>();
		Board board = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				board = new Board();
				
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setReadCount(rset.getInt("read_count"));
				
				//첨부파일이 있는 경우
				if(rset.getInt("attach_no")!=0) {
					Attachment attach = new Attachment();
					attach.setBoardNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("no"));
					attach.setOriginalFileName(rset.getString("original_filename"));
					attach.setRenamedFileName(rset.getString("renamed_filename"));
					attach.setStatus("Y".equals(rset.getString("status"))? true : false );
					board.setAttach(attach);
				}
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoardCount");
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

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
//		String sql = "insert into board (no,title, writer, content) values (seq_board_no.nextval, ?,?,?)";
		int result = 0;

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new BoardException("게시물 등록 오류",e);
		}finally {
			//5. 자원반납(pstmt)
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		int boardNo = 0;		
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		String sql = prop.getProperty("selectLastBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				boardNo= rset.getInt("board_no");
			}
		} catch (SQLException e) {
			throw new BoardException("게시물 등록번호 조회 오류",e);
		}finally {
			close(rset);
			close(pstmt);
		}		
		return boardNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");

		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3-1) 미완성쿼리의 '?'에 값 대입
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());
			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new BoardException("게시물 첨부파일 등록 오류",e);
		}finally {
			//5. 자원반납(pstmt)
			close(pstmt);
		}
		
		return result;
	}

	public Board selectBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoard");
		ResultSet rset=null;
		Board board = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				board = new Board();
				
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setReadCount(rset.getInt("read_count"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	public Attachment selectAttach(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAttach");
		ResultSet rset=null;
		Attachment attach = null;
		try {
			//3. PreparedStatement 객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			//4. 실행 DML(executeUpdate) -> int , DQL(executeQuery) -> REsultSet
			rset = pstmt.executeQuery();
			//4-1) ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				attach = new Attachment();
				
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFileName(rset.getString("original_filename"));
				attach.setRenamedFileName(rset.getString("renamed_filename"));
				attach.setStatus("Y".equals(rset.getString("status"))? true : false );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attach;
	}
}
