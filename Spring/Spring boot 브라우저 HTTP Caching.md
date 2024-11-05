

# Spring boot 브라우저 HTTP Caching

---

> [Spring Cache 문서 ](https://docs.spring.io/spring-framework/reference/web/webflux/caching.html)

## Cache (캐시)

1. 클라이언트(브라우저)나 서버가 이전에 요청한 데이터를 저장해 두고, 동일한 요청이 있을 때 저장된 데이터를 빠르게 반환하는 메커니즘

## HTTP Caching 

##### 개념 

1. 클라이언트와 서버 간의 통신을 최적화하여 응답 시간을 단축하고 네트워크 대역폭을 절약하는 중요한 메커니즘이다. 
2. 캐싱을 통해 웹 애플리케이션의 성능을 개선할 수 있으며, 캐시된 데이터는 서버의 부하를 줄이는 데에도 도움을 준다. 
3. **민감한 데이터나 자주 변경되는 리소스는 캐시를 비활성화하는 것이 좋다.**

##### 장점

1. 성능 향상
   1. 클라이언트가 캐시된 데이터를 사용함으로써 서버와의 통신을 줄이고, 응답 시간을 단축
2. 서버 부하 감소
   1. 동일한 데이터에 대한 반복적인 요청을 서버가 아닌 클라이언트 측에서 처리함

##### caching 전략

1. **Client-Side Caching (클라이언트 측 캐싱)**: 
   1. 클라이언트(브라우저)가 캐시를 관리
   2. 이 경우, Cache-Control과 Expires 헤더를 통해 캐시 유효성 검사 및 동작을 제어한다. 
2. **Proxy Caching (프록시 캐싱)**: 
   1. 중간 프록시 서버가 클라이언트와 서버 간의 요청을 캐시한다. 
   2. 이를 통해 서버와 클라이언트 모두의 성능을 향상시킬 수 있다. 
3. **Server-Side Caching (서버 측 캐싱)**: 
   1. 서버가 데이터를 캐시하여 클라이언트의 요청에 대해 더 빠른 응답을 제공한다.

## Caching Header 

1. **Cache-Control**
   1. 캐싱의 동작을 정의
   2. `no-cache`
      1. 캐시된 데이터가 유효한지 확인해야 함.
   3. `no-store`
      1. 응답을 캐시하지 않음.
   4. `public`
      1. 모든 사용자에게 캐시 가능.
   5. `private`
      1. 개인 사용자만 캐시 가능.
   6. `max-age=<seconds>`
      1. **캐시된 응답이 유효한 최대 시간을 지정합니다.**
2. Expires
   1. 특정 날짜 및 시간까지 리소스가 유효하다는 정보를 제공
   2. `Cache-Control`이 없다면 이 값을 사용하여 캐시의 유효성을 판단
3. **ETag**
   1. 리소스의 특정 버전을 나타내는 식별자이다. 
   2. 클라이언트가 이 값을 사용하여 서버에 리소스의 변경 여부를 확인할 수 있다. 
4. Last-Modified
   1. 리소스가 마지막으로 수정된 날짜를 나타낸다. 
   2. 클라이언트는 이를 사용하여 서버에 리소스의 최신 버전을 요청할 수 있다.

## 주의할점

1. **HTTP 메소드**

   1. cache 는 GET 메소드에서만 사용된다. 
      1. 즉, POST 의 Response는 cache 를 사용하지 못한다. 
   2. 이유
      1. **HTTP 프로토콜의 설계 원칙에 따른 것**
      2. **HTTP 명세**의 HTTP/1.1 스펙에 따르면, GET 요청은 캐시될 수 있는 반면, POST 요청은 기본적으로 캐시되지 않도록 설계되어있다.
      3. 이 때문에 웹 브라우저와 서버는 POST 요청에 대한 응답을 캐시하지 않도록 설정되어있다.  

2. Spring Config 

   1. 전역으로 cache 설정이 비활성화 되어있으면 모든 Request에 대해 `no-cache` 상태가 될 수 있다. 

      1. 따라서 개별 Response 에대해 `no-cache` 가 될 수 있다.
      2. 즉, Spring에 설정해놓은 Security, Intercept, JWT 등 config 설정 중 cacheControl 이 비활성화 되어있다면 cache를 설정해도 `no-cache` 가 될 수 있다. 

   2. 예를들어, Spring Security 에 **민감한 데이터 보호** 를 위해 `no-cache` 가 되도록 `cacheControl().disable()` 을 추가할 수 있다. 

      ```java
      @Override
      protected void configure(HttpSecurity http) throws Exception {
          http
              .csrf().disable()
              .headers()
              .cacheControl().disable() // 전역 캐시 비활성화 -> no-cache와 같은 값으로 설정
              .and()
              .authorizeRequests()
              .antMatchers("/your-endpoint").permitAll() // 캐시 활성화할 엔드포인트
              .anyRequest().authenticated();
      }
      ```

## 사용예제

```java
@GetMapping("/book/{id}")
public ResponseEntity<Book> showBook(@PathVariable Long id) {

	Book book = findBook(id);
	String version = book.getVersion();
    // 1
 	CacheControl ccCacheOneHour = CacheControl.maxAge(1, TimeUnit.HOURS); // 1시간동안 캐싱 유지 

    // 2
	acheControl ccCustom = CacheControl.maxAge(10, TimeUnit.DAYS).noTransform().cachePublic(); // 10일동안 캐시 유지
    
    // 3
	CacheControl cacheControl = CacheControl.maxAge(80, TimeUnit.SECONDS).cachePublic(); // 80초동안 캐시 유지 
    
    // 1. Return 타입 : ModelAndView 
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", cacheControl.getHeaderValue()); // Response 헤더에 Cache-control 활성화 
    response.setHeader("Cache-Control", "public, max-age=30");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 사용 안함 
    return book;
    
    // 2. Return 타입 : ResponseEntity
	return ResponseEntity
			.ok()
			.cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))  // Response 헤더에 Cache-control 활성화
			.eTag(version) // lastModified is also available
			.body(book);
}
```

