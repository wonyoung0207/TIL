# Gradle 이용 test 코드 실행

---

>

## test  코드 경로 

1. Gradle은 기본적으로 `src/test/java` 하위의 모든 테스트를 실행한다. 
2. 하지만 `--tests`를 사용하면 **특정 테스트 클래스 또는 메서드만 선택 실행**할 수 있다. 

## --tests 형태 

| 명령어                          | 대상                                                |
| ------------------------------- | --------------------------------------------------- |
| `--tests RelayTest`             | `RelayTest` 클래스 전체 실행                        |
| `--tests com.meta.tt.RelayTest` | FQCN(전체 경로)으로 실행                            |
| `--tests *RelayTest*`           | 이름에 `RelayTest`가 포함된 모든 클래스             |
| `--tests *Test.relayServer`     | 이름이 `relayServer`인 메서드 전체 (여러 클래스 중) |

## 실행 버튼 이용 예시

1. 테스트 클래스나 테스트 메서드 위에 마우스를 올리면 뜨는 버튼을 이용 
   1. `@Run Test    @Debug Test`

#####  "RUN AND DEBUG" 탭에서 중지

1. 좌측 사이드바에서 "Run and Debug" (▶ 아이콘) 클릭



## gradle 커맨드 이용 예시

1. ```bash
   gradle test --tests RelayTest.relayServer
   ```

   1. RelayTest 클래스에서 relayServer 메소드를 실행한다. 

```java
public class RelayTest {

    @Test
    public void relayServer() {
        // 테스트 코드
    }

    @Test
    public void anotherTest() {
        // 또 다른 테스트
    }
}
```

