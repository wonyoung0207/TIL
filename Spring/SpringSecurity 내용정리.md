# Spring Security 개념정리 

---

>

## Spring Security 

1. Spring Security는 강력한 인증 및 인가 기능을 제공하는 프레임워크이다. 
2. 이를 사용하면 애플리케이션의 보안 요구 사항을 쉽게 구현할 수 있다. 

## SecurityConfig 클래스 

1. Spring Boot의 `SecurityConfig` 클래스는 애플리케이션의 보안 설정을 정의하는 중요한 구성 요소이다. 
2. 다음 클래스는 **보안 설정을 커스터마이징**하기 위해 사용된다.
   1. 애플리케이션의 보안을 강화하고, 사용자 경험을 향상시킨다. 
3. 이 클래스는 `WebSecurityConfigurerAdapter`를 상속받아 구현한다. 
4. 이 클래스에서 사용할 수 있는 설정 
   1. HTTP 요청의 인가 및 인증 규칙 
   2. 사용자 정의 로그인 페이지
   3. 비밀번호 인코딩
   4. 데이터베이스 기반 인증
   5. CSRF 보호
   6. CORS 설정 
   7. 예외 처리

## 기본구조

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### 1. HttpSecurity 설정

1. `HttpSecurity` 객체를 사용하여 HTTP 요청에 대한 보안 규칙을 정의한다. 
2. `authorizeRequests()`: 요청에 대한 인가 규칙을 정의
   - `antMatchers()`: 특정 경로에 대한 접근 권한을 설정
   - `anyRequest()`: 모든 요청에 대한 접근 권한을 설정
3. `formLogin()`: 폼 기반 로그인 설정을 정의
   - `loginPage()`: 사용자 정의 로그인 페이지 경로를 설정
4. `logout()`: 로그아웃 설정을 정의

#### 1-1PasswordEncoder 설정

1. 비밀번호를 안전하게 저장하기 위해 `PasswordEncoder`를 사용한다. 
2. Spring Security는 다양한 인코더를 제공하며, `BCryptPasswordEncoder`가 가장 많이 사용된다. 

#### 1-2사용자 정의 로그인 페이지

1. 사용자 정의 로그인 페이지를 사용하려면 `loginPage("/login")`을 설정하고, 해당 경로에 대한 컨트롤러와 뷰를 구현하면 된다. 



### 2. In-Memory Authentication 설정

1. Spring Security는 기본적으로 메모리 기반의 사용자 인증을 지원한다. 
2. 이는 테스트나 프로토타입 단계에서 유용하다. 

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user")
        .password(passwordEncoder().encode("password"))
        .roles("USER")
        .and()
        .withUser("admin")
        .password(passwordEncoder().encode("admin"))
        .roles("ADMIN");
}
```



### 3. 데이터베이스 기반 인증 설정 

1. 실제 애플리케이션에서는 보통 데이터베이스를 사용하여 사용자 정보를 관리한다. 
2. 이를 위해 `JdbcUserDetailsManager`나 사용자 정의 `UserDetailsService`를 사용할 수 있게 제공한다. 

```java
import javax.sql.DataSource;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Bean
public UserDetailsManager users(DataSource dataSource) {
    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
    return manager;
}
```



### 4. CSRF 보호

1. Spring Security는 기본적으로 CSRF 보호를 활성화한다. 
2. 이를 비활성화하려면 다음과 같이 설정한다. 

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
        .logout()
            .permitAll();
}
```



### 5. CORS 설정

1. CORS 설정은 다른 도메인에서의 요청을 허용하기 위해 필요할 수 있다.

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                .allowedOrigins("http://example.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        }
    };
}
```



### 6. 예외처리 

1. 보안 예외 발생 시 사용자에게 적절한 응답을 제공하기 위해 예외 처리를 설정할 수 있다.

```java
@Bean
public AuthenticationFailureHandler authenticationFailureHandler() {
    return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
}
```

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .failureHandler(authenticationFailureHandler())
            .permitAll()
            .and()
        .logout()
            .permitAll();
}
```



