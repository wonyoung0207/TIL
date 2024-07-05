# Spring AuthException 처리방법

---

>

## AuthExceptionHandler 

1. **Spring boot 의 SpringSecurity** 를 이용하는 경우 사용하는 **exception 방법**이다. 
2. 로그인 과정 중 사용되는 JwtAuthenticationProvider 클래스의 authenticate() 메소드에서 로그인 시 발생할 수 있는 에러에 대해 처리했다. 

## Auth 관련 Exception 에러 

1. UsernameNotFoundException
   1. 인증 과정에서 **사용자를 찾을 수 없을 때** 발생
2. BadCredentialsException
   1. 사용자가 **입력한 인증 정보가 잘못**되었을 때 발생
3. AccountExpiredException
   1. 사용자의 **계정이 만료**되었을 때 발생
4. LockedException
   1. 사용자의 **계정이 잠겨 있을 때** 발생
5. DisabledException
   1. 사용자의 **계정이 비활성화**되어 있을 때 발생
6. CredentialsExpiredException
   1. 사용자의 **자격 증명(비밀번호 등)이 만료**되었을 때 발생
7. InsufficientAuthenticationException
   1. **인증 정보가 부족**할 때 발생
8. SessionAuthenticationException
   1. **세션과 관련된 문제**가 발생했을 때 발생

### 동작 순서

1. RESTful 시스템으로, AuthController 에서 login 에 대한 리소스를 호출
2. SpringSecurity 의 로그인 인증 과정 시작
   1. AuthController의 파라미터로 넘어온 Id, Password 가 로그인에 적합한지 판별해야함 
3. 판별을 위해 AuthenticationProvider 가 호출된다. 
   1. SpringSecurity 에서 사용되는 인증 정보는 Authentication 객체여야 한다. 
   2. 따라서 authenticate() 의 파라미터는 Authentication 객체이다. 
   3. Authentication 객체 안에는 파라미터인 Id, Password 가진다. 
   4. 여기선 AuthenticationProvider 상속받은 JwtAuthenticationProvider 클래스의 authenticate() 메소드 호출 
4. authenticate() 에서 로그인 정보 판별 
5. 비적합한 경우 Exception 발생 
6. AuthExceptionHandler에서 Exception 처리  

## 사용예제

1. **AuthController** 클래스 에서 login() 메소드 구현 

   ```java
   // AuthController.java
   
   @PostMapping("/auth")
   public ResponseEntity<ApiResult> login(@RequestBody LoginUser loginUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
   
       logger.info("Login Attempt => {}", loginUser.getUserId());
       // 아이디 , 패스워드 인코딩해서 비교 . 에러나면 throw
       Authentication authentication = authenticationManagerBuilder.getObject().authenticate(loginUser.toAuthentication()); 
       String token = jwtTokenProvider.createToken(authentication);
   
       jwtTokenProvider.addCookie(token, response);
       int cnt = userService.getCount();
       userService.failReset(loginUser.getUserId());
       logger.info("Login Success => {}", loginUser.getUserId());
   
       return ApiResult.builder()
               .ok().success()
               .data("accessToken", token)
               .data("userAuth",((LoginUser) authentication.getPrincipal()).getUserAuth())
               .data("fir",((LoginUser) authentication.getPrincipal()).getFir() )
               .data("eventPopupTimer",((LoginUser) authentication.getPrincipal()).getEventPopupTimer() )
               .data("name",userService.getUser( loginUser.getUserId()) )
               .response();
   }
   ```

2. **JwtAuthenticationProvider** 클래스에서 login시 발생할 수 있는 에러 사항 처리 

   ```java
   @Component
   public class JwtAuthenticationProvider implements AuthenticationProvider {
   
   	protected Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);
   	
   	@Autowired
   	private LoginService loginService;
   	
   	@Autowired
   	private PasswordEncoder passwordEncoder;
   
   	@Override
   	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
   				
   		if(authentication.getPrincipal() == null) {
   			throw new BadCredentialsException("Bad credentials.");
   		}
   		
   		// 로그인 시도한 id, pwd 값 
   		String userId = String.valueOf(authentication.getPrincipal()); 
           String password = String.valueOf(authentication.getCredentials());
   		if(userId == null) {
   			throw new BadCredentialsException("Bad credentials.");
   		}
   		
   		LoginUser loginUser = (LoginUser) loginService.loadUserByUsername(userId);
   
   		// 비밀번호 실패 카운트 에러 
   		if(loginUser.getFailcnt()>4) {
   			throw new BadCredentialsException("failcnt");
   		}
   		
   
   		// 비밀번호 틀림 에러 
   		if(!password.equals(loginUser.getPassword())) {
   			loginService.setfailcntAdd(userId);
   			logger.info("Login Failed Bad credentials => {}", loginUser.getUserId());
   			throw new BadCredentialsException("Bad credentials.");
   		}
   
   		// 계정 만료 에러 
   		if(loginUser.getAvailableAccount().equals("n")) {
   			logger.info("Login Failed unavaliableAccount => {}", loginUser.getUserId());
   			throw new AccountExpiredException("unavaliableAccount");
   		}
   		
   		return new UsernamePasswordAuthenticationToken(loginUser, password, loginUser.getAuthorities());
   	}
   	@Override
   	public boolean supports(Class<?> authentication) {
   		return true;
   	}
   }
   ```

3. 발생한 **exception** 에러를 AuthExceptionHandler 클래스에서 예외 처리 

   ```java
   @RestControllerAdvice
   public class AuthExceptionHandler {
       // 사용자 찾을 수 없는경우 호출 
   	@ExceptionHandler(UsernameNotFoundException.class)
   	public ResponseEntity<ApiResult> usernameNotFound(UsernameNotFoundException e) {
   		return ApiResult.builder()
   				.ok().failure()
   				.error(AuthErrorCode.WRONG_INFO)
   				.response();
   	}
   	
       // 입력값 중 잘못된 인증정보 입력시 호출 
   	@ExceptionHandler(BadCredentialsException.class)
   	public ResponseEntity<ApiResult> usernameNotFound(BadCredentialsException e) {
   		if(e.getMessage().equals("failcnt")) {
   			return ApiResult.builder()
   					.ok().failure()
   					.error(AuthErrorCode.DISABELD_MESSAGE)
   					.response();
   		}
   		return ApiResult.builder()
   				.ok().failure()
   				.error(AuthErrorCode.WRONG_INFO)
   				.response();
   	}
   
       // 계정 만료시 호출 
   	@ExceptionHandler(AccountExpiredException.class)
       public ResponseEntity<ApiResult> handleAccountExpiredException(AccountExpiredException e) {
   		if(e.getMessage().equals("unavaliableAccount")) {
   			return ApiResult.builder()
   					.ok().failure()
   					.error(AuthErrorCode.DISABLE_ACCOUNT)
   					.response();
   		}
   		return ApiResult.builder()
   				.ok().failure()
   				.error(AuthErrorCode.WRONG_INFO)
   				.response();
       }
   
   	@ExceptionHandler(InvalidTokenException.class)
   	public ResponseEntity<ApiResult> invalidTokenException(AuthenticationException e) {
   		
   		return ApiResult.builder()
   				.status(HttpStatus.BAD_REQUEST).failure()
   				.error(AuthErrorCode.AUTH_NOT_VAILD)
   				.response();
   	}
   }
   ```

   