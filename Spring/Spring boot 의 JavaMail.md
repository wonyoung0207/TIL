# Spring Boot 의 JavaMail API

---

>

## JavaMail 라이브러리 

### 정의 

1. Java에서 이메일을 송수신할 수 있도록 지원하는 표준 라이브러리이다. 
2.  IMAP, POP3, SMTP와 같은 이메일 프로토콜을 지원한다. 

### 특징

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

### 주요 클래스 

1. **Session**
   1. 메일 세션을 생성하고, SMTP 서버와 같은 메일 설정을 포함
2. **Message**
   1. 이메일 메시지를 나타내는 클래스
3. **MimeMessage**
   1. MIME 형식의 이메일 메시지를 나타내는 클래스
4. **Transport**
   1. 메시지를 전송하는 클래스
5. **Store**
   1. 이메일을 저장하고 검색하는 클래스
6. **Folder**
   1. 이메일 폴더를 나타내는 클래스

##### 의존성 추가 

```js
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-mail'
}
```

##### application.properties 추가 

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

##### 예시

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Text 만 보내는 경우 
    @PostMapping("/email/sendSimple")
    public String sendSimpleEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String content) {
        emailService.sendSimpleEmail(to, subject, content); 
        return "Simple email sent successfully!";
    }

    // HTML 로 형식 같춰 보내는 경우 
    @PostMapping("/email/sendHtml")
    public String sendHtmlEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String htmlContent) {
        emailService.sendHtmlEmail(to, subject, htmlContent);
        return "HTML email sent successfully!";
    }
}

```

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Text 만 보내는 경우 
    public void sendSimpleEmail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // 인코딩 설정 필요 없음
            helper.setFrom("your-email@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, false); // content 만 보냄
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // HTML 로 형식 같춰 보내는 경우 
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // 인코딩 설정 필요
            helper.setFrom("your-email@gmail.com");
            helper.setTo(to); // 보낼곳
            helper.setSubject(subject); // 제목
            helper.setText(htmlContent, true); // html 파일 자체를 보냄 
            mailSender.send(message); // 전송
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

```
