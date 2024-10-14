<%@page import="com.glassis5.DaoBoard"%>  <!-- DaoBoard 클래스 임포트 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  <!-- JSP 페이지 설정: Java 언어 사용, UTF-8 인코딩 설정 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">  <!-- 페이지 문자 인코딩을 UTF-8로 설정 -->
<title>Insert title here</title>  <!-- 페이지 제목 설정 -->
</head>
<body>

<%
    // 카테고리 정보를 요청 파라미터에서 가져옴
    String category = request.getParameter("category");  // 게시글 카테고리 값 가져오기
%>

<!-- 게시글 작성 폼 -->
<form action="/board/write">  <!-- 폼을 제출할 때 "/board/write"로 데이터를 전송 -->
    <!-- 게시글 카테고리 값을 숨김 필드로 전송 -->
    <input type="hidden" name="category" value="<%=category%>">  <!-- 카테고리 값을 포함하여 전송 -->

    <!-- 제목 입력 필드, 사용자가 제목을 입력 -->
    <input name="title" placeholder="제목">  <!-- 제목 입력 필드, 기본적으로 "제목"이라는 안내문 표시 -->

    <!-- 작성자 ID 입력 필드, 사용자가 ID를 입력 -->
    <input name="id" placeholder="작성자id">  <!-- 작성자 ID 입력 필드, "작성자id"라는 안내문 표시 -->

    <!-- 게시글 내용 입력 필드, 사용자가 내용을 입력 -->
    <input name="text" placeholder="내용">  <!-- 게시글 내용 입력 필드, "내용"이라는 안내문 표시 -->

    <!-- 폼 제출 버튼, 사용자가 작성한 내용을 서버로 전송 -->
    <input type="submit" value="쓰기">  <!-- 버튼을 누르면 폼 데이터를 제출 (쓰기 처리) -->
</form>

</body>
</html>
