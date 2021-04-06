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

public class BoardService {
	private BoardDao boardDao = new BoardDao();
	public int globalBoardNo = 0; 

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
			
			if(board.getAttach()!=null) {
				//참조할 boardNo 세팅
				board.getAttach().setBoardNo(boardNo);
				result = boardDao.insertAttachment(conn,board.getAttach());
			}
			commit(conn);
			//내 임의 코드
			globalBoardNo = boardNo;
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
			result=0;
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

	public Attachment selectAttach(int no) {
		Connection conn = getConnection();
		//--------Dao 요청----------
		Attachment attach = boardDao.selectAttach(conn,no);
		//6. 트랜잭션처리(DML) commit/rollback		
		if(attach!=null) commit(conn);
		else rollback(conn);
		//7. 자원반납(conn)
		close(conn);
		
		return attach;
	}
}
