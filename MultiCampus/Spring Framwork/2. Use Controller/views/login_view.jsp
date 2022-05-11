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

<h1>login Page</h1>
<form action="login" mathod="POST">
<fieldset>
<legend> 로그인 </legend>
<table border="1">
	<thead>
		<tr><th>ID</th><td><input type="text" name="id"></td></tr>
	</thead>
	<tbody>
		<tr><th>Password</th><td><input type="text" name="pwd"></td></tr>
		<tr><td colspan="2"><input type="submit" value="send"></td></tr>
	</tbody>
</table>
</fieldset>
</form>

</body>
</html>