# Spring과 Spring boot 차이점

---

## spring

### 정의

- 스프링 프레임워크(Spring Framework)는 **자바 플랫폼**을 위한 **경량형 오픈소스 애플리케이션 프레임워크**로서 간단히 스프링(Spring)이라고도 불린다.
- 따라서 Spring은 POJO 방식을 기반으로 한 웹 프레임워크이고, **IoC와 DI, AOP** 등 Spring의 주요 기술을 활용해 POJO 기반의 구성을 이루게 되었다.

### 특징 

- 경량 컨테이너
- **IoC**(Invertion of Control: 제어 역행)
- **Di**(Dependency Injection: 의존성 주입)
- **AOP**(Aspect-Oriented Programming: 관점지향 프로그래밍)

### 단점

- 환경설정이 복잡하다. 
  - 예시로 Maven에 필요한 라이브러리를 설정해줘야한다. 
  - pom.xml 에 dependency로 필요한 라이브러리들을 일일히 설정해줘야한다.   

### 사용예시

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>5.3.5</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.5</version>
</dependency>
```

### spring MVC 패턴 동작 형태

- url 로 클라이언트가 접속하면 서버가 Controller에서 해당 url과 일치하는 RequestMapping을 호출한다. 
- 호출한 RequestMapping 에서 일을 처리하고 그다음 호출할 View를 클라이언트에게 보여준다. 
- 이때 보여주는 View는 JSP 파일이 아닌 HTML 파일이다.

---

## spring boot

## 정의

- spring + boot(strap). 스프링 부트는 기존 스프링 프레임워크 위에 구축되었다. 
- 스프링 부트는 스프링 프레임워크를 사용하기 위한 설정의 많은 부분을 자동화하여 사용자가 정말 편하게 스프링을 활용할 수 있도록 돕는다.
- **REST API 개발**에 주로 사용되는 프레임워크이다. 

### 출현이유

- 스프링 프레임워크는 기능이 많은만큼 **환경설정이 복잡한 편**이다. 이에 어려움을 느끼는 사용자들을 위해 나온 것이 바로 스프링 부트이다.

### 장점

- spring framework 의 단점인 실행환경구성을 쉽게 할 수 있다.
  - 의존성 주입부분을 자동화 한다. 
  - 따라서 개발시간, 노력을 줄이고 생산성을 높일 수 있다. 

### spring 과의 차이점 

1. Embed Tomcat을 사용하기 때문에, (Spring Boot 내부에 Tomcat이 포함되어있다.) 따로 Tomcat을 설치하거나 매번 버전을 관리해 주어야 하는 수고로움을 덜어준다.
2. **starter을 통한 dependency 자동화** 
   - 과거 Spring framework에서는 각각의 dependency들의 호환되는 버전을 일일이 맞추어 주어야 했고, 때문에 하나의 버전을 올리고자 하면 다른 dependeny에 까지 영향을 미쳐 version관리에 어려움이 많았다.
   - 하지만, 이제 starter가 대부분의 dependency를 관리해주기 때문에 이러한 걱정을 많이 덜게 되었다.
3. XML설정을 하지 않아도 된다.
   - **필요한 라이브러리들을 starter가 자동으로 dependency를 해주기 때문에** XML을 설정하지 않아도 된다.  
4. jar file을 이용해 자바 옵션만으로 손쉽게 배포가 가능하다.
   - Spring Actuaor를 이용한 애플리케이션의 모니터링과 관리를 제공한다

### 사용예시

```xml
implementation 'org.springframework.boot:spring-boot-starter-web'
```

### Spring Boot starter 란?

- **dependency 의 자동화**를 해주는 부분으로, spring 사용시 pom.xml에 많은 dependency 하는 부분들을 자동화 해주는 API이다. 
- 만약 우리가 JPA가 필요하다면 prom.xml(메이븐)이나 build.gradle(그레이들)에 'spring-boot-starter-data-jpa'만 추가해주면 **spring boot가 그에 필요한 라이브러리들을 알아서 받아온다.**
