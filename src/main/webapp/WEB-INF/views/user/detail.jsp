<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>사용자 정보 상세보기</h1>
	
	<table width="100%" border="1">
		<tr>
			<th width="20%">ID</th>
			<td width="80%">
				[<c:out value="${user.id }"/>] </td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><c:out value="${user.password }"></c:out></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><c:out value="${user.name }"></c:out></td>
		</tr>
		<tr>
			<th>역할</th>
			<td><c:out value="${user.role}"></c:out></td>
		</tr>
	</table>
	<hr>
	<a href="list.do">사용자 목록</a>
	<a href="update.do?id=${user.id }">사용자 수정</a>
	<a href="delete.do?id=${user.id}">사용자 삭제</a>
</body>
</html>