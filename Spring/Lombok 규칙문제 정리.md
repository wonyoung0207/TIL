# Lombok의 Getter/Setter 네이밍 규칙 문제

---

>

## 문제발생

1. API Request를 요청하고 Lombok 의 `@Data` 를 사용하는 VO로 받았는데 카멜 케이스로 인식을 하지 못하는 문제가 발생했다. 

## 문제해결

1. Lombok의 `@Data` 어노테이션을 사용하면 자동으로 Getter/Setter를 생성하는데, **Lombok은 기본적으로 필드 이름을 Camel Case 스타일로 처리하면서 대소문자를 다르게 인식하는 문제**가 있다.
2. 예시
   1. `rStartPosition` → **Getter: `getRstartPosition()`** (대문자 `S`가 소문자 `s`로 변환됨)
   2. `rEndPosition` → **Getter: `getRendPosition()`** (대문자 `E`가 소문자 `e`로 변환됨)
   3. 즉, Jackson이 JSON을 `Schedule` 객체로 변환할 때 필드명이 `rStartPosition`이어야 하는데, Lombok이 `rstartPosition`으로 처리해서 충돌이 발생하는 것
3. Lombok은 jackson을 사용하는데, jackson의 `@JsonProperty` 를 이용하면 해결이 가능하다. 

```java
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Schedule {
	
	int no;

    // 기존 안되던 변수명
	String rStartPosition; 
	String rEndPosition; 
    
    // 변경 후 정상 동작하는 변수명
	@JsonProperty("rStartPosition") // JSON 키와 정확히 일치하도록 지정
    String rStartPosition;
    @JsonProperty("rEndPosition")
    String rEndPosition;
}

```

