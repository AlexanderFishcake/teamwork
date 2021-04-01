/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.44
 * Generated at: 2021-04-01 10:44:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import member.model.service.MemberService;
import member.model.vo.Member;

public final class memberList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1617176343017L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1616656743416L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("member.model.service.MemberService");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String msg = (String)session.getAttribute("msg");
	if(msg!=null) session.removeAttribute("msg");
	String loc = (String)request.getAttribute("loc");
	Member loginMember = (Member)session.getAttribute("loginMember");
	
	//사용자 쿠키처리
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie c : cookies){
			String name = c.getName();
			String value = c.getValue();
			//System.out.println(name+" : "+value);
			if("saveId".equals(name))
				saveId = value;
			
		}
	}

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Hello MVC</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/style.css\" />\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-3.6.0.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\t");
      out.write('\r');
      out.write('\n');
      out.write('	');
if(msg!=null){ 
      out.write("\r\n");
      out.write("\t\talert(\"");
      out.print( msg );
      out.write("\");\r\n");
      out.write("\t");
 } 
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
if(loc!=null){ 
      out.write("\r\n");
      out.write("\t\tlocation.href = \"");
      out.print( loc );
      out.write("\";\r\n");
      out.write("\t");
 } 
      out.write("\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t/**\r\n");
      out.write("\t\t* 로그인 폼 유효성검사\r\n");
      out.write("\t\t*/\r\n");
      out.write("\t\t$(\"#loginFrm\").submit(function(){\r\n");
      out.write("\t\t\tvar $memberId = $(memberId);\r\n");
      out.write("\t\t\tvar $password = $(password);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(/^.{4,}$/.test($memberId.val()) == false){\r\n");
      out.write("\t\t\t\talert(\"유효한 아이디를 입력하세요.\");\r\n");
      out.write("\t\t\t\t$memberId.select();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(/^.{4,}$/.test($password.val()) == false){\r\n");
      out.write("\t\t\t\talert(\"유효한 비밀번호를 입력하세요.\");\r\n");
      out.write("\t\t\t\t$password.select();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"container\">\r\n");
      out.write("\t\t<header>\r\n");
      out.write("\t\t\t<h1>Hello MVC</h1>\r\n");
      out.write("\t\t\t<div class=\"login-container\">\r\n");
      out.write("\t\t\t");
 if(loginMember == null){ 
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t<!-- 로그인폼 시작 -->\r\n");
      out.write("\t\t\t\t<form id=\"loginFrm\" action=\"");
      out.print(request.getContextPath() );
      out.write("/member/Login\" method=\"POST\">\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"memberId\" id=\"memberId\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tplaceholder=\"아이디\" tabindex=\"1\" value=\"");
      out.print( saveId !=null? saveId : "" );
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"submit\" value=\"로그인\" tabindex=\"3\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"password\" name=\"password\" id=\"password\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"비밀번호\" tabindex=\"2\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"saveId\" id=\"saveId\" ");
      out.print(saveId!=null ? "checked" : "" );
      out.write("/>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"saveId\">아이디저장</label>&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"button\" value=\"회원가입\" onclick=\"location.href='");
      out.print(request.getContextPath() );
      out.write("/member/memberEnroll'\">\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t<!-- 로그인폼 끝-->\r\n");
      out.write("\t\t\t\t");
 } else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<table id=\"Login\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      out.print(loginMember.getMemberName() );
      out.write("님, 안녕하세요</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"button\" value=\"내정보보기\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\tonclick=\"location.href='");
      out.print(request.getContextPath() );
      out.write("/member/memberView';\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"button\" value=\"로그아웃\"\r\n");
      out.write("\t\t\t\t\t\t\t\t onclick=\"location.href='");
      out.print(request.getContextPath() );
      out.write("/member/Logout'\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- 메인메뉴 시작 -->\r\n");
      out.write("\t\t\t<nav>\r\n");
      out.write("\t\t\t\t<ul class=\"main-nav\">\r\n");
      out.write("\t\t\t\t\t<li class=\"home\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("\">Home</a></li>\r\n");
      out.write("\t\t\t\t\t<li class=\"notice\"><a href=\"#\">공지사항</a></li>\r\n");
      out.write("\t\t\t\t\t<li class=\"board\"><a href=\"#\">게시판</a></li>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t");
if(loginMember!=null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t<li class=\"members\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberList\">회원관리</a></li>\r\n");
      out.write("\t\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</nav>\r\n");
      out.write("\t\t\t<!-- 메인메뉴 끝-->\r\n");
      out.write("\t\r\n");
      out.write("\t\t</header>\r\n");
      out.write("\t\t<section id=\"content\">");
      out.write('\r');
      out.write('\n');

	List<Member> list = (List<Member>)request.getAttribute("list");
	String type = request.getParameter("searchType");
	String kw = request.getParameter("searchKeyword");

      out.write("\r\n");
      out.write("<!-- 관리자용 admin.css.link -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/admin.css\" />\r\n");
      out.write("<style>\r\n");
      out.write("\tdiv#search-container {margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3);}\r\n");
      out.write("\tdiv#search-memberId {display: ");
      out.print(type==null || "memberId".equals(type)? "inline-block":"none");
      out.write(";}\r\n");
      out.write("\tdiv#search-memberName{display:");
      out.print("memberName".equals(type)? "inline-block":"none");
      out.write(";}\r\n");
      out.write("\tdiv#search-gender{display:");
      out.print("gender".equals(type)? "inline-block":"none");
      out.write(";}\r\n");
      out.write("</style>\r\n");
      out.write("<section id=\"memberList-container\">\r\n");
      out.write("\t<h2>회원관리</h2>\r\n");
      out.write("    <div id=\"search-container\">\r\n");
      out.write("       검색타입 : \r\n");
      out.write("       <select id=\"searchType\">\r\n");
      out.write("           <option value=\"memberId\" ");
      out.print("memberId".equals(type)? "selected" : "" );
      out.write(">아이디</option>\t\t\r\n");
      out.write("           <option value=\"memberName\" ");
      out.print("memberName".equals(type)? "selected" : "" );
      out.write(">회원명</option>\r\n");
      out.write("           <option value=\"gender\" ");
      out.print("gender".equals(type)? "selected" : "" );
      out.write(">성별</option>\r\n");
      out.write("       </select>\r\n");
      out.write("       <div id=\"search-memberId\" class=\"search-type\">\r\n");
      out.write("           <form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("               <input type=\"hidden\" name=\"searchType\" value=\"memberId\"/>\r\n");
      out.write("               <input type=\"text\" name=\"searchKeyword\"  size=\"25\" value=\"");
      out.print("memberId".equals(type) ? kw:"");
      out.write("\" placeholder=\"검색할 아이디를 입력하세요.\"/>\r\n");
      out.write("               <button type=\"submit\">검색</button>\t\t\t\r\n");
      out.write("           </form>\t\r\n");
      out.write("       </div>\r\n");
      out.write("       <div id=\"search-memberName\" class=\"search-type\">\r\n");
      out.write("           <form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("               <input type=\"hidden\" name=\"searchType\" value=\"memberName\"/>\r\n");
      out.write("               <input type=\"text\" name=\"searchKeyword\" size=\"25\" value=\"");
      out.print("memberName".equals(type) ? kw:"");
      out.write("\" placeholder=\"검색할 이름을 입력하세요.\"/>\r\n");
      out.write("               <button type=\"submit\">검색</button>\t\t\t\r\n");
      out.write("           </form>\t\r\n");
      out.write("       </div>\r\n");
      out.write("       <div id=\"search-gender\" class=\"search-type\">\r\n");
      out.write("           <form action=\"");
      out.print(request.getContextPath());
      out.write("/admin/memberFinder\">\r\n");
      out.write("               <input type=\"hidden\" name=\"searchType\" value=\"gender\"/>\r\n");
      out.write("               <input type=\"radio\" name=\"searchKeyword\" value=\"M\" ");
      out.print("gender".equals(type) && "M".equals(kw) ? "checked":"");
      out.write("> 남\r\n");
      out.write("               <input type=\"radio\" name=\"searchKeyword\" value=\"F\" ");
      out.print("gender".equals(type) && "F".equals(kw) ? "checked":"");
      out.write("> 여\r\n");
      out.write("               <button type=\"submit\">검색</button>\r\n");
      out.write("           </form>\r\n");
      out.write("       </div>\r\n");
      out.write("   </div>\r\n");
      out.write("\t<table id=\"tbl-member\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>아이디</th>\r\n");
      out.write("\t\t\t\t<th>이름</th>\r\n");
      out.write("\t\t\t\t<th>회원권한</th>\r\n");
      out.write("\t\t\t\t<th>성별</th>\r\n");
      out.write("\t\t\t\t<th>생년월일</th>\r\n");
      out.write("\t\t\t\t<th>이메일</th>\r\n");
      out.write("\t\t\t\t<th>전화번호</th>\r\n");
      out.write("\t\t\t\t<th>주소</th>\r\n");
      out.write("\t\t\t\t<th>취미</th>\r\n");
      out.write("\t\t\t\t<th>가입일</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t\t<tbody>\r\n");
      out.write("\t\t");
if(list==null || list.isEmpty()){ 
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td colspan=\"10\" style=\"text-align: center;\">조회된 회원이 없습니다.</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t");

			} else{ 
				for(Member m : list){
		
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getMemberId() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getMemberName() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<select class=\"member-role\" data-member-id=\"");
      out.print(m.getMemberId());
      out.write("\" data-member-role=\"");
      out.print(m.getMemberRole());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<option\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalue=\"");
      out.print(MemberService.ADMIN_ROLE);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      out.print(MemberService.ADMIN_ROLE.equals(m.getMemberRole())? "selected" : " ");
      out.write(" >관리자</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t<option\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalue=\"");
      out.print(MemberService.MEMBER_ROLE);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      out.print(MemberService.MEMBER_ROLE.equals(m.getMemberRole())? "selected" : " ");
      out.write(" >일반</option>\r\n");
      out.write("\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print("M".equals(m.getGender()) ? "남":"여" );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getBirthday()!=null ? m.getBirthday() : "" );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getEmail()!=null? m.getEmail() : "" );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getPhone() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getAddress()!=null? m.getAddress() : "" );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getHobby() != null ? m.getHobby() : "");
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(m.getEnrollDate() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");

				}
			} 
		
      out.write("\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div id=\"pageBar\"></div>\r\n");
      out.write("\t\t");
      out.print(request.getAttribute("pageBar") );
      out.write("\r\n");
      out.write("</section>\r\n");
      out.write("<form \r\n");
      out.write("\taction=\"");
      out.print(request.getContextPath() );
      out.write("/admin/memberRoleUpdate\" \r\n");
      out.write("\tname=\"memberRoleUpdateFrm\"\r\n");
      out.write("\tmethod=\"POST\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"memberId\" />\r\n");
      out.write("\t<input type=\"hidden\" name=\"memberRole\" />\r\n");
      out.write("</form>\r\n");
      out.write("<script>\r\n");
      out.write("/**\r\n");
      out.write(" * #searchType가 변경되면, 해당form이 노출되어야 한다.\r\n");
      out.write(" */\r\n");
      out.write("\t$(searchType).change(function(){\r\n");
      out.write("\t\tvar searchTypeVal = $(this).val();\r\n");
      out.write("\t\t$(\".search-type\")\r\n");
      out.write("\t\t\t.hide()\r\n");
      out.write("\t\t\t.filter(\"#search-\"+searchTypeVal)\r\n");
      out.write("\t\t\t.css(\"display\",\"inline-block\");\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t$(\".member-role\").on(\"change\",function(){\r\n");
      out.write("\t\tvar memberId = $(this).data(\"memberId\");\r\n");
      out.write("\t\tvar memberRole = $(this).val();\r\n");
      out.write("\t\t//alert(memberId + \" : \" + memberRole);\r\n");
      out.write("\t\tvar memberRoleStr = \r\n");
      out.write("\t\t\tmemberRole == \"");
      out.print(MemberService.ADMIN_ROLE );
      out.write("\" ? \"관리자\" : \"일반\";\r\n");
      out.write("\t\tif(confirm(\"[\"+memberId + \"]\" + \"회원의 권한을 \"+\"[\"+memberRoleStr+\"]로 변경하시겠습니까?\")){\r\n");
      out.write("\t\t\tvar $frm = $(document.memberRoleUpdateFrm);\r\n");
      out.write("\t\t\t$frm.find(\"[name=memberId]\").val(memberId);\r\n");
      out.write("\t\t\t$frm.find(\"[name=memberRole]\").val(memberRole);\r\n");
      out.write("\t\t\t$frm.submit();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//변경 취소를 눌렀다면 option을 원래대로.\r\n");
      out.write("\t\telse{\r\n");
      out.write("\t\t\tconsole.log(\"옵션값 : \"+$(this).val());\r\n");
      out.write("\t\t\tconsole.log(\"originMemberRole : \"+$(this).data(\"memberRole\"));\r\n");
      out.write("\t\t\tvar originMemberRole = $(this).data(\"memberRole\");\r\n");
      out.write("\t\t\t$(this).val(originMemberRole).prop(\"selected\", true);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\t\t</section>\r\n");
      out.write("\r\n");
      out.write("\t\t<footer>\r\n");
      out.write("\t\t\t<p>&lt;Copyright 1998-2021 <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>\r\n");
      out.write("\t\t</footer>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
