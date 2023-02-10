# Thymeleaf 개념정리

---

>[나의 Thymeleaf 함수 정리](https://github.com/wonyoung0207/TIL/blob/master/MultiCampus/%EB%B0%B0%EC%9A%B4%20%EB%82%B4%EC%9A%A9%20List/Thymeleaf%20Function.md)

## 동적 웹페이지 만드는 방식 

1. JSP
2. Thymeleaf

## JSP 파일 호출

   - jsp파일은  url 에서 바로 호출이 불가능하다. 
   - 서버가 controller에서 알맞은 jsp 파일을 찾아 html로 바꿔 전달해주는형식이기 때문이다. \

### Thymeleaf 파일 호출

- Thymeleaf 파일은 .html 파일로 제작되어 서버를 거치지 않고도 그냥 html 파일로 인식된다. 
- 만약 thymeleaf에서 호출했던 파일이 없을 경우 th 파일을 무시하고 진행된다. 

---

## Thymeleaf

### 정의

- 템플릿 엔진이자, 서버사이드 자바 템플릿의 한 종류
- thymeleaf 서버의 특징은 jsp파일을 사용하지 않는다는 것이다. 

### 템플릿 엔진

- 템플릿 엔진이란 동적 컨텐츠를 생성하는 방법이다.
- 템플릿 양식과 특정 데이터 모델(Model) 에 따른 입력 자료를 결합하여 결과 문서를 출력하는 소프트웨어를 말하며 view code(html)와 data logic code(DB connection)를 **분리**해주는 기능을 한다.
- 스프링 MVC에서 주로 동적인 view를 만드는데 사용

### 특징

1. 네츄럴 템플릿
   - thymeleaf로 작성한 파일은 확장자도 .HTML이고 웹 브라우저에서 직접 파일을 열어도 내용을 확인할 수 있다. 
   - 물론, 이 경우 동적인 결과 렌더링은 되지 않지만 HTML 마크업 언어가 어떻게 되는지 확인할 수 있다. 
2. 수정 용이 
   - view를 수정하고 싶을 때 렌더링 없이 페이지 url에 직접 접근해 소스의 css에서 색상을 수정하고 F5를 누르면 뷰를 쉽게 바꿔볼 수 있다.
   - 반면 JSP는 서버를 키고, 수정을 원하는 페이지까지 직접 클릭을 여러번 해 들어가야 한다. 
3. 파일 형태
   - jsp는 파일의 형태가 .jsp라서 오직 서버를 통해서 JSP가 렌더링 되고 HTML 응답 결과를 받아야 화면을 확인할 수 있다.
   - 반면에, Thymeleaf로 작성된 파일은 HTML을 그대로 웹 브라우저에서 열어도 정상적인 HTML 결과를 확인할 수 있다.
4. 서버 사이드 HTML 렌더링(SSR)
   - SSR 이란
     - 서버가 클라이언트에게 HTML 파일을 전달할 때, 사용자가 볼 수 있는 **완전한 html을 전송한다는 뜻**이다.
   - Thymeleaf는 백엔드 서버(Spring Application)에서 HTML을 동적으로 렌더링 하는 용도로 사용
   - 페이지가 어느정도 정적이고 빠른 생산성이 필요한 경우 백엔드 개발자가 개발해야하는 일이 생기는데 이 경우 타임리프는 좋은 선택지이다.
5. 스프링 통합 지원
   - 타임리프는 스프링과 자연스럽게 통합되고, 스프링의 다양한 기능을 편리하게 사용할 수 있도록 지원힌다.
   - 예를들어 Model로 던진 객체를 자유자제로 사용할 수 있다. 

### SSR 과 CSR 차이점 

1. 렌더링
   - 브라우저가 사용자가 보기 편하게 표시하는 과정 
2. SSR ( 서버 사이드 렌더링)
   - 서버측에서 페이지의 내용을 다 그려서 브라우저로 던저주는 것 
   - 단점
     - 페이지 이동시 화면이 리로드 되는 과정이 필요 
     - 서버에 매번 요청을 해 속도가 느리고 서버가 부하됨
   - 장점
     - 서버에서 그리기 때문에 페이지 그리는 시간 단축 
     - "빠른 페이지 렌더링" 과 검색엔진 최적화에 많이 사용된다. 
3. CSR ( 클라이언트 사이드 렌더링)
   - 비어있는 HTML 파일을 받아 페이지의 내용을 브라우저에서 그린다.
   - 단점
     - 초기 화면 로드가 느리다.
     - 처음에 모든 HTML과 Static 파일을 로드해야함.
   - 장점
     - 로드되고나면 사이트 내에서 돌아다닐 때 리로드 과정이 없어지므로 사용성이 좋아진다. 
     - 변화한 부분만 재 렌더링을 해줘 사용성이 좋아진다. 
     - 서버에 데이터를 요청하는 횟수가 적음
4. 정리
   - 검색엔진이 필요하다면 SSR을 사용하고, 필요없다면 CSR 방식을 사용하는것이 좋다. 
   - Thymeleaf는 SSR 이다. 

### 사용법

- 타임리프는 문서 최상단에 다음과 같은 코드를 넣어서 사용할 수 있다. 

```html
<html xmlns:th="http://www.thymeleaf.org">
```

#### 1. 의존성 추가

-  pom.xml 에 다음 코드 추가 

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

#### 2. th 네임스페이스 추가 

- thymeleaf 파일 만들기
- .html 파일에 \<html xmlns:th="http://www.thymeleaf.org"> 을 추가한다. 

#### 3. 경로 설정 

1. controller가 String 형을 리턴하면 **templates 파일 바로 밑에있는 html파일**을 찾는다. 
2. 리턴전에 Model 객체에 값을 설정할 수 있다.
   1.  Model에 담긴 객체를 이용해서 main.html의 section태그안에 들어갈 파일명을 자유롭게 변경할 수 있다. 
   2.  .html 을 쓰지 않아도 thymeleaf 서버에서는 자동으로 파일명.html 로 찾아서 보여준다. 

```java
// Controller.java

// 앞에서 한것과 다른점은 jsp파일을 사용하지 않는다는 것이다. 

@RequestMapping("/")
public String main(Model m) {
    //resource - templates 파일의 html파일에서 main을 호출한다. 
    m.addAttribute("center","center");
    return "main";

}

@RequestMapping("/home")
public String home(Model m) {
    m.addAttribute("center","home");

    return "main";

}

@RequestMapping("/home1")
public String home1(Model m) {
    m.addAttribute("center", "home");
    m.addAttribute("scenter", "home/home1");
	//home.html 안에 있는 section의 scenter에 home/home1 을 넣어준다. 만약 scenter가 없다면 기본설정값인 home/homeMain 을 들어가도록 설계한다. 
    return "main";
}
```

```html
<!-- home.html -->
<html xmlns:th="http://www.thymeleaf.org">
    
<aside id="left_aside">
	<ul>
		<li><a href="home1">home1</a>
		<li><a href="home2">home2</a>
		<li><a href="home3">home3</a>
	</ul>

</aside>
<aside id="right_aside" th:include="${scenter} ? ${scenter} : @{home/homeMain}"></aside>

```

```html
<!-- main.html -->

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/global.css">
</head>
<body>
<h1>Main page</h1>
<header>
<h1><a href="/"> Shopping Mall </a></h1>
</header>
<nav>
<ul>
	<li><a href="home">home</a>
	<li><a href="food">food</a>
	<li><a href="wear">wear</a>
	<li><a href="media">Media</a>
</ul>
</nav>
<!-- center가 있으면 ${center}.html를, 없으면 center.html를 출력. 
이때 ${center} 는 controller에서 Model에 설정한 객체의 변수 값 이다. 
-->
   
<section th:include="${center} ? ${center} : center">
</section>
<footer></footer>
</body>
</html>


```

#### 4. **동작방법**

1. 클라이언트가 서버에 URL 요청
2. 서버에서 thymeleaf 에서 main.html 파일을 찾음
3. 찾은 thymeleaf 로된 main.html 파일을 html파일로 변경
4. 변경된 html 파일을 사용자 화면에 뿌려줌.
   - **동기**일 경우 : 요청시 무조건 서버에서 html 을 새로 만들어서 사용자에게 뿌려주는 것임.
   - **비동기**일 경우 : 내려받은 html파일에서 특정 부분만 변경되도록 할 수 있다. 

<img src="../MultiCampus\images\thymeleaf.png" >