<%@page import="board.model.vo.BoardVer2"%>
<%@page import="board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
//	List<Board> list = (List<Board>)request.getAttribute("list");
	List<BoardVer2> list = (List<BoardVer2>)request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판 </h2>
	<%if(loginMember!=null){ %>
	<input type="button" value="글쓰기" id="btn-add" 
		onclick="location.href='<%=request.getContextPath()%>/board/boardForm'"/>
	<%} %>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
			<th>조회수</th>
		</tr>
		<tbody>
			<%if(list==null || list.isEmpty()){ %>
				<tr>
					<td colspan="10" style="text-align: center;">조회된 게시글이 없습니다.</td>
				</tr>
			<%
				} else{ 
					//for(Board b : list){
					for(BoardVer2 b : list){
			%>
						<tr>
							<td><%=b.getNo() %></td>
							<td>
								<a href="<%=request.getContextPath()%>/board/boardView?no=<%=b.getNo()%>"><%=b.getTitle() %></a>
								<span style="font-size: small;">[<%=b.getCommentCount() %>]</span>
							</td>
							<td><%=b.getWriter() %></td>
							<td><%=b.getRegDate() %></td>
							<td>
								<%if(b.getAttach()!= null){ %>
								<img src="<%=request.getContextPath() %>/images/file.png" alt="" style="width: 16px"/>
								<% }%>
							</td>
							<td><%=b.getReadCount() %></td>
						</tr>
			
			
			<%
					}
				} 
			%>
		</tbody>
	</table>

	<div id='pageBar'></div>
	<%=request.getAttribute("pageBar") %>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
