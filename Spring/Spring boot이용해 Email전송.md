# Spring boot이용해 Email전송

---

>[참고 사이트1](https://badstorage.tistory.com/38)
>
>[참고 사이트2](https://abn-abn.tistory.com/241)
>
>[참고 사이트3](https://memo-the-day.tistory.com/35)
>
>[참고 사이트4](https://aamoos.tistory.com/501)
>
>[참고 사이트5](https://velog.io/@wlgns3855/JAVA-%EC%9D%B4%EB%A9%94%EC%9D%BC-%EC%A0%84%EB%8B%AC%ED%95%98%EA%B8%B0)
>
>[Google 앱 비밀번호 만들기](https://support.google.com/accounts/answer/185833#zippy=%2C%EC%95%B1-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%EA%B0%80-%ED%95%84%EC%9A%94%ED%95%9C-%EC%9D%B4%EC%9C%A0)

## Mail 보내는 방법

1. Spring Boot 의 **JavaMail** 라이브러리 
2. Apache Commons 의 **Email** 라이브러리

## Spring Boot 의 JavaMail API

1. 정의
   1. Java에서 이메일을 송수신할 수 있도록 지원하는 표준 라이브러리이다. 
   2.  IMAP, POP3, SMTP와 같은 이메일 프로토콜을 지원한다. 
2. 특징
   1. **이메일 송신**
      1. SMTP 프로토콜을 통해 이메일을 보낼 수 있다.
   2. **이메일 수신**
      1. IMAP, POP3 프로토콜을 통해 이메일을 받을 수 있다.
   3. **확장성**
      1. 텍스트, HTML, 첨부 파일 등 다양한 이메일 형식을 지원한다. 
      2. MIME 메시지와 같은 복잡한 이메일을 보낼 수 있도록 지원한다. 
   4. **안전성**
      1. SSL/TLS를 사용한 안전한 이메일 송수신을 지원한다. 
   5. **구성 용이성:** 
      1. `application.properties` 또는 `application.yml` 파일에서 SMTP 서버 설정을 쉽게 구성할 수 있다.
   6. **Spring 통합:** 
      1. Spring의 다른 기능과 자연스럽게 통합된다. 

## Apache Commons 의 Email 

1. 정의
   1. Java 애플리케이션에서 이메일을 쉽게 보내기 위한 라이브러리이다. 
   2. 다양한 이메일 형식을 지원하며, 간단한 텍스트 이메일부터 복잡한 HTML 이메일, 첨부 파일이 포함된 이메일까지 보낼 수 있다. 
3. 특징 
   1. **단순성** 
      1. 사용하기 매우 간단하며, 단순한 이메일 전송 작업에 적합하다.
   2. **다양한 이메일 형식 지원**
      1. 텍스트, HTML, 첨부 파일이 포함된 이메일 등을 보낼 수 있다.
   3. **경량** 
      1. 비교적 작은 라이브러리로, 이메일 전송 기능만을 제공한다.
   4. **독립성** 
      1. Spring 등의 프레임워크에 종속되지 않으며, 독립적으로 사용될 수 있다.
   5. **SMTP 인증 및 TLS 지원**
      1. 보안 설정을 쉽게 할 수 있다.
      2. 하지만 SMTP 서버에 의존하여, 이메일 전송에 실패할 경우의 에러 핸들링이 필요하다는 단점이 있다. 

## SpringBoot의 JavaMail  vs  Commons 의 SimpleEmail API 

1. 결론 

   1. **간단한 이메일** 송수신 작업에는 Apache **Commons Email**과 같은 라이브러리를 사용하고, 
   2. **복잡한 이메일** 기능이 필요한 경우에는 **JavaMail API**를 사용하는 것이 좋다.

2. 차이점

   1. 확장성
      1. `JavaMailSender`는 Spring과 긴밀히 통합되어 있으며, 복잡한 이메일 전송 시 유연하고 확장성이 좋다.
      2. `SimpleEmail`은 경량이고 독립적인 유틸리티로, 단순한 이메일 전송 작업에 적합하다. 

   1. 구성
      1. `JavaMailSender`는 Spring의 구성 파일을 통해 쉽게 설정할 수 있다.
      2. `SimpleEmail`은 코드 내에서 SMTP 서버 설정을 직접 정의한다. 

   2. 기능
      1. `JavaMailSender`는 다양한 이메일 유형(텍스트, HTML, 첨부 파일 등)을 지원한다. 
      2. `SimpleEmail`은 주로 단순한 텍스트 이메일 전송에 중점을 둔다.

## 사용 방법

1. 의존성 추가 

   1. 사용하는 build 툴에 따라 의존성을 추가한다. 

   2. 예를들어, Gradle 을 사용하면 `build.gradle`에 다음과 같이 의존성을 추가한다. 

      ```js
      // mailserver 연결
      // 1. JavaMail 이용
      implementation 'org.springframework.boot:spring-boot-starter-mail'
      
      // Commons의 Email 이용
      implementation 'org.apache.commons:commons-email:1.5'
      implementation 'net.jodah:expiringmap:0.5.10'
      ```

2. 사용할 SMTP 계정 연결 

   1. SMTP 를 제공하는 플랫폼의 Email 서버를 이용하는 방식이다. 

   2. 따라서 email.host 를 해당 플랫폼의 smtp 와 연결해야한다. 

   3. 여기서는 Google 의 SMTP 와 gradle 을 사용하기 때문에 `application.properties`설정으로 smtp 와 계정 설정을 진행한다. 

      ```properties
      # Gmail SMTP 서버 주소
      email.host=smtp.gmail.com
      email.port=587
      email.username=user@gmail.com
      # google 계정 App Password 
      email.password=[google 계정 App Password]
      email.from=[user@gmail.com]
      
      # # Gmail SMTP 서버 주소
      # spring.mail.host=smtp.gmail.com
      # spring.mail.port=587
      # spring.mail.username=user@gmail.com
      # spring.mail.password=userPassword
      # # TLS 연결 활성화 여부
      # spring.mail.properties.mail.smtp.starttls.enable=true 
      # spring.mail.properties.mail.smtp.starttls.required=true
      # # SMTP 인증 사용 여부
      # spring.mail.properties.mail.smtp.auth=true
      ```

3. 구글 SMTP 설정

   1. 구글의 SMTP 를 사용하기 위해선 `앱 비밀번호` 를 사용하는 것이 좋다. 
   2. 왜냐하면 사용자 Email 의 비밀번호 노출을 피할 수 있기 때문이다. 
      1. 구글의 2단계 인증을 사용하는 경우에만 `앱 비밀번호` 를 사용해 사용자의 Password를 숨길 수 있다. 

   3. 설정 방법은 [Google 계정 도움말](https://support.google.com/accounts/answer/185833#zippy=%2C%EC%95%B1-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%EA%B0%80-%ED%95%84%EC%9A%94%ED%95%9C-%EC%9D%B4%EC%9C%A0) 에서 방법을 찾을 수 있다. 
      1. `구글 계정 -> 2단계 인증 등록 -> 앱 비밀번호 생성 -> 생성된 비밀번호 email.password에 적용`
      2. [google 앱 비밀번호 생성](https://myaccount.google.com/apppasswords)

4. noreply 적용

   1. 발신자의 Email을 noreply 로 변경할 수 있다. 

      1. noreply란, 이메일의 송신만 할 뿐 재전송에 대한 답변을 안한다는 약속같은 것이다. 

   2. 하지만 Google 을 사용한다면 보안정책으로 인해 noreply@gmail.com 처럼 수신자의 이메일을 변경할 수 없다. 

      1. Gmail의 보안 정책에 따라 이메일 발신자의 주소가 항상 인증된 사용자의 이메일 주소로 설정되기 때문임

      ```java
      htmlEmail.setFrom("noreply@example.com", "NoReply");
      ```

5. EmailService 제작 

   1. Controller 다음에 오는 Service를 통해 Email을 전송하게 된다. 

   2. 이때, 사용하는 라이브러리에 따라 **sendEmail**() 메소드의 내부 내용이 달라진다. 

   3. 만약 `Commons Email` 인 경우 

      ```java
      @Service("EmailService")
      public class EmailService {
          public Result sendEmail(String userId, String userEmail) {
      
          HtmlEmail  htmlEmail = new HtmlEmail(); // Email 객채 생성 
          htmlEmail.setHostName(hostName);
          htmlEmail.setSmtpPort(Integer.parseInt(smtpPort));
          htmlEmail.setAuthenticator(new DefaultAuthenticator(smtpUserName, smtpPassword));
          htmlEmail.setStartTLSEnabled(true);
          htmlEmail.setCharset("UTF-8"); // 이메일 인코딩 설정
          htmlEmail.setFrom(smtpUserName);
          htmlEmail.addTo(userEmail);
          htmlEmail.setSubject(emailTitle);
                
          // String emailBody = "<!DOCTYPE html> <html> <body> <div> hello </div> </body> </html>";
          String emailBody = formatEmailBody(); // HTML 생성 ( 내부 내용 설정 )
          htmlEmail.setHtmlMsg(emailBody); // HTML 추가 
          htmlEmail.send();
      
          }
      }
      ```

6. RESTful API 를 통해 Controller 호출

   1. Email 전송 Controller 호출

      ```java
      @RestController
      public class EmailController {
          private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
      
          // email 서비스 
          @Autowired
          private EmailService emailService;
      
          /**
           * 이메일 발송
           * @param params
           * @return
           */
          @PostMapping("/email/sendEmail")
          public ResponseEntity<ApiResult> sendEmail(@RequestBody Map<String, Object> params){
              System.out.println("controller .... params : " + params.get("userId") + "######################################################");
              Result result = emailService.sendEmail( (String) params.get("userId"),(String) params.get("userEmail"));
      
              return ApiResult.builder()
                  .ok()
                  .result(result)
                  .response();
                      
          }
      }
      ```

---

## HTML 스타일 적용 안되는 문제 발생 

1. 이유 

   1. 이메일 클라이언트에서 보안 및 호환성 문제로 인해 외부 CSS 나 `<style>` 태그 내의 CSS 를 무시하게 된다. 

2. 해결 방법 

   1. 더 잘 지원되는 인라인 태그 스타일을 적용해서 해결했다. 

3. 예시

   ```html
   <html>
   <head>
   </head>
   <body>
       <div style="background: #FFFFFF; padding: 3rem 4rem; box-sizing: border-box; max-width: 600px; border: 1px solid #ccc; position: relative; font: 15px 'NotoKrL', sans-serif; color: black;">
           <h1 style="text-align: center; color: black;">인증 번호 안내</h1>
           <p style="color: black;">안녕하세요,<br>
               Traffic Digital Twin 을 이용해 주셔서 감사합니다.<br>
               유효시간 {{expirationTime}}분 안에 아래 <span style="color: #f47e1f; font-weight: bold; margin: 2rem 0;">인증번호</span>를 화면에 입력하시면 다음 단계로 진행할 수 있습니다.<br>
               감사합니다.</p>
           <div style="color: #f47e1f; font-weight: bold; margin: 2rem 0; text-align: center;">[ {{verificationCode}} ]</div>
           <h3 style="color: black;">요청 정보</h3>
           <table style="width: 100%; border-collapse: collapse; color: black;">
               <tr>
                   <td style="width: 50%; text-align: left; border-top: 2px solid grey;">
                       <p>요청 일시:</p>
                   </td>
                   <td style="width: 50%; text-align: right; border-top: 2px solid grey;">
                       <p>{{requestDt}}</p>
                   </td>
               </tr>
               <tr>
                   <td style="width: 50%; text-align: left; border-top: 2px solid grey;">
                       <p>요청 ID:</p>
                   </td>
                   <td style="width: 50%; text-align: right; border-top: 2px solid grey;">
                       <p>{{requestId}}</p>
                   </td>
               </tr>
           </table>
       </div>
   </body>
   ```

   
