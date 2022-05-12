<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.box{
	width:100px;
	height: 100px;
	/* float:left; */
	position:absolute;/* 위치를 절대적으로 0.0 을 기준으로 설정한다.  */
	opacity: 0.5;/* 투명도를 나타낸다. 0~1 사이값 설정가능  */
}

.box:nth-child(1){
	background:red;
	left:10px;
	top:10px;
	z-index:100 /* 큰 숫자가 먼저 나온다.  */
	
}
.box:nth-child(2){
	background:blue;
	left:50px;
	top:50px;
	z-index:10 
	
}
.box:nth-child(3){
	background:green;
	left:90px;
	top:90px;
	z-index:20 
	
}

</style>

</head>
<body>


<div class="box"></div>
<div class="box"></div>
<div class="box"></div>





</body>
</html>