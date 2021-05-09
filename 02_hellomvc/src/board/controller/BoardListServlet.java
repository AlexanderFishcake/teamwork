package board.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.test.model.Test;
import com.kh.test.model.TestService;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardVer2;
import common.MvcUtils;
import sun.security.provider.certpath.CollectionCertStore;


@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 인코딩처리는 EncodingFilter가 선처리
		//1. 사용자 입력값
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 처리 코드 없음. 기본값 1 유지.
		}
		
		//2. 업무로직
		//a. contents영역 start ~ end
		int end = cPage * numPerPage;
		int start = (cPage-1)*numPerPage + 1;
		
//		List<Board> list = boardService.selectList(start,end);
//		System.out.println("list@servlet = "+list);
		
		//댓글카운트 추가
		
		//List<boardVer2>list에 리턴받을 수는 없나보다.
		List<BoardVer2> list = boardService.selectListVer2(start,end);
		System.out.println("listVer2@servlet = "+list);		

//		List<BoardVer2> listVer2 = boardService.countBoardCommentList(start,end);
		
		//시험용 test코드
		List<Test> testList = TestService.selectList();
		System.out.println("testList : "+testList);
		
		
		//b. pageBar영역 
		int totalContents = boardService.selectBoardCount();
		String url = request.getRequestURI(); // /mvc/board/boardList
		String pageBar = MvcUtils.getPageBar(
					cPage,
					numPerPage,
					totalContents,
					url
				);
		
		//3. 응답 html처리
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
				.forward(request, response);
	}

}
