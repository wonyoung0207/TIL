# Spring boot 의 서버 메모리 이용 Cache설정(기본기능)

---

>

## 1. 서버 메모리 캐시

1. Spring boot 브라우저 HTTP Caching 과는 다르게 **서버에 캐시를 저장**하는 방식이다. 
1. **단점은 TTL 을 사용하지 못한다. ( EhCache , redis 등 다른 dependencies를 추가해야한다. )**

## 2. 의존성

1. 의존성 선언 x

   1. `spring-boot-starter-cache` 의존성이 없어도 Spring Boot는 **캐시 관련 자동 구성을** 제공합니다. 
   2. 이 구성을 통해 기본적인 캐시 기능이 활성화 된다. 

2. 의존성 선언 o

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-cache</artifactId>
   </dependency>
   ```

## 3. Cachemanager

##### 1. ConcurrentMapCacheManager 

1. 메모리 내 캐시를 관리하는 기본 구현체이다. 

##### 2. 동작방식

1. `spring-boot-starter-cache`를 의존성에 포함하면, `cacheManager`가 자동으로 생성된다. 
2. 기본적으로 **`ConcurrentMapCacheManager`**가 사용됩니다.
3. 만약 별도로 다른 캐시 매니저(예: Redis, EhCache 등)를 사용하고 싶다면, `@Configuration` 클래스를 만들어 캐시 매니저를 명시적으로 정의할 수 있다. 

##### 3. 기본제공

1. `cacheManager`를 명시적으로 정의하지 않아도 **`spring-boot-starter-cache`** 의존성이 추가되어 있으면, Spring Boot가 기본적으로 **`ConcurrentMapCacheManager`**를 제공하여 캐시를 관리한다. 
2. 이로 인해 별도로 캐시 매니저를 설정하지 않아도 캐시 기능이 동작하는 것이다. 

```java
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching  // 캐시 기능 활성화
public class CacheConfig {
    
    // 생략가능 
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("tileCache");
    }
}
```

## 4. 어노테이션 종류

##### 1. 캐시 활성화 (`@EnableCaching`)

1. 이 어노테이션을 사용하면 캐시 관리가 활성화되며, Spring Boot가 **캐시 매니저**를 자동으로 설정한다. 
2. 만약 외부 캐시 서버(예: Redis, EhCache)를 사용하고자 한다면 해당 의존성을 추가하고 설정을 변경할 필요가 있지만, 메모리 캐시를 사용하는 경우에는 기본 설정으로 동작해 따로 캐시 매니저를 설정하지 않아도 된다. 

##### 2. 캐시 등록(`@Cacheable`)

1. 메서드의 결과를 캐시에 저장하며, 같은 키로 호출할 경우 캐시된 값을 반환한다. 

2. 캐시에 값이 없으면 메서드가 실행되고, 결과가 캐시에 저장된다. 

3. Spring Boot가 자동으로 캐시를 관리하고, 기본적으로 메모리 캐시인 **`ConcurrentMapCacheManager`**를 사용한다. 

   ```java
   @Service
   public class TileService {
   
       @Cacheable(value = "tileCache", key = "#tileId")
       public String getTileData(String tileId) {
           System.out.println("Fetching data for Tile ID: " + tileId); // 캐시가 없는 경우만 출력
           return "Tile Data for ID: " + tileId;
       }
   }
   ```

##### 3. 캐시 갱신 (`@CachePut`)

1. **항상 메서드를 실행**하고 결과를 캐시에 업데이트한다. 

2. 메서드를 실행하면서도 캐시 값을 갱신해야 할 때 사용한다. 

   ```java
   @CachePut(value = "tileCache", key = "#tileId")
   public String updateTileData(String tileId, String newData) {
       System.out.println("Updating data for Tile ID: " + tileId);
       return newData; // 캐시와 반환 값이 동기화
   }
   ```

##### 4. 캐시 무효화 (`@CacheEvict`)

1. 캐시에 저장된 데이터를 삭제한다. 

2. 데이터가 변경되어 캐시를 무효화해야 할 때 사용

3. 속성 

   1. `value`: 캐시 이름을 지정
   2. `key`: 삭제할 캐시 키 지정
   3. `allEntries`: `true`로 설정하면 해당 캐시 이름의 모든 항목 삭제
   4. `beforeInvocation`: 기본값은 `false`. `true`로 설정하면 메서드 실행 전에 캐시를 비운다. 

   ```java
   @CacheEvict(value = "tileCache", key = "#tileId")
   public void removeTileData(String tileId) {
       System.out.println("Evicting cache for Tile ID: " + tileId);
   }
   ```

##### 5. 여러 캐시 동작 (`@Caching`)

1. 여러 캐시 동작(예: `@Cacheable`, `@CachePut`, `@CacheEvict`)을 하나의 메서드에 결합하여 사용

   ```java
   @Caching(
       cacheable = { @Cacheable(value = "tileCache", key = "#tileId") },
       evict = { @CacheEvict(value = "tileCache", key = "#tileId", condition = "#tileId.length() > 5") }
   )
   public String manageTileData(String tileId) {
       System.out.println("Managing data for Tile ID: " + tileId);
       return "Tile Data for ID: " + tileId;
   }
   ```

   

