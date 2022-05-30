<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.big_bt{
	width:150px;
	height: 70px;
	background : #FF6A00;
	border: 10px solid #FFFFFF;
	border-radius: 30px;
	box-shadow: 5px 5px 5px #A9A9A9;
	/* 가로, 세로 5씩, 그림자를 5px, 색상을 #A9로  */

}

.big_bt > a{
	text-decoration:none;
	display:block;
	text-align:center;
	line-height: 70px;
	/* block 형으로 바꿔도 높이는 글자높이 만큼만 된다. 따라서 따로 높이를 조절해 줘야한다.  */
	font-weight:bold;
	font-size: 2em;
	
}

.big_bt > a:hover{

	color:yellow;
}

.big_bt:hover{

	background:blue;
	border: 10px solid black;
}
</style>

</head>
<body>

<h1>button_select page</h1>

<div class="big_bt">
	<a href="#">Click</a>

</div>





</body>
</html>