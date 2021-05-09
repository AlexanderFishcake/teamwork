package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		
		//2. 업무로직
		Board board = boardService.selectBoard(no);
		Attachment attach = boardService.selectAttach(no);
		board.setAttach(attach);
		
		//3. jsp 포워딩
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdateForm.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. MultipartRequest객체 생성
		// /WebContent/upload/board/업로드파일명.jpg
		// getRealPath :  web root dir를 절대경로로 반환
		
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		System.out.println("saveDirectory@servlet ="+saveDirectory);
		
		//최대 파일 허용 크기 10mb = 10 * 1kb * 1kb
		int maxPostSize = 10*1024*1024;
		
		//인코딩
		String encoding = "utf-8";
		
		//파일명 변경정책 객체
		//중복파일인 경우, numbering처리
		//filerename : 20210406191919_123.jpg
//		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = 
				new MultipartRequest(
						request,
						saveDirectory, 
						maxPostSize, 
						encoding, 
						policy
					);
		
		//2. db에 게시글/첨부파일 정보 저장
		
		//2-1. 사용자 입력값처리
		// title writer upFile content
		int no = Integer.parseInt(multipartRequest.getParameter("no"));
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		String content = multipartRequest.getParameter("content");
		
		//업로드한 파일명
		String originalFileName = multipartRequest.getOriginalFileName("upFile");
		String renamedFileName =  multipartRequest.getFilesystemName("upFile");
		
		//삭제할 첨부파일 번호
		String attachNo = multipartRequest.getParameter("delFile");
		System.out.println("attachNo@servlet : "+attachNo);

		Board board = new Board();
		board.setNo(no);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//첨부파일이 있는 경우
		//multipartRequest.getFile("upFile"):File !=null 이것은 또다른 방법
		if(originalFileName!=null) {
			Attachment attach = new Attachment();
			attach.setBoardNo(no);
			attach.setOriginalFileName(originalFileName);
			attach.setRenamedFileName(renamedFileName);
			board.setAttach(attach);
		}
		
		try {
			//2. 업무로직 :
			//첨부파일
			int result=0;
			if(attachNo !=null)
				result = boardService.deleteAttachment(attachNo);
			//db에 update
			result = boardService.updateBoard(board);
			System.out.println("처리결과 = "+result);
			
			HttpSession	session = request.getSession();
			
			String msg = (result>0) ? "게시글 수정 성공!" : "게시글 수정 실패!";
			
			String location = request.getContextPath()+"/board/boardView?no="+board.getNo();
			
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
