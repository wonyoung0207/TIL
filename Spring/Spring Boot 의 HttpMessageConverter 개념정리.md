# HttpMessageConverter 개념정리 

---

>

## HttpMessageConverter

### 정의

1. Spring MVC에서 HTTP 요청과 응답을 Java 객체와 변환하는 역할을 한다. 
2.  HTTP 메시지 본문을 Java 객체로 변환하거나, Java 객체를 HTTP 메시지 본문으로 변환하는 데 사용된다. 
   1. Spring MVC에서 클라이언트가 서버로 데이터를 전송하거나 서버가 클라이언트로 데이터를 응답할 때, 데이터는 일반적으로 JSON, XML 등의 형식으로 전송된다. 
   2. 이때 Java Object 로 변경하는 것이 HttpMessageConverter의 역할이다. 

## 주요 메소드 

1. read
   1. HTTP 요청 본문을 읽어 지정된 클래스로 변환한다. 
2. write 
   1. 지정된 객체를 HTTP 응답 본문으로 사용한다. 

## 주요 구현체 

1. `MappingJackson2HttpMessageConverter` 
   1. JSON 데이터를 Java 객체로 변환하거나 Java 객체를 JSON 데이터로 변환한다.
   2. Jackson 라이브러리를 사용
2. `MappingJackson2XmlHttpMessageConverter`
   1. XML 데이터를 Java 객체로 변환하거나 Java 객체를 XML 데이터로 변환한다. 
   2. Jackson XML 확장을 사용
3. `StringHttpMessageConverter`
   1. 문자열 데이터를 처리한다. 
4. `FormHttpMessageConverter`
   1. 폼 데이터를 처리한다.

## 동작 방법 

1. HTTP 요청이 들어오면 Spring MVC는 요청의 `Content-Type` 헤더를 확인한다. 
   1. 그리고, 적절한 `HttpMessageConverter`를 선택한다. 
2. HTTP 응답을 생성할 때는 `Accept` 헤더를 참고하여 클라이언트가 수용할 수 있는 형식으로 응답을 생성한다. 

## 의존성 추가 

```xml
<!-- Maven -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

```java
// gradle
implementation 'org.springframework.boot:spring-boot-starter-web'
```

## 동작예시

### Webconfig.java

```JAVA
import com.meta.tt.api.jackson.WYMappingJackson2HttpMessageConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Autowired
	private Interceptor interceptor;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            // @Override
            // public void addCorsMappings(CorsRegistry registry) {
            //     registry
            //             .addMapping("/**")
            //             .allowedOrigins("*")
            //             .allowedHeaders("*")
            //             .allowedMethods("*");
            // }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
                messageConverters.add(customJackson2HttpMessageConverter());
            }
            
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//            	WebMvcConfigurer.super.addInterceptors(registry);
            	registry
            	.addInterceptor(interceptor)
            	// .addPathPatterns("/login/**");
            	.addPathPatterns("/**");
            }
        };
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new WYMappingJackson2HttpMessageConverter();
        return converter;
    }

}
```

### WYMappingJackson2HttpMessageConverter.java

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class dtMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(dtMappingJackson2HttpMessageConverter.class);

    @Autowired
    private HttpServletRequest request;
    

    // 생성자 
    public dtMappingJackson2HttpMessageConverter() {
        super();

        // 날짜 변환 DB와 같게 하려고 
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.setObjectMapper(objectMapper);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        String userId = (String) request.getAttribute("userId");
        Object obj = super.read(type, contextClass, inputMessage);

        Method[] methods = obj.getClass().getMethods();

        if (!StringUtils.hasLength(userId)){
            return obj;
        }

        try {
            String methodName = null;

            for (Method method : methods) {
                methodName = method.getName();
                if ("setUpdId".equals(methodName) || "setRegId".equals(method.getName())){
                    method.invoke(obj, userId);
                }
            }

        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        } catch(Exception e){
            logger.error(e.getMessage(), e);
        }

        return obj;
    }
}
```

