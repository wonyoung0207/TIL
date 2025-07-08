4. # Spring boot 의 Logger Interceptor 사용법

   ---
   
   >
   >
   >
   
   ## 선행 및 사용 개념

   1. Spring Boot 의 webConfig 
      1. 프로젝트의 설정파일로 Webconfig 추가 
   2. http 변환기 
      1. MappingJackson2HttpMessageConverter 사용 
   3. Intercepter 
      1. HTTP 통신의 API 호출시 Request 값을 이용해 데이터 수집 
   4. HTTP 통신의 Request 와 Response
      1. Request 정보를 이용
   5. Spring Boot의 SLF4J 어노테이션 
      1. Spring Boot 의 어노테이션 이용해 필요한 Log 내용 표출 
   
   ### 1. Config 란? 
   
   1. 프로젝트의 설정을 뜻하는 것으로, 프로그램 동작시 전역으로 설정할 값들을 넣어 사용할 수 있다.
   2. `WebMvcConfigurer`
      1. Spring MVC에서 애플리케이션의 웹 구성 요소를 사용자 정의할 수 있는 인터페이스이다. 
      2. 이 인터페이스를 구현하여 애플리케이션에 필요한 설정을 추가할 수 있다.
      3. 이를 통해, 기존의 XML 기반 설정 파일을 대체할 수 있으며, 더욱 직관적이고 강력한 방식으로 웹 애플리케이션을 설정할 수 있다. 
      4. 스프링 MVC 애플리케이션에서 웹 설정을 프로그래밍 방식으로 할 수 있는 강력한 인터페이스이다. 
         1.  이를 통해 인터셉터, 뷰 컨트롤러, 정적 자원, 메시지 컨버터, CORS 설정 등을 손쉽게 관리
         2.  `@Configuration` 어노테이션과 함께 사용하여 애플리케이션의 설정을 **중앙 집중화**하고, 코드 기반 설정의 이점을 활용할 수 있다. 
   
   ### 2. HttpMessageConverter
   
   1. Spring MVC에서 HTTP 요청과 응답을 Java 객체와 변환하는 역할을 한다. 
   2.  HTTP 메시지 본문을 Java 객체로 변환하거나, Java 객체를 HTTP 메시지 본문으로 변환하는 데 사용된다. 
      1. Spring MVC에서 클라이언트가 서버로 데이터를 전송하거나 서버가 클라이언트로 데이터를 응답할 때, 데이터는 일반적으로 JSON, XML 등의 형식으로 전송
      2. 이때 Java Object 로 변경하는 것이 HttpMessageConverter의 역할이다. 
   
   ### 3. Intercepter 
   
   1. MVC에서 요청(request)과 응답(response)의 흐름을 가로채고, 이를 가로채서 특정 작업을 수행할 수 있도록 하는 기능을 제공한다. 
   
   ### 4. Request 와 Response 
   
   1. **응답 본문과 분리**해서 사용해야 한다. 
   
      1. `HttpServletRequest` 는 Java Servlet API의 핵심 인터페이스로, HTTP 요청에 대한 정보를 제공하고, 요청 데이터를 처리할 수 있도록 하는 다양한 메서드를 제공한다. 
      2. `HttpServletRequest`는 요청 본문을 제공하며, 응답 본문은 `HttpServletResponse` 객체를 통해 설정하고 처리한다. 
   
   2. Spring MVC에서는 `@RequestBody`를 사용하여 본문 데이터를 직접 매핑할 수 있으며, 요청 본문을 직접 읽는 방법도 가능하다. 
   
      1. 하지만 본문 데이터를 한 번만 읽을 수 있기 때문에, 여러 번 읽어야 하는 경우에는 래퍼 클래스를 사용하는 것이 좋다. 
      2. 즉, `@RequestBody` 를 이용해 api 의 매개변수값을 사용하거나, `@RequestParam` 을 통해 매개변수를 꺼내와 사용할 수 있다. 
      3. `@RequestHeader` 를 이용하면 api의 header 정보를 추출할 수있다.
   
   3. 예시
   
      ```java
      @RestController
      public class ExampleController {
           @GetMapping("/example")
          public String getExample(HttpServletRequest request, @RequestParam String param, @RequestHeader String userAgent) {
              // 쿼리 파라미터 읽기
              String paramValue = request.getParameter("param");
          }
          
          @PostMapping("/api/example")
          public String postExample(@RequestBody RequestPayload payload) {
              // 본문 파라미터 추출
              return "Param1: " + payload.getParam1() + ", Param2: " + payload.getParam2();
          }
      
          @GetMapping("/api/example")
          public String getExample(@RequestParam String param1, @RequestParam String param2) {
              return "Param1: " + param1 + ", Param2: " + param2;
          }
          
          @GetMapping("/api/example")
          public String getExample(@RequestHeader("Authorization") String authHeader, @RequestHeader("Custom-Header") String customHeader) {
              return "Authorization Header: " + authHeader + ", Custom Header: " + customHeader;
          }
      }
      ```
   
   
   
   ---
   
   
   
   ## 전체 파일 구조 
   
   <img src="./images/interceptor 파일구조.png" width="400">
   
   ### 저장 DB 구조 
   
   <img src="./images/interceptor DB구조.png" width="700">
   
   ## Interceptor 종류 
   
   1. preHandle
      1. 컨트롤러 실행 전에 수행
      2. 이 메서드가 `true`를 반환하면 다음 인터셉터나 컨트롤러로 요청이 전달된다. 
      3. `false`를 반환하면 요청이 중단되고 응답이 클라이언트로 돌아간다.
   2. postHandle
      1. 컨트롤러 수행 후 결과를 뷰로 보내기 전에 수행
      2. `ModelAndView` 객체를 통해 컨트롤러가 반환한 모델 데이터를 수정할 수 있다. 
   3. afterCompletion
      1. 뷰의 작업까지 완료된 후 수행
      2. 이 메서드는 주로 리소스를 해제하거나 로그를 기록하는 데 사용된다. 
   
   ## Interceptor 동작 순서
   
   1. API 호출 
      1. 클라이언트 -> 서버로 API 요청 발생 
      2. SpringBoot의 MVC 구조로 인해 Controller 가 호출
   2. 요청 처리
      1. MVC 구조에 따라 요청을 처리 
         1. 요청 정보에는 파라미터, Requset Header 정보 등의 네트워크 요청 정보가 포함됨
         2. **requset.getAttribute("name") 로 요청값에 데이터 추가 가능** 
      2. 결과값을 Controller 에서 리턴 
   3. 화면 표출 
      1. 요청 결과값을 화면에 표출 
   4. **Interceptor** 
      1. 사용 메소드에 따라 Controller 실행 전 후, 화면 랜더링 후의 결과값을 Intercepte 할 수 있다. 
      2. 즉, 시점에 따라 값을 다르게 저장할 수 있다.
   
   ## Interceptor 예시 
   
   1. Intercepor 클래스
   
      ```java
      @Slf4j
      // @SuppressWarnings("deprecation")
      @Component
      public class CustomInterceptor implements HandlerInterceptor {
      
          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
              System.out.println("Pre Handle method is Calling");
              return true;  // 다음 인터셉터나 컨트롤러로 요청을 전달
          }
      
          @Override
          public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
              System.out.println("Post Handle method is Calling");
          }
      
          @Override
          public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
              System.out.println("Request and Response is completed");
          }
      }
      ```
   
   2. Converter 등록
   
      ```java
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
   
   3. Interceptor 등록 
   
      ```java
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
                  	registry
                  	.addInterceptor(interceptor)
                  	.addPathPatterns("/**");
                  }
              };
          }
          
          @Bean
          public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter(){
              MappingJackson2HttpMessageConverter converter = new dtMappingJackson2HttpMessageConverter();
              return converter;
          }
      
      }
      
      ```
      
      - 다중 intercepter 추가 
      
      ```java
      @Configuration
      public class WebMvcConfig implements WebMvcConfigurer {
      
          private final AuthInterceptor authInterceptor;
          private final LoggingInterceptor loggingInterceptor;
          private final LocaleInterceptor localeInterceptor;
      
          public WebMvcConfig(AuthInterceptor authInterceptor,
                              LoggingInterceptor loggingInterceptor,
                              LocaleInterceptor localeInterceptor) {
              this.authInterceptor = authInterceptor;
              this.loggingInterceptor = loggingInterceptor;
              this.localeInterceptor = localeInterceptor;
          }
      
          @Override
          public void addInterceptors(InterceptorRegistry registry) {
      
              // 1. 로깅 인터셉터: 전체 요청 로깅
              registry.addInterceptor(loggingInterceptor)
                      .addPathPatterns("/**");
      
              // 2. 인증 인터셉터: /api/** 경로 보호
              registry.addInterceptor(authInterceptor)
                      .addPathPatterns("/api/**")
                      .excludePathPatterns("/auth/**", "/swagger-ui/**");
      
              // 3. 언어 인터셉터: 특정 경로에서만 다국어 처리
              registry.addInterceptor(localeInterceptor)
                      .addPathPatterns("/international/**");
          }
      }
      ```
   
   ---
   
   ## 문제 발생 
   
   1. Interceptor 추가 후 API 결과값으로 받은 값 중에 "시간"이 다른 형태로 넘어오는 문제 발생 
      1. 예를들어, 이전에는 "2024-07-26T16:58:56.100141" 의 값이 들어왔다면, Interceptor 를 적용하고 난 후에는 "시간" 값들이 배열로 [2024, 07, 26 ... ] 이런식으로 저장되어 표출된다.
      2. 따라서 배열형태의 시간값을 String 형식인 `ISO-8601` 으로 바꿔줘야 했다. 
      3. 문제의 이유는 Interceptor 에 있는 Converter 인 `MappingJackson2HttpMessageConverter` 이였다. 
   
   ### MappingJackson2HttpMessageConverter
   
   1. 정의
   
      1. Jackson의 **`ObjectMapper`**를 사용해 JSON과 Java 객체 간의 변환 작업을 수행한다. 
      2. **즉, request 에 있는 JSON 파일을 Java객체로 변환하거나, Java객체를 JSON으로 변환하는데 사용한다.** 
   
   2. 특징
   
      1. `ObjectMapper`를 수정해 날짜 형식을 지정하거나, 특정 필드만 직렬화하도록 설정할 수 있다. 
      2. `read` 및 `write` 메서드를 오버라이드함으로써 데이터 처리 방식을 커스터마이징할 수 있다. 
      3. 필요에 따라 커스터마이징된 변환기를 추가할 수 있다. 
      4. Spring MVC와 함께 사용되며, `@RequestBody`, `@ResponseBody` 어노테이션과 관련된 작업에 자주 사용된다. 
   
   3. 날짜 배열을 ISO 형식으로 변환 
   
      ```java
      // 생성자 
      public MappingJackson2HttpMessageConverter() {
          super();
          // 날짜 변환 DB와 같게 하려고 
          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.registerModule(new JavaTimeModule());
          objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
          this.setObjectMapper(objectMapper);
      }
      ```
   
   4. 사용예시
   
      ```java
      @PostMapping("/api/user")
      public ResponseEntity<User> createUser(@RequestBody User user) { // RequestBody는 post 로 넘겼을 경우 파라미터로 넘겨준 값이 들어간다.
          // 'user' 객체는 JSON으로부터 자동으로 변환됨
          return ResponseEntity.ok(user);
      }
      ```
