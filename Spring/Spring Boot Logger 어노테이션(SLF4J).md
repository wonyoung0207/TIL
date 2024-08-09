# Spring Boot Logger 어노테이션(SLF4J)

---

>

## SLF4J (Simple Logging Facade for Java)

### 정의

1. **Java에서 로깅**을 위한 일관된 API를 제공하는 추상화 계층이다. 
2. SLF4J는 다양한 로깅 프레임워크(Logback, Log4j, Log4j2 등)의 추상화 계층을 제공한다. 
3. `Logger` 인터페이스를 통해 정적 메서드를 제공하여 로그 메시지를 기록할 수 있다. 
   1. 이를 통해 로그 레벨(**TRACE, DEBUG, INFO, WARN, ERROR**)에 따라 로그를 기록한다. 

### 사용방법

1. 의존성 주입

   ```html
   <!-- SLF4J API -->
   <dependency>
       <groupId>org.slf4j</groupId>
       <artifactId>slf4j-api</artifactId>
       <version>1.7.36</version>
   </dependency>
   
   <!-- Logback (SLF4J의 기본 구현체) -->
   <dependency>
       <groupId>ch.qos.logback</groupId>
       <artifactId>logback-classic</artifactId>
       <version>1.2.10</version>
   </dependency>
   ```

   ```js
   // SLF4J API
   implementation 'org.slf4j:slf4j-api:1.7.36'
   
   // Logback (SLF4J의 기본 구현체)
   implementation 'ch.qos.logback:logback-classic:1.2.10'
   ```

2. 코드 사용 

   ```java
   import org.slf4j.Logger;
   import org.slf4j.LoggerFactory;
   
   public class Example {
       private static final Logger logger = LoggerFactory.getLogger(Example.class);
   
       public void doSomething() {
           logger.info("This is an info message");
           logger.debug("This is a debug message");
       }
   }
   ```



## 주의할점

1. logger 의 사용법과 형태에 주의해야한다. 

   1. 나같은 경우는 logger  를 이용해 값을 찍었는데, 아무 값도 안나와 고생한적이 있다. 
   2. 이유는 logger의 사용법에 있었다. 

2. **`{}` 를 넣어줘야 logger의 값이 출력된다.** 

   ```java
   private static final Logger logger = LoggerFactory.getLogger(YourClass.class);
   
   // 틀린 방법
   logger.info("Request URI: ", request.getRequestURI());
   
   // 올바른 방법
   logger.info("Request URI: {}", request.getRequestURI());
   logger.info("attributes : {} , value :  {}", attributeName , attributeValue);
   ```

   