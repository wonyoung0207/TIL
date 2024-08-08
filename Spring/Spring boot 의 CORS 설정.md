# Spring boot 의 CORS 설정

---

>

## CORS(Cross-Origin Resource Sharing) 

1. CORS는 다른 도메인에서 리소스를 요청할 때 발생하는 문제를 해결하기 위한 HTTP 헤더 기반의 메커니즘이다. 
2. 기본적으로 웹 브라우저는 보안상의 이유로 동일 출처 정책(Same-Origin Policy)을 따르며, 이는 한 출처(origin)에서 로드된 웹 애플리케이션이 다른 출처의 리소스에 접근하는 것을 제한한다. 
3. CORS는 이러한 제한을 완화하여, 특정 조건 하에서 다른 출처의 리소스에 접근할 수 있도록 허용한다. 

### 사용 이유 

1. 보안
   1. 동일 출처 정책은 웹 애플리케이션 간에 보안 경계를 유지하기 위해 존재합니다. 
   2. 그러나 현대 웹 애플리케이션은 API 서버, CDN, 제3자 서비스 등 다양한 출처에서 리소스를 불러와야 한다. 
2. 유연성
   1. CORS를 통해 개발자는 특정 출처에 대해서만 리소스 접근을 허용하거나 제한할 수 있다. 
3. 통제
   1. 특정 HTTP 메서드(GET, POST, PUT 등)나 헤더에 대해서만 접근을 허용하는 등 세부적인 설정이 가능하다. 

### 종류

1. **Access-Control-Allow-Origin**
   1. 특정 출처에 대해 리소스 접근을 허용
2. **Access-Control-Allow-Methods**
   1. 허용된 HTTP 메서드를 지정
3. **Access-Control-Allow-Headers**: 
   1. 클라이언트가 요청할 수 있는 헤더 목록을 지정
4. **Access-Control-Allow-Credentials**: 
   1. 인증 정보(쿠키, 인증 헤더 등)를 포함한 요청을 허용할지 여부를 지정
5. **Access-Control-Max-Age**: 
   1. 캐시할 수 있는 시간(초 단위)을 지정



## WebMvcConfigurer  VS Spring Security  에서의 CORS 차이점 

### 1. `WebMvcConfigurer` 에서 CORS 설정

1. 애플리케이션의 모든 HTTP 요청에 대해 전역적으로 CORS 설정을 적용

2. Spring MVC에서 제공하는 모든 엔드포인트에 대해 적용

3. `WebMvcConfigurer`: 주로 애플리케이션의 웹 구성 요소를 설정하는 클래스에 위치

   

### 2. Spring Security`에서 CORS 설정

1. 보안 필터 체인 내에서 처리되는 요청에 대해 CORS 설정을 적용
2. Spring Security의 CORS 설정이 우선한다. 
   1. 즉, Spring Security 필터 체인 내에서 CORS 설정이 활성화되면, 이 설정이 Spring MVC의 CORS 설정보다 우선하여 적용된다
3. 주로 보안이 중요한 요청이나 인증이 필요한 요청에 대해 더 세밀한 CORS 정책을 관리할 때 사용
4. `Spring Security`: 보안 설정 클래스에 위치하며, 보안 필터 체인 내에서 CORS 설정을 관리