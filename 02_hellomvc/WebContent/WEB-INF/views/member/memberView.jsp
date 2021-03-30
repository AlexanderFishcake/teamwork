<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	/* Member loginMember = (Member)session.getAttribute("loginMember"); */
	String memberId = loginMember.getMemberId();
	//String password = loginMember.getPassword();
	String memberName = loginMember.getMemberName();
	Date birthday = loginMember.getBirthday();
	String email = loginMember.getEmail() != null? loginMember.getEmail() : "";
	String phone = loginMember.getPhone();
	String address = loginMember.getAddress() != null? loginMember.getAddress() : "";
	String gender = loginMember.getGender() != null? loginMember.getGender() : "";
	String hobby = loginMember.getHobby();
	
	List<String> hobbyList = null;
	if(hobby!=null){
		String[] arr = hobby.split(",");
		hobbyList = Arrays.asList(arr);
	}
	
%>
<section id=enroll-container>
	<h2>회원 정보</h2>
	<form id="memberUpdateFrm" name="memberUpdateFrm" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" id="memberId_" value="<%=memberId %>" readonly>
				</td>
			</tr>
<%-- 			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" value="<%=password %>" required>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password2" value="<%=password %>" required><br>
				</td>
			</tr> --%> 
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="<%=memberName %>"  required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%=birthday %>"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%=email %>"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%=phone %>" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address" value="<%=address %>"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
			       		 <input type="radio" name="gender" id="gender0" value="M" 
			       		 	<% if("M".equals(gender)){%>
			       		 		checked 
			       		 	<% } %>	>
						 <label for="gender0">남</label>
						 <input type="radio" name="gender" id="gender1" value="F" 
			       		 	<% if("F".equals(gender)){%>
			       		 		checked 
			       		 	<% } %>	>
						 <label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=hobbyChecked(hobbyList, "운동") %>>
			       		 	<label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" <%=hobbyList !=null && hobbyList.contains("등산") ? "checked" : "" %>>
			       		 	<label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" <%=hobbyList !=null && hobbyList.contains("독서") ? "checked" : "" %>>
							<label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임" <%=hobbyList !=null && hobbyList.contains("게임") ? "checked" : "" %>>
			       		 	<label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" <%=hobbyList !=null && hobbyList.contains("여행") ? "checked" : "" %>>
			       		 	<label for="hobby4">여행</label><br />


				</td>
			</tr>
		</table>
        <input type="button" onclick="updateMember();" value="정보수정"/>
        <input type="button" onclick="updatePassword();" value="비밀번호변경"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
</section>
<%!
	//메소드 선언문
	public String hobbyChecked(List<String> hobbyList, String hobby){
		return hobbyList !=null && hobbyList.contains(hobby) ?
					"checked" : "";
}
%>
<script>
	function updatePassword(){
		location.href = "<%=request.getContextPath() %>/member/updatePassword";
	}

$("#memberUpdateFrm").submit(function(){
	//password
/* 	var $p1 = $("#password_");
	var $p2 = $("#password2");
	if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
		alert("유효한 패스워드를 입력하세요.");
		$p1.select();
		return false;
	} 
	
	if($p1.val() != $p2.val()){
		alert("패스워드가 일치하지 않습니다.");
		$p1.select();
		return false;
	}*/
	
	//memberName
	var $memberName = $("#memberName");
	if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		$memberName.select();
		return false;
	}
	
	//phone
	var $phone = $("#phone");
	//-제거하기
	$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	
	if(/^010[0-9]{8}$/.test($phone.val()) == false){
		alert("유효한 전화번호가 아닙니다.");
		$phone.select();
		return false;
	}
	
	return true;
});
	function updateMember(){

		$frm = $(document.memberUpdateFrm);
		$frm.attr("action", "<%= request.getContextPath()%>/member/memberUpdate")
			.attr("method","POST")
			.submit();
		console.log($frm);
		
	}
	
	function deleteMember(){
		if(confirm("정말로 탈퇴하시겠습니까?")){
			$frm = $(document.memberUpdateFrm);		
			$frm.attr("action", "<%= request.getContextPath() %>/member/memberDelete")
				.attr("method","POST")
				.submit();
			console.log("delete");			
		}
	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
