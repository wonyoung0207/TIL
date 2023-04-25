# XML 의 Self-Closing Tag 개념정리 

---

>

## Self-Closing Tag

### 정의

- XML에서 **값이 없는 태그**를 표시할때 사용한다. 

### 형태

- `<div/>`와 같이 `<div></div>`처럼 시작 태그와 끝 태그가 모두 있는 경우도 있지만
  - 값이 없는 태그는 보통 `<div/>`와 같이 **시작 태그에 / 넣어 표시한다.**
  
- 이러한 값이 없는 태그를 처리할 때는 SAX 파서에서 **`characters()` 메서드**를 사용하여 **빈 문자열("")이 전달**될 경우에 대해 **처리**를 해준다.


### 예시

```java
public class BaseFileHandler extends DefaultHandler{ 
    ...
    
    public void characters(char[] ch, int start, int length) { 
    	str = new String(ch, start, length);
        if(str.trim().equals("")){ // Self-Closing Tag 의 값을 처리하기 위한 조건문
            str = null;
        }
    }
}
```

