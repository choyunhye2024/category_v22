<%@page import="com.glassis5.Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// request 객체에서 게시글 정보인 Dto를 가져옴
// 글 정보를 가져와 Dto 객체로 반환
Dto d = (Dto)request.getAttribute("post");
// 요청 파라미터에서 카테고리 정보를가져옴
// 카테고리 값을 url 파라미터로부터 가져옴
String category = request.getParameter("category");
%>
<!-- 게시글 정보를 출력 -->
카테고리 : <%=d.category %> <!-- 카테고리 출력 -->
<hr>
<%=d.no %> <!-- 글 번호출력 -->
<%=d.id %> <!-- 작성자 아이디 출력 -->
<%=d.title %><!-- 글 제목 출ㄹ력 -->
<hr>
<%=d.text %> <!-- 글 내용출력 -->
<hr>
<!-- 게시글 수정, 삭제 및 리스트로 돌아가는 링크 제공 -->
<a href = "/board/del?category=<%=category %>&no=<%=d.no %>">삭제</a>
<!-- 삭제 링크 -->
<a href = "/board/edit_insert?category=<%=category %>&no=<%=d.no %>">수정</a>
<!-- 수정링크 -->
<a href = "/board/list?category=<%=category %>">리스트로</a>
<!-- 홈으로 -->
<a href = "/">홈으로</a>

<hr>
<hr>
<hr>
<!-- JSTL 표기법으로 데이터출력 -->
${post.no} <!-- 글번호 -->
<hr>
${post.id} <!-- 작성자 아이디 -->
<hr>
${post.title} <!-- 글제목 -->
<hr>
${post.text}<!-- 글내용 출력 -->
<hr>  
</body>
</html>