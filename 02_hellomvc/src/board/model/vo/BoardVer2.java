package board.model.vo;

import java.sql.Date;

public class BoardVer2 extends Board {

	private int commentCount;

	public BoardVer2() {
		super();
	}
	
	public BoardVer2(int no, String title, String writer, String content, Date regDate, int readCount,
			Attachment attach) {
		super(no, title, writer, content, regDate, readCount, attach);
	}

	public BoardVer2(int no, String title, String writer, String content, Date regDate, int readCount,
			Attachment attach, int commentCount) {
		super(no, title, writer, content, regDate, readCount, attach);
		this.commentCount = commentCount;
	}

	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "BoardVer2 [no=" + super.getNo() + ", title=" + super.getTitle() + ", writer="+ super.getWriter()
				+ ", content=" + super.getContent() + ", regDate="+ super.getRegDate() + ", readCount=" + super.getReadCount()
				+ ", attach=" + super.getAttach() + "commentCount=" + commentCount+"]";
	}
	
}
