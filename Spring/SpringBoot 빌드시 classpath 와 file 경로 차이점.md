# classpath 와 file 경로 차이점

---

>
>
>

## 결론

1. classpath는 jar 파일 내부에서 파일을 탐색할 때 사용하고, file은 JAR 내부가 아닌, JAR 외부의 파일을 직접 참조할 때 사용해야 함.
2. `classpath:`와 `file:`은 Spring Boot에서 설정 파일을 로드할 때 경로를 해석하는 방식이 다름.
   1. **즉, `classpath:`는 Java의 `클래스패스(classpath)`를 기준으로, `file:`은 파일 시스템의 절대/상대 경로를 기준으로 동작함.**

## `classpath:`와 `file:` 차이점

| 사용 상황                                  | `classpath:` 사용              | `file:` 사용                         |
| ------------------------------------------ | ------------------------------ | ------------------------------------ |
| JAR 내부의 리소스를 로드할 때              | ✅ 가능 (`src/main/resources/`) | ❌ 불가능                             |
| JAR 외부의 설정 파일을 로드할 때           | ❌ 불가능                       | ✅ 가능 (`conf/`, `C:/...` 경로 가능) |
| 실행 디렉토리에 있는 파일을 로드할 때      | ✅ 가능                         | ✅ 가능                               |
| JAR 외부 특정 폴더에 있는 설정을 로드할 때 | ❌ 불가능                       | ✅ 가능                               |

## 예제 코드

```properties
# classpath 사용 (jar 파일 내부에 있는 xml만 사용 가능)
logging.config=classpath:logback-prod.xml

# file 사용 (외부 conf 폴더에 있는 xml 사용 가능 )
logging.config=file:conf/logback-prod.xml
```

