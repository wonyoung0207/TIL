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

<h1>wsProduct List Page</h1>
<form action="wsproduct" mathod="get">
<fieldset>
<legend> Product List</legend>
<table border="1" id="product_tb">

	<thead>
		<tr><th>num</th><th>과일 이름</th><th>가격</th><th>상태</th><th>등급</th></tr>
	</thead>
	<tbody>
		<tr>
		<td>1</td>
		<td><input type="text" name="name1" value="apple"></td>
		<td><input type="text" name="price1" value="1000"></td>
		<td><input type="text" name="status1" value="fresh"></td>
		<td><input type="text" name="rank1" value="중"></td>
		</tr>
		
		<tr>
		<td>2</td>
		<td><input type="text" name="name2" value="orange"></td>
		<td><input type="text" name="price2" value="2000"></td>
		<td><input type="text" name="status2" value="frozen"></td>
		<td><input type="text" name="rank2" value="중"></td>
		</tr>
		
	</tbody>
</table>
<input type="submit" value="send Product">
</fieldset>
</form>

</body>
</html>