<%@page import="com.glassis5.DaoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<fieldset>
	<legend>일반</legend>
	<a href="/board/list?category=free">자유게시판</a>
	<hr>
	<a href="/board/list?category=notice">공지게시판</a>
</fieldset>
<fieldset>
	<legend>게임</legend>
	<a href="/board/list?category=lol">롤</a>
	<hr>
	<a href="/board/list?category=dia2">디아2</a>
	<hr>
	<a href="/board/list?category=loa">로아</a>
	<hr>
	<a href="/board/list?category=bag">배그</a>
</fieldset>
<fieldset>
	<legend>커뮤니티</legend>
	<a href="/board/list?category=humor">유머</a>	
	<hr>
	<a href="/board/list?category=animal">동물</a>
	<hr>
	<a href="/board/list?category=cat">고양이</a>
	<hr>
	<a href="/board/list?category=plants">식물</a>
	<hr>
	<a href="/board/list?category=car">자동차</a>
	<hr>
	<a href="/board/list?category=politics">정치</a>
	<hr>
	<a href="/board/list?category=soccer">축구</a>
	<hr>
	<a href="/board/list?category=basketball">농구</a>
	<hr>
</fieldset>
</body>
</html>