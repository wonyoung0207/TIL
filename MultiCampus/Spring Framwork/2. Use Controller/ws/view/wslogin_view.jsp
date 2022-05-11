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

<h1>wslogin Page</h1>
<form action="wslogin" mathod="POST">
<fieldset>
<legend> 로그인 </legend>
<table border="1">
	<thead>
		<tr><th>ID</th><td><input type="text" name="id" value="id01"></td></tr>
	</thead>
	<tbody>
		<tr><th>Password</th><td><input type="text" name="pwd" value="1234"></td></tr>
		<tr><td colspan="2"><input type="submit" value="send"></td></tr>
	</tbody>
</table>
</fieldset>
</form>

</body>
</html>