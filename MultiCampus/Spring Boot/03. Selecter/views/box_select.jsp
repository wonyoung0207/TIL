<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Koulen&family=Oleo+Script+Swash+Caps&display=swap" rel="stylesheet">
<style>
	*{
		margin:0;
		padding:0;
		
	}
	
	div{
	
		width:200px;
		height: 200px;
		border: 3px solid red;
		border-radius : 10px 50px;
		margin:150px;/* 상하좌우 모두 margin으로 250을 지정한다.  */
		padding:10px;
	}
	
	p{
		display: none;/* 영역까지 없애서 화면에서 안보이게 만든다.  */
		/* visiblity: hidden;/* 영역은 유지하면서 안보이게 만든다.  */ */
		/* display:inline;/* 가로 세로를 설정할 수 없다.  */ */
		/* display:inline-block;/* 가로 세로를 설정할 수 있다.  */ */
		
	
	}
	
	span{
		font-family: 'Oleo Script Swash Caps', cursive;/* 만약 앞에께 없으면 디폴트로 cursive를 적용한다.  */
	}


</style>

</head>
<body>

<h1>box_select page</h1>

<span>span1</span>
<p>Paragraph</p>
<span>span2</span>

<div> <span>Text </span></div>



</body>
</html>