<%@page import="com.glassis5.Dto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.glassis5.BoardListProcessor"%>
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
// BoardListProcessor 객체를 request에서 가져와서 게시글 목록과 관련 데이터를 사용
BoardListProcessor blp = (BoardListProcessor)request.getAttribute("blp");

// 게시글 목록을 BoardListProcessor 객체에서 가져옴
ArrayList<Dto> posts = blp.posts;
%>

<!-- 게시판의 카테고리와 게시글 목록을 표시하는 부분 -->
<fieldset>
<legend><%=blp.category %> 게시판 </legend><!-- 현재 게시판의 카테고리를 표시 -->
글번호, 제목, 작성자 <hr><!-- 글 목록의 헤더를 간단히 표시 -->
<%

// 게시글 목록을 반복하여 화면에 출력
for(int i=0; i<posts.size(); i=i+1){
	
	%>
	<%=posts.get(i).no %><!-- 게시글번호출력 -->
	<!-- 게시글 제목을 클릭하면 해당 게시글로 이동 -->
	<a href = "/board/read?category<%=blp.category %>&no=<%=posts.get(i).no %>"><%=posts.get(i).title %></a>
	<%=posts.get(i).id %><!-- 게시글 작성자 출력 -->
	<hr>
	<%
}
%>
</fieldset>
<fieldset>
<!-- 페이지 네비게이션 영역 -->
<legend>페이지블럭</legend>
<!--  html 형식으로 페이징처리된 페이지 목록출력 -->
<%=blp.getHtmlPageList() %>
</fieldset>

<!-- 링크영역: 게시글 작성, 목록으로 돌아가기, 홈으로 가기 링크 제공 -->
<fieldset>
<legend>링크</legend>
<!-- 게시글 작성페이지로 이동 -->
<a href = "/write.jsp?category=<%=blp.category %>">쓰기</a>
<!--  게시글 목록페이지로 이동 -->
<a href = "/board/list?category=<%=blp.category %>&">리스트로</a>
<!--  혼페이지로 이동 -->
<a href = "/">홈으로</a>
</fieldset>

<!-- 검색 기능 영역 -->
<fieldset>
<legend>검색</legend>
<!--  검색어 입력 폼 : 게시글 목록을 검색할 수 있음  -->
<form action = "/board/list">
<input type = "hidden" name = "category" value="<%=blp.category %>"> <!-- 현재 카테고리를 숨겨서 전송 -->
<input name = "word"><!-- 검색어 입력 필드 -->
<input type = "submit" value = "검색"> <!-- 검색버튼  -->
</form>
</fieldset>
</body>
</html>