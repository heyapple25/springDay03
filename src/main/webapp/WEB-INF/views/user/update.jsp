<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>사용저 정보 수정 페이지</h1>
	<form action="update.do" method="post">
		<input type="hidden" name="id" value="${user.id }">
		<input type="hidden" name="name" value="${user.name }">
		
		<table width="100%" border="1" cellpadding="5">
			<tr>
				<th width="20%">비밀번호</th>
				<td width="80%">
					<input name="password" type="text" value="${user.password }" maxlength="8" required/></td>
			</tr>
			<tr>
				<th>사용자 이름</th>
				<td>${user.name }</td>
			</tr>
			<tr>
				<th>역할</th>
				<td>
					<input type="text"  name="role"  value="${user.role }" maxlength="5" required/>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정하기"/>
	</form>
	<hr>

</body>
</html>