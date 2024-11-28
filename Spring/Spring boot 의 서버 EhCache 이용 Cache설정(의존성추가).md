# Spring boot 의 서버 EhCache 이용 Cache설정(의존성추가)

---

>

## EhCache란?

1. Java 기반의 강력하고 유연한 캐싱 솔루션으로, JVM 메모리 내에서 데이터를 저장하며 빠른 읽기 속도를 제공한다. 
2. TTL(시간 기반 만료) 및 TTI(활용 시간 기반 만료) 같은 다양한 캐싱 정책을 지원한다. 
   1. Spring Boot 의 기본 서버 메모리 캐싱은 TTL 사용이 불가능하다. 

## 이용 방법

##### 1. 의존성 추가 

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
    <groupId>org.ehcache</groupId>
    <artifactId>ehcache</artifactId>
</dependency>
```

##### 2. EhCache 설정 파일 작성

1. EhCache의 캐싱 정책을 설정하기 위해 `ehcache.xml` 파일을 생성해야한다.ㅏ 
2. 보통 `src/main/resources`에 작성한다. 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<config xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.ehcache.org/v3"
        xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core.xsd">

    <cache alias="tileCache">
        <expiry>
            <ttl unit="seconds">60</ttl> <!-- 캐시 유효기간 60초 -->
        </expiry>
        <heap unit="entries">100</heap> <!-- 최대 100개의 엔트리 저장 -->
    </cache>
</config>
```

##### 3. Spring Boot 설정에 EhChache 추가 

1. Spring Boot 프로젝트에서 EhCache를 활성화하려면 `@EnableCaching`을 사용해야한다. 
2. 설정 위치 
   1. **`@EnableCaching` 는 `main` 이 있는 Spring Boot Application 시작점**에 걸어주는것이 좋다. 
   2. 왜냐하면 **`@EnableCaching`은 전역 기능을 활성화**하므로, 일반적으로 Spring Boot의 메인 클래스에 붙이는 것이 관리 측면에서 좋기 때문이다.
   3. `@EnableCaching`을 `EhCacheConfig`에 붙여도 동작은 하지만, 구성의 역할이 분리되지 않아 프로젝트가 복잡해질 경우 비효율적이기 때문에 지양하는것이 좋다. 

```java
// 1. main 에 한경우 
@SpringBootApplication
@EnableCaching
public class SpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(EhCacheApplication.class, args);
    }
}

// 2. config 클래스에 한경우
@Configuration
@EnableCaching
public class EhCacheConfig {
    
}
```

##### 4. **CacheManager 설정**

1. EhCache를 사용하려면 Spring의 `CacheManager`를 EhCache로 설정해야한다. 

   1. 이때는 Config 를 만들어 관리하는것이 좋다. 

2. **ehcache.xml 사용**

   1. `ehcache.xml` 파일을 `resources` 디렉토리에 배치하면 Spring Boot가 이를 자동으로 로드

      ```xml
      <config xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns="http://www.ehcache.org/v3"
              xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core.xsd">
          <cache alias="tileCache">
              <key-type>java.lang.String</key-type>
              <value-type>java.lang.String</value-type>
              <expiry>
                  <ttl unit="seconds">60</ttl>
              </expiry>
              <heap unit="entries">100</heap>
          </cache>
      </config>
      ```

3. **config 메소드 사용** 

   1. java class를 사용해 cacheManager Bean을 생성한다. 

   2. 캐시 등록 

      1. **withCache() 를 이용해 Cache 를 등록**해야한다.

      ```java
      @Configuration
      public class CacheConfig {
      
          @Bean
          public CacheManager cacheManager() {
              return new EhCacheCacheManager(ehCacheManager()); // ehCacheManager() 호출 
          }
      
          @Bean
          public org.ehcache.CacheManager ehCacheManager() {
              return CacheManagerBuilder.newCacheManagerBuilder()
                      .withCache("tileCache", createCacheConfiguration(100, Duration.ofSeconds(60))) // 100개, 60초 TTL
                      .withCache("tileDownload", createCacheConfiguration(50, Duration.ofMinutes(10))) // 50개, 10분 TTL
                      .build(true);
          }
      
          // CacheConfiguration을 생성하는 메소드
          private CacheConfiguration<String, Object> createCacheConfiguration(int maxEntries, Duration ttl) {
              return CacheConfigurationBuilder
                      .newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(maxEntries))
                      .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(ttl)); // TTL 설정
          }
      }
      ```

##### 5. Service에 캐시 적용

1. Service에서 `@Cacheable` 를 이용해 등록한 cache를 사용할 수 있다. 

```java
@Service
public class TileService {

    @Cacheable(value = "tileCache", key = "#tileId")
    public String getTileData(String tileId) {
    }

    @CachePut(value = "tileCache", key = "#tileId")
    public String updateTileData(String tileId, String newData) {
    }
}
```

