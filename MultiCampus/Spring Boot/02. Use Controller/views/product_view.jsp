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

<h1>product info Page</h1>
<form action="product" mathod="get">
<fieldset>
<legend> 물건의 정보를 입력해 주세요.   </legend>
<table border="1">
	<tr><th>name</th><td><input type="text" name="name" value="product"></td></tr>
	<tr><th>price</th><td><input type="text" name="price" value="1000"></td></tr>
	
	<tr><th>status</th><td>
	<input type="radio" name="pro_status" value="frozen"> 냉동
	<input type="radio" name="pro_status1" value="fresh"> 신선
	</td></tr>
	
	<tr><th>rank</th><td>
	<input type="checkbox" name="rank" value="상"> 상
	<input type="checkbox" name="rank" value="중" checked> 중
	<input type="checkbox" name="rank" value="하"> 하
	
	</td></tr>
	
</table>
<input type="submit" value="send">

</fieldset>

</form>

</body>
</html>