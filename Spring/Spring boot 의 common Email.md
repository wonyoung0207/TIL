# Apache Commons 의 Email 

---

>

## Apache Commons 의 Email 

### 정의

1. Java 애플리케이션에서 이메일을 쉽게 보내기 위한 라이브러리이다. 
2. 다양한 이메일 형식을 지원하며, 간단한 텍스트 이메일부터 복잡한 HTML 이메일, 첨부 파일이 포함된 이메일까지 보낼 수 있다. 

### 객체 종류 ( 보낼 수 있는 형태 종류 )

1. **SimpleEmail**
   1. 단순한 텍스트 이메일을 보내기 위한 클래스
2. **HtmlEmail**
   1. HTML 콘텐츠가 포함된 이메일을 보내기 위한 클래스
3. **MultiPartEmail** 
   1. 첨부 파일이 포함된 이메일을 보내기 위한 클래스
4. **EmailAttachment**
   1. 첨부 파일을 다루기 위한 클래스

### 특징 

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

### 사용 예제 

##### 의존성 추가 

```js
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.apache.commons:commons-email:1.5'
    // 다른 필요한 의존성들...
}
```

##### application.properties 설정

```properties
email.host=smtp.gmail.com
email.port=587
email.username=your-email@gmail.com
email.password=your-password
email.from=your-email@gmail.com
```

##### Serveice 

```java
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    // application.properties의 email 설정 정보들
    @Value("${email.host}")
    private String hostName;

    @Value("${email.port}")
    private int smtpPort;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.from}")
    private String fromEmail;

    // SimpleEmail = 간단한 정보만 보낼 경우 사용
    public void sendSimpleEmail(String to, String subject, String text) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(hostName); // 어디의 MailServer 를 사용할 것인지
        email.setSmtpPort(smtpPort); // SMTP 서버 포트 
        email.setAuthentication(username, password); // 이메일 인증 여부 
        email.setStartTLSEnabled(true); // TLS 사용 여부 
        email.setFrom(fromEmail); // 보낸 사람 명 
        email.setSubject(subject); // 제목
        email.setMsg(text); // 보낼 메인 메시지
        email.addTo(to); // 보내는곳
        email.send();
    }
    
    // HtmlEmail = html 파일의 형식으로 보내는 경우 사용 ( 다양한 스타일 적용 가능 )
    public void sendHtmlEmail(String to, String subject, String htmlMessage) {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName("smtp.example.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("username", "password"));
            email.setFrom("sender@example.com");
            email.setSubject(subject); // 제목
            email.setHtmlMsg(htmlMessage); // 보낼 메인 메시지 ( html 파일이 들어감 )
            email.addTo(to);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    
    // MultiPartEmail = 첨부파일을 포함한 이메일을 보내는 경우 사용 
    public void sendEmailWithAttachment(String to, String subject, String message, String attachmentPath) {
        MultiPartEmail email = new MultiPartEmail();
        try {
            email.setHostName("smtp.example.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("username", "password"));
            email.setFrom("sender@example.com");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);

            // 첨부 파일 추가
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(attachmentPath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    
}
```

##### Controller 

```java
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        try {
            emailService.sendSimpleEmail(to, subject, text);
            return "Email sent successfully";
        } catch (EmailException e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}
```

