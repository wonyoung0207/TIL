# Day77

---

> Spring Boot Security 설정

# Final Project

>Spring Boot Security 설정
>

## OAuth 로그인 

- [유튜브 강의-나도코딩](https://opentutorials.org/course/2473/16571)

### Spring Boot Security 설정

- 사용방법

  1. Maven Dependency 추가 

     ```xml
     <dependency>
     	<groupId>org.springframework.boot</groupId>
     	<artifactId>spring-boot-starter-security</artifactId>
     </dependency>
     ```

  2. application.properties 에서 security user name, password 설정

     ```properties
     spring.security.user.name = user
     spring.security.user.password = 1234
     spring.seucrity.user.roles = USER, ADMIN
     ```

