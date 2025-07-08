# SpringBoot JWT의 Claims정리 

---

>

## 결론

- `Claims`는 JWT의 **Payload 부분을 표현한 객체**
- 내부는 **key-value 구조**
- 사용자 정의 정보나 토큰 관련 기본 정보가 들어감
- JWT 인증을 사용할 때 핵심 객체 중 하나

## JWT 구조 

JWT는 세 부분으로 구성된다.

```
Header.Payload.Signature
```

예를 들어:

```bash
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9. # Header
eyJ1c2VySWQiOiIxMjM0Iiwicm9sZSI6IlVTRVIiLCJleHAiOjE2ODU5MjAwMDB9. # Payload
[signature] # Signature
```

- 이 중 **Payload 부분**이 바로 Claims 이다.

## Claims란?

Spring Boot + jwt(JWT) 라이브러리 사용 시:

```
import io.jsonwebtoken.Claims;
```

- `Claims`는 **Map<String, Object>** 형태로 동작한다. 
- 즉, **JWT 안에 들어 있는 데이터를 쉽게 꺼낼 수 있게 도와주는 객체**이다. 

## 사용 예시

```java
public Claims getClaims(String token) {
    return Jwts.parser()
               .setSigningKey(secretKey) // JWT 서명 키
               .parseClaimsJws(token)
               .getBody();              // 여기서 Claims 리턴
}
```

```java
Claims claims = getClaims(token);
String userId = claims.get("userId", String.class);
String role = claims.get("role", String.class);
Date expiredAt = claims.getExpiration();
```

## Claims에 들어가는 일반적인 정보

| Key      | 설명                                   |
| -------- | -------------------------------------- |
| `sub`    | 주제(subject), 일반적으로 user ID      |
| `exp`    | 만료시간 (expiration)                  |
| `iat`    | 발급 시간 (issued at)                  |
| `iss`    | 발급자 (issuer)                        |
| `custom` | 사용자 정의 예: role, email, siteId 등 |