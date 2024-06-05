<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
td{
	width:90px;
	text-align:center;
}
a{
	display:block;
}
</style>
</head>
<body>

	<h1>유저 목록</h1>
	<table border="1">
	<tr>
		<th> 아이디</th>
		<th>이름</th>
		<th> 역할</th>
		<td></td>
	</tr>
	<c:forEach var="user" items="${userList }">
		<tr>
		<td id="userId">
			<label>
				<a href="detail.do?id=${user.id }">
					<c:out value="${user.id }"></c:out>
				</a> 
			</label>
		</td>
		
		<td>
			<c:out value="${user.name }"></c:out> 
		</td>
		<td>
			<c:out value="${user.role }"></c:out> 
		</td>
		<td>
			<a href="delete.do?id=${user.id }">유저 삭제</a>
		</td>
		</tr>
	</c:forEach>
	</table>
<hr>
<a href="insert.do">유저 추가</a>
</body>
</html>