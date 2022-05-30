<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="user-scalable=no, initial-scale=1,maximum-scale=1">
<link ref="stylesheet" href="css/global.css">

<style>
	@media screen and (max-width:767px){/* 화면 크기가 767 보다 작은경우 표시 */
		body{
			background: red;
		}
	}
	
	@media screen and (min-width:768px) and (max-width:959px){/* 768~959 사이면 실행 */
		body{
			background: green;
		}
	}
	
	@media screen and (min-width:960px){/* 960 이상 화면이면 실행 */
		body{
			background: blue;
		}

	}
	
</style>

</head>
<body>

<h1>Main page</h1>
	<p>현대차, 바이든 방한 때 70억불 전기차공장 대미 투자 발표</p>
	<span>현대자동차가 조 바이든 미국 대통령의 한국 방문 기간 미국 조지아주에 70억 달러 규모의 전기차 공장을 건립하는 계획을 발표할 예정인 것으로 알려졌습니다.</span>


</body>
</html>