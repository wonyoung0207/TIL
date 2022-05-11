<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>login !! 환영합니다~ ${user.id} 님. </h1>
<form action="login" mathod="POST">
<fieldset>
<legend> 로그인 </legend>
<table border="1">
	<thead>
		<tr><th>ID</th><th>Password</th></tr>
	</thead>
	<tbody>
		<tr><td>${user.id} </td><td>${user.pwd}</td></tr>
	</tbody>
</table>

</fieldset>

</form>

</body>
</html>