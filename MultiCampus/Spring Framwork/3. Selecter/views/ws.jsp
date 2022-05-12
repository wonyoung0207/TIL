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
	height: 50px;
	background:lightyellow;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */
	

}

header > h1{

	text-align:center;
	line-height: 100px;
	float:left;
}

nav{
	width:600px;
	height: 40px;
	background:lightgray;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */
	color:white;

}


nav > ul{
	width:500px;
	margin:0 auto;

}
nav > ul > li{
	float:left;
	width:100px;
}


nav > ul > li > a{
	display: block;
	text-align: center;
	line-height: 40px;
}

nav > ul > li > a:hover{
	color:brown;
	
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
	background:lightyellow;
}

section > #center_aside{
	width:450px;
	height: 500px;
	background:white;
	margin : 0 auto;
}



img{
	width:200px;
	height: 250px;
}


section > #right_aside{
	width:50px;
	height: 500px;
	background:darkgray;
}



footer{
	width:600px;
	height: 30px;
	background:lightyellow;
	margin: 0 auto;/* 화면이 변경되어도 중간에 표시한다.  */

}

td,tr{
	text-align:center;
	height: 30px;		

}

table{
	border-collapse:collapse;
	
}

button{
	border: 3px solid #4CAF50;
	border-radius: 10px;
	background-color: white;
	color:black;
 	transition-duration: 0.4s;

}

button:hover{
	color:white;
	background-color: #4CAF50;

}

.himg{
	width:20px;
	height: 20px
}

#hdiv{
	margin:0 auto;
}

#hlogo{
	float:left;

}

#hicon{
	
}
#hicon > li{
	float:right;
	margin : 12px;
	border:0;
}

#aside_tbl{
	margin : 20px;

}

#left_aside > ul > li{
	margin : 15px;
	
}

#left_aside > ul > li > a:hover{
	color:brown;
}


#right_aside > ul > li > img{
	width:40px;
	height: 40px;
	margin: 20px 5px 20px 5px;

}

footer > ul > li{
	float: right;
	margin : 0 20px;
}





</style>

<script>
function buy(a){
	var c = confirm(1 + " 결제창으로 이동하시겠습니까?");
	
};

</script>

</head>
<body>




<header>
<div id="hdiv"><h1 id="hlogo"> Apple </h1>

<ul id="hicon">
	<li><a href="#"><img class= "himg" src="img/돋보기.png"></a>
	<li><a href="#"><img class= "himg" src="img/목록.png"></a>
	<li><a href="#"><img class= "himg" src="img/장바구니.png"></a>
	
</ul>


</div>

</header>


<nav>
<ul>
	<li><a href="#"><span>Mac</span></a>
	<li><a href="#"><span>iPad</span></a>
	<li><a href="#"><span>iPhone</span></a>
	<li><a href="#"><span>Watch</span></a>
	<li><a href="#"><span>AirPods</span></a>
</ul>
</nav>


<section>
	<aside id="left_aside">
		<ul>
			<li><a href="#"><span>home</span></a>
			<li><a href="#"><span>개요</span></a>
			<li><a href="#"><span>제품사양</span> </a>
		
		</ul>
	
	</aside>
	
	<aside id="center_aside">
		<table id="aside_tbl">
			<thead>
				<tr><th><img src="img/iphone1.png"></th><th><img src="img/iphone2.png"></th></tr>
							
			</thead>
			<tbody>
				<tr><td><span> iPhone SE </span></td><td><span> iPhone pro </span></td><tr>
				<tr><td><span> $10000 </span></td><td><span> $15000 </span></td><tr>
				<tr><td><button onclick="buy(1)"> 구입하기 </button></td><td><button onclick="buy(2)"> 구입하기 </button></td><tr>
				<tr><td><a href="#"> 더 알아보기 ></a></td><td><a href="#"> 더 알아보기 ></a></td><tr>
			
			</tbody>
		
		</table>
		
		
	</aside>
	<aside id="right_aside">
		<ul>
			<li><img src="img/imac.png">
			<li><img src="img/tablet.png">
			<li><img src="img/airpods.png">
			<li><img src="img/watch.png">
			
		
		</ul>
	
	</aside>
</section>




<footer>
<ul>
	<li><span>서비스</span>
	<li><span>계정</span>
	<li><span>비즈니스</span>

</ul>
</footer>

</body>
</html>