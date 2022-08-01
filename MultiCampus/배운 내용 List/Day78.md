# Day78

---

> 쿠폰기능 
>
> 로그인 후 이전 페이지 기억 

# Final Project

>spring boot 에서 properties 와 yml 파일의 차이점 
>
>WebSecurityConfigurerAdapter 지원 불가 해결방법

## OAuth 로그인 

#### spring boot 에서 properties 와 yml 파일의 차이점 

- [참고 사이트](https://newwisdom.tistory.com/89)

- **_properties (속성 파일)_**

  - 기본적으로 Spring Boot는 key-value 형식을 사용 하는 *application.properties* 파일에 설정된 구성에 액세스 할 수 있다. 

  - 버전 2.4.0부터 Spring Boot는 다중 문서 속성 파일 생성을 지원한다. 따라서 각 프로필에 대한 문서를 모두 동일한 파일에 정의 할 수 있다.

    ```properties
    # apllication-prod.properties
    spring.config.activate.on-profile=prod
    # apllication-dev.properties
    spring.config.activate.on-profile=dev
    
    # 예를 들어 application-dev.yml 또는  application-dev.properties 와 같이 파일 이름에 프로필 이름을 입력하면됩니다 .
    ```

- **_yml (YAML 파일)_**

  - YAML은 계층 적 구성 데이터를 지정하기위한 편리한 형식이다. 

  - 속성 파일( properties 파일 )과 달리 YAML은 설계에 따라 다중 문서 파일을 지원하므로 사용하는 Spring Boot 버전에 관계없이 동일한 파일에 여러 프로필을 저장할 수 있다.

    ```yaml
    spring:
      config:
        activate:
          on-profile: staging
      datasource:
        password: 'password'
        url: jdbc:h2:staging
        username: SA
    bael:
      property: stagingValue
    ```

- 사용방법

  1. 값 어노테이션 이용 

     ```java
     @Value("${key.something}")
     private String injectedProperty;
     ```

  2. Envirionment Abstraction 이용 

     - Envirionment API 를 이용해서 사용 

     ```java
     @Autowired
     private Environment env;
     
     public String getSomeKey(){
         return env.getProperty("key.something");
     }
     ```

  3. ConfigurationProperties 어노테이션 이용 

     ```java
     @ConfigurationProperties(prefix = "mail")
     public class ConfigProperties {
         String name;
         String description;
     }
     ```

- 정리 

  - properties 파일은 ket-value 형식으로 여러 Profile 값들을 가져와 사용한다. 
  - yml 파일은 계층구조로 되어있어 데이터를 지정하기 더 편리하다. 
  - 주의할점은 **application.properties와 application.yml 동시사용이다.**  둘 간의 순서 차이가 있는데, application.properties가 항상 나중에 로드되어 YAML에 정의한 profil 설정이 덮어씌여질 수 있기 때문이다.

### SecurityConfig 에서 WebSecurityConfigurerAdapter 지원 불가 

- WebSecurityConfigurerAdapter 를 지원하지 않아 사용불가하다. 

  - 기존 security 파일 

    ```java
    @SuppressWarnings("deprecation")
    @RequiredArgsConstructor
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {//WebSecurityConfigurerAdapter
    	// 커스텀한 OAuth2UserService DI.
      @Autowired
      private CustomOAuth2UserService customOAuth2UserService;
      @Override
      protected void configure(HttpSecurity http) throws Exception {// oauth 서비스 연결하는 곳 
          http
                  .csrf().disable()
                  .headers().frameOptions().disable()
                  .and()
                  .authorizeRequests()
                  .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
    //              .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                  .antMatchers("/login").permitAll()// 커스텀 로그인 페이지를 만든 경우 권한을 수동으로 모두 접근 가능하도록 변경
                  .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                  .anyRequest().authenticated()
                  .and()
                  .logout().logoutUrl("/logout")
                  .logoutSuccessUrl("/")// 로그아웃 성공시 이동 페이지 
                  .and()
                  .oauth2Login()
                  .loginPage("/login")//OAuth2 로그인 설정에서 로그인 페이지 URL을 수동으로 변경
                  .defaultSuccessUrl("/")// 로그인 성공시 이동 페이지
                  .userInfoEndpoint()
                  .userService(customOAuth2UserService);	// oauth2 로그인에 성공하면, 유저 데이터를 가지고 우리가 생성한
           	       						// customOAuth2UserService에서 처리를 하겠다. 그리고 "/login-success"로 이동하라.
      }
    }
    
    ```

- 방법 

  - bean을 이용해서 config 만들기 

  ```java
  @EnableWebSecurity
  @RequiredArgsConstructor
  @Configuration(proxyBeanMethods = false)
  @ConditionalOnDefaultWebSecurity
  @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
  public class SecurityConfig {
  
  	@Autowired
      private final CustomOAuth2UserService customOAuth2UserService;
  
      @Bean
      @Order(SecurityProperties.BASIC_AUTH_ORDER)
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .csrf().disable()
                  .headers().frameOptions().disable()
                  .and()
                  .authorizeRequests()
                  .antMatchers("/", "/css/**", "/images/**",
                          "/js/**", "/h2-console/**").permitAll()
                  .antMatchers("/api/v1/**").hasRole(Role.
                          USER.name())
                  .anyRequest().authenticated()
                  .and()
                  .logout()
                  .logoutSuccessUrl("/")
                  .and()
                  .oauth2Login()
                  .userInfoEndpoint()
                  .userService(customOAuth2UserService);
          return http.build();
      }
  }

