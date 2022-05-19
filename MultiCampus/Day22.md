# 22일차

------

> html 태그의 종류와 구조에 대해 학습한다. 
>
> Spring 프레임워크의 Controller 와 JSP  구조에 대해 연습하고 학습한다.

## 1. Spring boot 

   + Spring boot는 서버측에 있는 것으로, ComcatServer를 탑재하고 있다. 

## 2. 이클립스 HTML  UTF-8 설정 방법

   1. WIndow -> Preparence -> Web -> HTML Files -> Encoding 을 UTF-8로 변경

## 3. Bootstrap

   - 웹 사이트를 쉽게 만들 수 있게 도와주는 HTML, CSS, JS 프레임워크이다. 
   - 다양한 기능을 제공하여 사용자가 쉽게 웹사이트를 제작, 유지, 보수할 수 있도록 도와준다. 

## 4. 용어

   1. 태그
      + HTML페이지를 구성하는 각 부품을 요소라고한다. 이러한 요소를 만들 떄 사용되는 것이 태그이다. 
      + 따라서 ''요소 = 태그'' 이다.
      + <> 를 사용하는 것을 태그라고 한다. 
   2. Element 
      - 태그와 내용을 합친것을 말한다. 
   3. attribute
      - 태그에 추가 정보를 부여할 떄 사용하는 것 
      - img 나 a 태그만 사용한다. 

## 5. HTML 페이지 구조

   1. \<DOCTYPE html>
      - 웹 브라우저에 HTML5 문서라는 것을 알리기 위해 반드시 첫 행에 나와야 한다. 
   2. \<html>
      + 모든 html 페이지의 기본 요소로, 모든 태그는 이 html 태그 내부에 작성된다. 
   3. \<head>
      - body 태그에 필요한 스타일시트와 자바스크립트를 제공한다. 
   4. \<title>
      - 웹 브라우저에 표시하는 제목을 지정 한다. 
   5. \<body>
      - 사용자에게 실제로 보이는 부분을 작성하는 곳 이다. 

## 6. 특수기호

   1. 띄어쓰기
      - \&nbsp;
   2. 괄호
      - \&lt, \&gt
   3. &
      - \&amp;

## 7. 빈 링크

   - a태그를 사용하지 않을 때는 href에 # 로 막아줘야 한다. 
   - \<a href="#">

## 8. 태그 종류

   1. ### 목록 태그

      1. UL

         - 순서가 없는 목록

      2. OL

         - 순서가 있는 목록 

      3. ```html
         <h2>ul tag : 순서 없는 태그 </h2>
         <ul>
         	<li><a href="#">First</a>
         	<li id='ulli'><a href="#">Second</a>
         	<li><a href="#">Third</a>
         </ul>
         
         <hr>
         <h2>ol tag : 순서 있는 태그 </h2>
         <ol>
         	<li><a href="#">First</a>
         	<li><a href="#">Second</a>
         	<li id='olli'><a href="#">Third</a>
         </ol>
         ```

   2. ### 테이블

      1. ```html
         <table border=1 width="300px">
         	<thead>
         		<tr><th>id</th><th>name</th><th>age</th></tr>
         	</thead>
         	<tbody>
         		<tr><td>id01</td><td>id01Name</td><td>20</td></tr>
         		<tr><td>id02</td><td>id02Name</td><td>10</td></tr>
         		<tr><td rowspan="2">id03</td><td colspan="2">id03Name</td></tr>
         		<tr><td>id04Name</td><td>40</td></tr>
         	</tbody>
         </table>
         <!-- colspan과 rowspan을 이용해서 행 열을 합할 수 있다.   -->
         ```

   3. ### 미디어 태그 

      1. img

         - ```html
           <a href="#"><img src="img/naver.png" alt="이미지 없는 경우 출력 "></a>
           <img src="http://via.placeholder.com/300x200">
           ```

      2. audio

         - 거의 사용하지 않는다. 

      3. video

         - ```html
           <video src="mv/movie.mp4" controls="controls"></video>
           ```

   4. ### 입력양식(from) 태그

      1. 항상 form으로 시작해서 form 으로 끝난다. 

      2. 종류

         1. form
         2. input
            1. text
               - 가장 자주 사용한다. 텍스트를 입력할 수 있게 한다. 
            2. submit
               - 데이터 보낼때 사용
            3. radio
               - 하나만 선택된다. 
            4. checkbox
               - 여러개를 선택할 수 있따. 
            5. date
               - 달력을 표시한다. 
            6. number
               - 숫자만 입력되게 한다. 
            7. password
               - 비밀번호를 입력할때 사용한다. 

         3. textarea
         4. select option
         5. fieldset legend

      3. ```html
         <form action="register" mathod="get">
         <!-- 입력한 값을 서버의 register에게 get방식으로 전송한다.  -->
         <!-- get = 속도는 빠르지만 url에 보내는 정보가 표시된다. 
         post = 속도는 느리지만 url에 보내는 정보가 표시되지 않는다.  -->
         
         	<fieldset>
         		<legend> 회원가입 </legend>
                 <label for="iid">ID</label>
         		<!--label의 효과는 ID 문자를 클릭시 input에 자동으로 포커스가 이동한다.  -->
         		<input id="iid" type="text" name="id"><br> 
         		PWD <input type="text" name="pwd"><br> 
         		age <input type="number" name="age"><br> 
         		BIRTH <input type="date" name="birth"><br> 
         		GENDER<br> 
         		<input type="radio" name="gender" value="f"> Female<br>
         		<input type="radio" name="gender" value="m"> Male<br>
         		Hobby<br>
         		<input type="checkbox" name="hobby" value="s"> Study <br>
         		<input type="checkbox" name="hobby" value="t"> Train <br>
         		<input type="checkbox" name="hobby" value="e"> Eat <br>
         		
         		Car<br>
         		<select name="car">
         			<option value="k1"> k1 </option>
         			<option value="k2"> k2 </option>
         			<option value="k3"> k3 </option>
         		</select><br>
         		
         		<textarea name="resume" rows="10" cols="100">Textarea</textarea><br>
         		<input type="hidden" name="loginid" value="leejan"><br>
         		<!-- 화면에는 보이지 않지만 서버로 전송할 수있다.  -->
         		<input type="range" name="range" size="10" step="1"> <br>
         		<!-- 게이지 형태로 숫자를 보낼 수 있다.  -->
         		
         	</fieldset>
         	<input type="submit" value="register2">
         	<input type="reset" value="RESET">
         </form>
         
         ```

## 9. Spring 동작

   1. 클라이언트가 서버 호출

      - url 에 서버 주소 호출
      - 127.0.0.1/
        - 내 컴퓨터의 로컬 호스트를 호출한다. 

   2. 서버가 **Controller** 에서 해당 url에 맞는 RequestMapping을 찾아 호출한다. 

      - ```java
        @RequestMapping("/")
        //어떤 요청이 들어왔는지를 기입한다.
        //root로 들어오면 실행되는 곳 
        public ModelAndView main(ModelAndView mv) {
            mv.addObject("w","Welcome!!");
            //mv 객체에 w 라는 객체를 생성하여 welcome 을 넣는다. 
            mv.setViewName("main");
            //mv 객체에 뷰(화면내용) 속성을 main으로 설정한다. 
            return mv;
            //mv에 설정되어있는 view가 호출된다. 
        }
        
        @RequestMapping("/product_view") //127.0.0.1/product_view 로 들어오면 호출됨
        public ModelAndView product_view(ModelAndView mv) {
            mv.setViewName("product_view");
            return mv;
        }
        ```

   3. **ModelAndView** 의 객체로 **RequestMapping**에서 일을 처리하고 ModelAndView의 객체에 설정되어있는 **view.jsp**를 호출하고 jsp 파일을 **html 로 변환**하여 클라이언트에게 보여준다. 

      - 서버는 main.jsp를 html 파일로 변경하여 클라이언트에게 보여준다. 

   ###  즉, url 로 클라이언트가 접속하면 서버가 Controller에서 해당 url과 일치하는 RequestMapping을 호출한다. 호출한 RequestMapping 에서 일을 처리하고 그다음 호출할 View를 클라이언트에게 보여준다. 이때 보여주는 View는 JSP 파일이 아닌 HTML 파일이다. 

