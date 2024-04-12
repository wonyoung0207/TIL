# Lombok 에러 :  The method builder() is undefined for the type ... 

---

>

## 발생 에러 

1. 백엔드 컴파일 시 builder() 를 인식하지 못해 실행되던게 갑짜기 실행이 되지 않음. 
2. lombok을 사용해 builder를 지정 후 사용중이였다. 이때 해당 에러가 발생했다. 
3. 발생 이유
   1.  The method builder() 를 인식하지 못함. 

```js
exception is java.lang.Error: Unresolved compilation problem:   The method builder() is undefined for the type LoginUser] with root cause 
java.lang.Error: Unresolved compilation problem: The method builder() is undefined for the type LoginUser
```

- 코드 일부

  ```java
  // VO
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import lombok.Singular;
  
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class LoginUser implements UserDetails {
  	@NotBlank
  	private String userId;
  	@NotBlank
  	private String password;
  	private String userAuth;
  }
  
  
  // 서비스 
  @Service
  public class LoginService implements UserDetailsService{
  	@Autowired
  	private LoginMapper loginMapper;
  	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  		User user = loginMapper.getUserByUserId(username);
  		if(user == null) {
  			throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다.");
  		}
  		return LoginUser.builder()
  				.userId(user.getUserId())
  				.password(user.getPassword())
  				.userAuth(user.getUserAuth())
  				.failcnt(user.getFailcnt())
  				.fir(user.getFir())
  				.eventPopupTimer(user.getEventPopupTimer())
  				.authority(new SimpleGrantedAuthority("ROLE_USER"))
  				.build();
  	}
  }
  ```

  

## 해결 방법

1. build.gradle 의 lombok 내용 수정 

   1. compileOnly 는 컴파일 시에만 해당 라이브러리가 필요하고 런타임에는 포함되지 않기 때문에 Lombok의 기능이 런타임에 동작하지 않을 수 있다. 
   2. **따라서 `compileOnly` 대신에 `implementation` 또는 `annotationProcessor` 구성으로 의존성을 추가해야 한다.** 

2. `implementation` 

   1. 컴파일 및 실행 시에 모듈에 대한 의존성을 포함시키는 데 사용
   2. 지정된 의존성은 컴파일 시 클래스패스에 포함되며, 런타임 시에도 해당 모듈의 클래스를 사용할 수 있다. 
   3. 해당 의존성은 프로젝트의 결과물(컴파일된 JAR 또는 WAR 파일)에 포함된다. 

3. `annotationProcessor` 

   1. 주로 컴파일 시에만 필요한 의존성을 지정할 때 사용
   2. 컴파일러가 소스 코드에 있는 애노테이션을 처리할 때만 해당 의존성을 사용하고, 런타임 시에는 포함되지 않는다. 
   3. 즉, 불필요한 라이브러리가 런타임 환경에 영향을 주지 않고, 컴파일 시에만 필요한 기능을 사용할 수 있도록 한다. 

   ```js
   // 수정 전 
   compileOnly 'org.projectlombok:lombok'
   
   // 수정 후 
   implementation 'org.projectlombok:lombok'
   annotationProcessor 'org.projectlombok:lombok'
   ```

   