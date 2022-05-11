<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		width:100px;
		height:100px;
	}

</style>

</head>
<body>

<h1>product</h1>
<form action="product" mathod="get">
<fieldset>
<legend> 입력한 물품 정보  </legend>
<table border="1">
	
	<tr><th>image</th><th>name</th><th>Price</th><th>Status</th><th>rank</th></tr>
	
	<tr>
	<td><img src="img/${product1.name}.png"></td>
	<td>${product1.name} </td>
	<td>${product1.price} </td>
	<td>${product1.status} </td>
	<td>${product1.rank} </td>
	</tr>
	
	<tr>
	<td><img src="img/${product2.name}.png"></td>
	<td>${product2.name} </td>
	<td>${product2.price} </td>
	<td>${product2.status} </td>
	<td>${product2.rank} </td>
	</tr>

</table>

</fieldset>

</form>



</body>
</html>