<%@page import="market.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<fmt:setLocale value="ko_kr"/>

<!-- section시작 -->
    <section>
        <div class="section-body">
            <div class="board">
                <div class="section-title">
                    <h3>중고거래</h3>
                </div>
				<c:forEach items="${requestScope.list}" var="p" >
					<div class="board-box1" style="cursor:pointer;" onclick="location.href='<c:url value="/market/marketView?no=${p.no}"/>'">
	                    <div class="thumbnail">
	                    <c:if test="${not empty p.attach}">
	                    	<img src="<c:url value="/upload/market/${p.attach.renamedFileName}"/>" alt="${p.title}" width="270px"  height="160px">
	                    </c:if>
	                    </div>
	                    <div class="product-title">
	                    	<span>${p.title}</span>
	                    		<c:choose>
	                    			<c:when test='${"new" eq p.status }'><p>판매중</p></c:when>
	                    			<c:when test='${"reserved" eq p.status }'><p>예약중</p></c:when>
	                    			<c:otherwise><p>판매완료</p></c:otherwise>
	                    		</c:choose>
	                    	<p><fmt:formatNumber value="${p.price}" type="currency"/> </p>
	                    </div>
	                </div>
				</c:forEach>
                <div class="more">${requestScope.pageBar}</div>
            </div>
        </div>
    </section>
    <!-- section끝 -->
    <!-- 게시글 쓰기 이동 버튼 시작 -->
    <div class="post-btn" onclick="location.href='<c:url value="/market/marketForm"/>';" 
                		style="cursor:pointer;"><img src="<c:url value="/img/add.png"/> "></div>
    <!-- 게시글 쓰기 이동 버튼 끝 -->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>