<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>/WEB-INF/views/board_write.jsp</title>
</head>
<body>

	<h1>사용자 정보 추가페이지</h1>
	<form action="insert.do" method="POST">
		<table width="100%" border="1" cellpadding="5">
			<tr>
				<th width="20%">아이디</th>
				<td width="80%">
					<input name="id" type="text" maxlength="8" required/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="password" type="password"  maxlength="8" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input name="name" type="text" maxlength="15" />
				</td>
			</tr>
			<tr>
				<th>역할</th>
				<td>
					<input name="role" type="text" maxlength="5"/>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정하기"/>
	</form>

</body>
</html>