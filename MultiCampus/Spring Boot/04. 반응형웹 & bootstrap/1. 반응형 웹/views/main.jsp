<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/global.css">

<style>



</style>

</head>
<body>

<h1>Main page</h1>


<header>
<h1><a href="/"> HTML 5 & Css3</a></h1>
</header>


<nav>
<ul>
	<li><a href="html5">HTML5</a>
	<li><a href="css3">CSS</a>
	<li><a href="js">JavaScript</a>
	<li><a href="media">Media</a>
	

</ul>
</nav>


<section>
	<jsp:include page="${center}.jsp"></jsp:include>
</section>
<footer></footer>

</body>
</html>