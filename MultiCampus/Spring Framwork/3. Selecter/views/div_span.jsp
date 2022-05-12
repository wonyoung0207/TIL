<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		margin:0;
		padding:0;
	}

	h1{
		color:red;
		background:black;
	}
	
	#hh1{
		color: blue;
		background: yellow;
	}
	
	.c1{
		color:green;
		background:pink;
	}
	
	a,p{
		color: orange;
	}
	
	a:hover{
		background : black;
		
	}

</style>

</head>
<body>

<h1>div_span page</h1>

<h1 id="hh1">Header1</h1>
<h1 class="c1">Header1</h1>
<h2 class="c1">Header2</h2>
<h2>Header2</h2>

<a href="#">click</a>
<span>span1</span>
<span>span2</span>
<p>Pragraph</p>


</body>
</html>