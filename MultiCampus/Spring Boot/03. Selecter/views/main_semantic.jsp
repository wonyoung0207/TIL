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
/* Global CSS start, 항상 사용되는 dCSS */
* {
	margin: 0;
	padding: 0;
	
}

a {
	text-decoration: none;
	color: black;
	
}



ul, ol {
	list-style: none;
}
/* Global CSS end  */
header{
	width:600px;
	height: 150px;
	background:lightyellow;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */

}

header > h1{

	text-align:center;
	line-height: 100px;
}

nav{
	width:600px;
	height: 40px;
	background:lightgreen;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */

}

nav > ul{
	width:500px;
	margin:0 auto;

}
nav > ul > li{
	float:left;
	width:125px;
}

nav > ul > li > a{
	display: block;
	text-align: center;
	line-height: 30px;
}



section{
	width:600px;
	height: 500px;
	background:lightgray;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */

}

section > aside{
	float:left;
}

section > #left_aside{
	width:100px;
	height: 500px;
	background:yellow;
}

section > #center_aside{
	width:450px;
	height: 500px;
	background:white;
}

section > #right_aside{
	width:50px;
	height: 500px;
	background:darkgray;
}



footer{
	width:600px;
	height: 30px;
	background:lightblue;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */

}


</style>

</head>
<body>

<h1>Main page</h1>



<header>
<h1> HTML 5 & Css3</h1>
</header>


<nav>
<ul>
	<li><a href="#"><span>HTML5</span></a>
	<li><a href="#">javascript</a>
	<li><a href="#">CSS</a>
	<li><a href="#">AJAX</a>
</ul>
</nav>


<section>
	<aside id="left_aside"></aside>
	<aside id="center_aside"></aside>
	<aside id="right_aside"></aside>
</section>




<footer></footer>

</body>
</html>