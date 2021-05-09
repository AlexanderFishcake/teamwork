package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;

@WebServlet("/binsert")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService  boardService = new BoardService ();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 사용자 입력값처리
		// title writer content
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		try {
			//업무로직
			int result = boardService.insertBoard(board);
			System.out.println("처리결과 = "+result);
			
			String location = request.getContextPath();
			String message="";
			
			if(result==1)
				location += "/blist";
			else
				location = "directory/views/common/error.jsp";		

			request.getSession().setAttribute("message", "게시글 등록 실패!");
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
