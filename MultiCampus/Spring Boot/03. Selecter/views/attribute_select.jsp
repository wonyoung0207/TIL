<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input[type="text"]{
		background:lightblue;
	}
	
	input[name="pwd"]{
		background:lightyellow;
	}
	
	input[type="submit"]:hover{
		color: white;
		background:black;
	}
	
	input:focus{
		background:blue;
		
	}

</style>

</head>
<body>

<h1>attribute_select page</h1>

<form action="">

	ID<input type="text" name="id"><span>Mandatory</span><br>
	PWD<input type="password" name="pwd"><span>Mandatory</span><br>
	<input type="submit" value="login">
	

</form>


</body>
</html>