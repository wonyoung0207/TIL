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

<h1>product</h1>
<form action="product" mathod="get">
<fieldset>
<legend> 입력한 물품 정보  </legend>
<table border="1">
	
	<tr><th>name</th><th>Price</th><th>Status</th><th>rank</th></tr>
	
	<tr><td>${product.name} </td><td>${product.price} </td><td>${product.status} </td><td>${product.rank} </td></tr>

</table>

</fieldset>

</form>



</body>
</html>