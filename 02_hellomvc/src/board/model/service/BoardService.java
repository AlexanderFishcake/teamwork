package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.BoardVer2;

public class BoardService {
	private BoardDao boardDao = new BoardDao();

	public List<Board> selectList(int start, int end) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		List<Board> list = boardDao.selectList(conn,start,end);
		//6. 트랜잭션처리(DML) commit/rollback		
		if(list!=null) commit(conn);
		else rollback(conn);
		//7. 자원반납(conn)
		close(conn);
		
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		//--------Dao 요청----------
		int totalContent = boardDao.selectBoardCount(conn);
		//7. 자원반납(conn)
		close(conn);
		
		return totalContent;
	}
	
	/**
	 * 첨부파일이 있는 경우, attach객체를 attachment테이블에 등록한다.
	 * - board등록, attachment등록은 하나의 트랜잭션으로 처리되어야 한다.
	 * - 하나의 Connection에 의해 처리되어야 한다.
	 * @param board
	 * @return
	 */
	public int insertBoard(Board board) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = boardDao.insertBoard(conn,board);
			
			//생성된 board_no를 가져오기
			int boardNo =  boardDao.selectLastBoardNo(conn);
			System.out.println("boardNo@service = "+boardNo);
			
			board.setNo(boardNo);
			
			if(board.getAttach()!=null) {
				//참조할 boardNo 세팅
				board.getAttach().setBoardNo(boardNo);
				result = boardDao.insertAttachment(conn,board.getAttach());
			}
			commit(conn);

		} catch (Exception e) {
//			e.printStackTrace();
			rollback(conn);
			result=0;
			throw e;
		} finally {
			close(conn);			
		}
	
//		if(result>0) commit(conn);
//		else rollback(conn);
		
		return result;
	}

	public Board selectBoard(int no) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		Board board = boardDao.selectBoard(conn,no);
		//6. 트랜잭션처리(DML) commit/rollback		
		if(board!=null) commit(conn);
		else rollback(conn);
		//7. 자원반납(conn)
		close(conn);
		
		return board;
	}
	
	/**
	 * board_no로 attachment 행 조회
	 * 
	 * @param no
	 * @return
	 */
	public Attachment selectAttach(int no) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		Attachment attach = boardDao.selectAttach(conn,no);
		//6. 트랜잭션처리(DML) commit/rollback		
//		if(attach!=null) commit(conn);
//		else rollback(conn);
		//7. 자원반납(conn)
		close(conn);
		
		return attach;
	}

	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoard(conn,no);
			if(result==0)
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : "+no);
			commit(conn);
		} catch (Exception e) {
//			e.printStackTrace();
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 연결.
		} finally {
			close(conn);
		}

		return result;
	}

	public int updateBoard(Board board) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//1. board update
			result = boardDao.updateBoard(conn,board);
			//2. attachment insert
			if(board.getAttach()!=null)
				result = boardDao.insertAttachment(conn, board.getAttach());
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	public int deleteAttachment(String attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteAttachment(conn,attachNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.insertBoardComment(conn,bc);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	public List<BoardComment> selectBoardCommentList(int no) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		List<BoardComment> list = boardDao.selectBoardCommentList(conn,no);
		close(conn);
		return list;
	}

	public int deleteBoardComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoardComment(conn,no);
			if(result==0)
				throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다. : "+no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 연결.
		} finally {
			close(conn);
		}

		return result;
	}

	public List<BoardVer2> countBoardCommentList(int start, int end) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		List<BoardVer2> list = boardDao.countBoardCommentList(conn,start,end);

		//7. 자원반납(conn)
		close(conn);
		
		return list;
	}

	public List<BoardVer2> selectListVer2(int start, int end) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		List<BoardVer2> list = boardDao.selectListVer2(conn,start,end);
		//commentCount를 위한 메소드
		List<BoardVer2> listForCount = boardDao.countBoardCommentList(conn,start,end);
		
		for(int i=0;i<list.size();i++) {
			list.get(i).setCommentCount(listForCount.get(i).getCommentCount());
		}
		
		close(conn);
		
		return list;
	}



}
