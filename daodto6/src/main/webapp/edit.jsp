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
// 서버에서 Dto 객체와 카테고리 파라미터를 가져오는 부분
// 글 정보를 가져옴. Dto 객체로 반환하여 사용
Dto dto = (Dto)request.getAttribute("post");
// 요정 파라미터로부터 카테고리 값을 가져옴
String category = request.getParameter("category");
%>
<!-- 카테고리 출력 -->
카테고리: <%=category %>
<hr>

<!-- 게시물 수정폼 -->
<!-- 수정처리 로직으로 폼 데이터를 전송할 경로저장 -->
<form action = "/board/edit_proc">
<!-- 카테고리 값을 숨겨서 전송 -->
<input type = "hidden" name = "category" value = "<%=category %>">
<!-- 글 번호 값을 숨겨서 전송 -->
<input type = "hidden" name = "no" value = "<%=dto.no %>">
<!-- 글 제목과 내용은 사용자가 수정할 수 있도록 input 필드에 기본값을 넣어줌 -->
<input name = "title" value = "<%=dto.title %>"><!-- 글제목필드 (사용자 수정가능) -->
<input name = "text" value = "<%=dto.text %>"><!-- 글 내용 필드(사용자 수정가능) -->
<input type = "submit" value = "수정"><!-- 폼 제출버튼 (수정처리) -->
</form>
<!-- 리스트 페이지 및 홈으로 돌아가는 링크제공 -->
<a href = "/board/list?category = <%=category %>">리스트로</a>
<a href = "/">홈으로</a> <!-- 홈페이지로 돌아가는 링크 -->
</body>
</html>