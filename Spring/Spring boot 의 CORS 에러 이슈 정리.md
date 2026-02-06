# CORS 에러 이슈 정리

---

## 문제

- **Preflight(OPTIONS) 요청은 200ok 으로 정상 처리** 
- 백엔드 호출 결과 return 까지 확인함 (postman 에서는 결과 나옴 ) 
  - Response Header 에 아무 정보가 안오는 점을 확인 ( `Access-Control-Allow-Origin`  가 없음) 
- 다른 API call 은 CORS 가 안남

---

## 원인

- 브라우저가 API 응답을 받을 때 **`Access-Control-Allow-Origin` 헤더가 없음**
  - `@EnableWebMvc` 사용으로 Spring Boot 기본 CORS 자동 설정 비활성화
  - Spring Security 사용 중이지만 **Security 단계에서 CORS 설정이 되어 있지 않음**
- 결과적으로 일부 API 응답이 **CORS 헤더 없이 반환됨**

------

## 조치

- CORS 설정을 **Spring Security 단계에서 명시적으로 추가**

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}
http
    .csrf().disable()
    .cors(); // CORS 활성화
```

(WebMvcConfigurer의 CORS 설정은 제거)

------

## 결과

- 모든 API 응답(정상/에러 포함)에 `Access-Control-Allow-Origin` 헤더 포함
- 브라우저가 응답을 정상적으로 프론트엔드에 전달
- 특정 API에서만 발생하던 CORS 에러 해결