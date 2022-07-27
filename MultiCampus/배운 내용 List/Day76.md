# Day75

---

> 쿠폰기능 
>
> 로그인 후 이전 페이지 기억 

# Final Project

>Ticket 구매 - javascript에서 split사용 
>
>google OAuth 로그인 

- javascript에서 split을 사용한다. 

  ```javascript
  coupon_cid = coupon_id.split(' ',2); // 띄어쓰기를 기준으로 2개까지 스플릿 한다. 
  $("#selectcid").val(coupon_cid[1]);//coupon_cid에는 배열로 값이 저장된다. 따라서 index를 적어주면 split한 값을 꺼낼 수 있다. 
  ```

## OAuth 로그인 

- [유튜브 강의-나도코딩](https://opentutorials.org/course/2473/16571)

### Google API 콘솔 이용 방법 

- [google api 콘솔](https://console.cloud.google.com/apis/)
- [참고 유튜브 강의](https://www.youtube.com/watch?v=9ui2i-SgBpk)
- [내용 참고 ](https://deeplify.dev/back-end/spring/oauth2-social-login) : 정리 잘되어있음 

1. 해당 사이트에 들어가 먼저 프로젝트를 등록해야한다. 

2. 프로젝트 등록 후 OAuth 동의 화면 선택 

   - 외부 체크 
   - 애플리케이션 이름 설정 - 저장

3. 사용자 인증정보 - Oauth 클라이언트 ID만들기 

   - 애플리케이션 유형 - 웹 애플리케이션 
   - 승인된 리디렉션 URI설정
     - https://localhost:8080/login/oauth2/code/google
     - 리디렉션 URI는 google server 쪽에서 인증이 되었다는 code를 보내주는데 이때 client에서는 이 code와 client-id, seretkey를 함께 google server로 넘긴다.
     - 따라서 리디렉션 URI 는 인증 되었다는 code를 받을 URL 이다. .
   - 리디렉션 URI의 주소는 앞에 https://localhost:8080 부분 까지는 바꿀 수 있지만 그 뒤의 주소 "/login/oauth2/code/google" 는 바꿀 수 없다. 
     - 해당 주소는 우리 Server에서 컨트롤 하지 않고 google library에서 컨트롤 한다. 

4. google 라이브러리 설치 -> 10분 42초

   - 사용할 프로젝트 - pom.xml 파일에 해당 내용 추가 

   ```xml
   <dependency> 
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-oauth2-client</artifactId>
   </dependency>
   ```

5. application.propertys 설정

   ```properties
     security:
       oauth2.client:
         registration:
           google:
             clientId: '{구글 client-id}'
             clientSecret: '{구글 client-secret}'
             scope:
               - email
               - profile
           facebook:
             clientId: '{페이스북 client-id}'
             clientSecret: '{페이스북 client-secret}'
             scope:
               - email
               - public_profile
           naver:
             clientId: '{네이버 client-id}'
             clientSecret: '{네이버 client-secret}'
             clientAuthenticationMethod: post
             authorizationGrantType: authorization_code
             redirectUri: "{baseUrl}/{action}/oauth2/code/{registrationId}"
             scope:
               - nickname
               - email
               - profile_image
             clientName: Naver
           kakao:
             clientId: '{카카오 client-id}'
             clientSecret: '{카카오 client-secret}'
             clientAuthenticationMethod: post
             authorizationGrantType: authorization_code
             redirectUri: "{baseUrl}/{action}/oauth2/code/{registrationId}"
             scope:
               - profile_nickname
               - profile_image
               - account_email
             clientName: Kakao
   ```

   

6. 구글 로그인 

   - form 을 이용해서 구글 로그인 진행 

   ```html
   <!-- /oauth2/authorization/google => 이 주소는 구글 라이브러리에 설정되어 있어 고정된 주소이다.  -->
   <div class="container unauthenticated">
     <div>
       With GitHub: <a href="/oauth2/authorization/github">click here</a>
     </div>
     <div>
       With Google: <a href="/oauth2/authorization/google">click here</a>
     </div>
   </div>
   ```

7. src/main/java 밑에 있는 config/SecurityConfig.java 에 다음 내용 추가 

   - ```java
     .and()
     .oauth2Login()
     .loginPage("/loginForm");//구글 로그인이 완료된 뒤의 후처리가 필요 
     ```

### Redirect url

```
https://accounts.google.com/o/oauth2/auth?
  client_id=[Your Client ID]&
  redirect_uri=http://localhost:8080/redirectCode&
  scope=https://www.googleapis.com/auth/indexing&
  response_type=code
```

- client id : 애플리케이션의 고유 ID
- redirect uri : 사용자의 확인이 완료되면 리다이렉트 되어 돌아올 uri
- scope : 구글 서버에 저장된 데이터 중 사용자의 어떤 정보에 접근할 것인가
