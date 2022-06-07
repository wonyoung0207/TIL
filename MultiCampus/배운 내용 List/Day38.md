# 38일차

------

> Cust  이용 Login 기능 구현 
>
> auto_increment 사용시 id값 가져오는 방법 
>
> 브라우저로 다국어 처리

# Login 

1. login 시 성공과 실패로 페이지 나누기 

2. login 성공시 HttpSession 객체를 이용해 유저정보 저장 

   ```java
   @RequestMapping("/loginimpl")
   public String loginimpl(Model m, String id, String pwd, HttpSession session) {
       String next = "";
       CustVO cust = null;
   
       try {
           //데이터 베이스에 있는 정보와 비교 
           // id 값이 없으면 null 이 출력된다. 
           cust = cbiz.get(id);
           if(cust != null) {
               if(cust.getPwd().equals(pwd)) {
                   session.setAttribute("cust", cust);
                   next = "loginok";
                   m.addAttribute("cust",cust);
               }else {
                   throw new Exception();
               }
           }else {
               throw new Exception();
           }
       } catch (Exception e) {
           //			e.printStackTrace();
           next="loginfail";
       }
       m.addAttribute("center", next);
       return "main";
   }
   ```

3. 저장된  session 제거 

   ```java
   @RequestMapping("/logout")
   public String logout(Model m, HttpSession session) {
       if(session != null) {
           session.invalidate();//서버에서 session을 제거한다. 
       }
   
       return "main";
   }
   ```

4. thymeleaf  에서의 if - else 문

   - th:if - th:unless 로 사용한다. 

   ```html
   <ul class="nav navbar-nav navbar-right">
       <!-- session 이 null이면 login을, session이 널이 아니면 logout을  -->
       <li th:if="${session.cust == null}">
           <a href="/login">
           <span class="glyphicon glyphicon-log-in"></span> Login</a></li>
       <li th:unless="${session.cust == null}">
           <a href="/logout"> 
           <span th:text="${session.cust.name} + '님 로그인'"></span> 
           <span class="glyphicon glyphicon-log-out"></span>Logout
           </a></li>
   </ul>
   ```

# Auto_increment 값 가져오기

- 마지막으로 insert 된 값을 이용하면 된다. 
  - SELECT last_insert_id() AS cnt -> 가장 마지막 insert된 id 값이 출력된다. 
- 오류 해결
  1. biz.insert(ProductVO) 할때 실행 안됨
     - 오류 이유 : CustMapper를 찾지 못함 
     - 해결방법 : CustMapper에 @Autowired를 해줘야 한다. 안해주게되면 찾지 못한다. 

# 다국어 처리

1. [사용 방법 및 자료](https://cafe.naver.com/2022webservice)

2. src/main/java 밑에 있는 com.multi는 내가 핸들링 하는 것이 아니다.

3. 이 패키지 안에있는 파일들은  Run As -> Spring Boot App 을 구동시켰을 경우 자동으로 실행되며 서버를 셋팅한다. 

4. 필요한 파일

   1. LocalResolve : 사용자가 어떤 브라우저의 언어,나라로  들어왔는지를 판단 . 
   2. MessageByLocal : 메시지를 뿌릴때 사용
   3. WebConfig : 어디서 message를 정할 것인지 파일 위치를 설정 

5. 사용 방법

   1. com.multi 에 필요한 3가지 파일 추가 

      1. LocalResolve : 사용자가 어떤 브라우저의 언어,나라로  들어왔는지를 판단 . 
      2. MessageByLocal : 메시지를 뿌릴때 사용
      3. WebConfig : 어디서 message를 정할 것인지 파일 위치를 설정 

   2. src/main/resource -> messages 파일 생성 -> 언어로 설정할 properties 생성 

      ```properties
      # 한국어일 경우 
      # 그냥 한국어 쓰면 자동으로 바뀜 
      # {0} 으로 파라미터를 사용할 수 있다. 
      home.welcom= \uD658\uC601\uD569\uB2C8\uB2E4. {0} {1}
      tel=010-1111-1111
      
      # 영문어일 경우 
      home.welcom = Welcom
      tel=010-2222-2222
      ```

   3. 사용하고자 할 테그에서 \<h1 th:text="#{home.welcom('hi', 'kim')}"> 추가 
