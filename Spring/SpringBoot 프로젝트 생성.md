# SpringBoot 프로젝트 생성 

---

>[참고 사이트1](https://velog.io/@luna001631/Springboot-Spring-Initializr%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90)

## 다운로드

1. Spring Initializr 에서 초기 프로젝트 구성을 받을 수 있다.
2. [Spring Initializr 사이트](https://start.spring.io/)

## 구성

1. **Project**: 
   1. SpringBoot를 빌드하고 배포하는 방식이다. 
   2. 현재는 Gradle을 사용하여 프로젝트를 생성하는 추세이다.
2. **Language**
   1. 사용하고자하는 언어를 선택하면 된다. (일반적으로 Java가 사용됨)
3. **SpringBoot**
   1. 버전을 선택해 준다. 
   2. SNAPSHOT은 데모버전이고 높은 버전은 높은 자바버전을 필요로 하므로 SNAPSHOT이 없는 낮은 버전을 선택하는 것이 좋다.
4. **Group**
   1. 기업 도메인명
5. **Artifact**
   1. 빌드되어 나올 결과물
6. **Name**
   1. 프로젝트명 (일반적으로 Artifact와 동일하게 사용함)
7. **Description**
   1. 설명
8. **Package name**
   1. 패키지이름(Group과 Artifact를 설정하면 자동으로 만들어 준다.)
9. **Packaging**: 
   1. 기본이 .jar이다. (spring framework와 model2는 .war를 사용한다.

<img src="./images/SpringBoot 프로젝트 생성.png" width="900">

## Dependencies 추가 

- SpringBoot 프로젝트 생성시 같이 사용할 라이브러리들을 추가할 수 있다. 

1. **SpringWeb (중요)**
   1. **웹 서비스를 만드는 데 가장 중요한 모듈**이다. 
   2. 내장 톰캣 뿐 아니라 Spring MVC 패턴을 구현하는 데 필요한 기능이 대부분 들어있다. 
   3. REST API서버를 만든다면 필수이다.
2. **Lombok (추천, 거의 필수)**
   1. Class에 getter, setter, toString, equals, constructor 등의 메소드들을 간단한 어노테이션(@)으로 지정해줄 수 있어 자바특유의 장황한 클래스를 줄여준다.
   2. 거의다 사용한다.
3. **Spring Data JPA ( 필수 )**
   1. JPA는 Java Persistence API라고 하는데 자바 ORM기술의 토대를 이루는 기술 명세이다. 
   2. Spring에서 DB를 다루는 거의 표준 기술인 상황이다. 
   3. Class Entity를 마치 DB처럼 사용할 수 있는 매우 유용한 도구이다. (학습량 또한 엄청나다.)
4. **MySQL Driver**
   1. MySql의 드라이버를 자동으로 연결해준다.
5. **Spring Configuration Processor**
   1. 스프링 개발을 하면서 application.yml 또는 application.properties를 작성할 때 추천을 받고 싶을 때 이 기능을 사용하면 된다.
6. **Spring Boot DevTools (취향에 따라 사용)**
   1. DevTools는 스프링 어플리케이션을 띄웠을 때, 재시작하지 않고 코드의 변화를 반영시킬 때 사용한다. 
   2. HTML 파일의 경우 LiveReload 기능을 제공하여 저장하면 그 변화가 페이지에 바로 나타나게 할 수도 있다.
7. **Thymeleaf**
   1. View 템플릿인 Thymeleaf를 사용할 수 있도록 하는 모듈이다. 
   2. Spring에서 많이 사용하는 JSP는 Spring Boot에서는 잘 사용하지 않는다.

## 사용방법

1. SpringBoot 프로젝트 다운로드 
   1. 위의 방법으로 설정 후 `Generate`  클릭 으로 프로젝트 다운로드 
2. 프로젝트 알집 풀어서 사용 