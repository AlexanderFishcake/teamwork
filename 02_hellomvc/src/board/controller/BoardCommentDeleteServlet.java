package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = 0;			//댓글의 번호
		int boardNo = 0;	//게시물의 번호
		try {
			no = Integer.parseInt(request.getParameter("no"));
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			int result = boardService.deleteBoardComment(no);
			
			System.out.println("처리결과 = "+result);			
			
			String msg = (result==1)? "댓글이 삭제되었습니다" : "댓글 삭제에 실패했습니다.";
			String url = request.getContextPath()+"/board/boardView?no="+boardNo;
			
			//3. DML요청 : 리다이렉트 & 사용자 피드백
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(url);
		} catch (Exception e) {
			//예외 로깅
			e.printStackTrace();
			//예외페이지 전환을 위해서 was에 예외던짐.
			throw e;
		}
	}

}
