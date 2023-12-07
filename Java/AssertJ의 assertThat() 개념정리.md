# AssertJ 의 assertThat

---

>[참고 사이트1](https://www.daleseo.com/assertj/)
>
>[참고 사이트2](https://hseungyeon.tistory.com/328)
>
>[참고 사이트3](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=simpolor&logNo=221327833587)

## assertJ 이란? 

- 많은 **assertion을 제공하는 자바 라이브러리**
- 에러 메세지와 테스트 코드의 가독성을 매우 높여준다는 장점을 가진다. 

### Junit 과의 차이점 

- Junit은 자바 언어용 단위 테스트 프레임워크이다. assertThat() 메소드를 가지고 있지만 매개변수가 assertJ 와 다르다. 
- Junit
  - assertThat(actual , matcher )
- assertJ 
  - assertThat(actual ).matcher1().matcher2().matcher3();
- assertJ 를 이용하는것이 훨씬 **가독성이 좋아** 많이 사용된다. 

### Junit 많이 사용되는 matcher 종류 

> [matcher 정리 사이트 ](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=simpolor&logNo=221327833587)

1. allOf

   - 내부에 선언된 모든 매쳐가 정상일 경우 통과한다.
   - ex ) assertThat("myValue", allOf(startsWith("my"), containsString("Val")))

2. is

   - is는 두가지 용도로 사용할 수 있다.
   - A is B와 같이 비교값이 서로 같은지 여부를 확인할 경우
   - is가 있음으로써 쉽게 읽혀지는 테스트 코드로 변경하여 준다.
   - ex )  
     - assertThat("Simple Text", is("Simple Text")); 
     - assertThat("Simple Text", is(equalTo("Simple Text"))); // 위와 동일

3. anything

   - 항상 true를 반환하는 매쳐

4. equalTo

   - 두 값이 같은지 여부를 체크한다. is와 동일하게 사용할 수 있다.
   - ex ) assertThat("foo", equalTo("foo"));

5. any

   - 비교값이 매쳐의 타입과 동일한지 여부를 체크한다. 

   - instanceOf와는 다르게 매쳐의 값은 앞서 비교값의 타입의 자식만 비교할 수 있다.
   - ex ) assertThat(new Canoe(), instanceOf(Canoe.class));

6. not

   - is와 동일하게 두가지 경우로 사용할 수 있다.
   - 내부에 매쳐를 선언할 경우 내부 매쳐의결과를 뒤집는다.
   - ex ) assertThat(cheese, is(not(equalTo(smelly))))

7. nullValue

   - 비교값이 **null**일경우 테스트가 통과한다.
   - ex ) assertThat(cheese, is(nullValue())

8. **containsString**

   - **특정 문자열**이 있는지를 검사한다.
   - ex ) assertThat("myStringOfNote", containsString("ring"));

## assertThat 이란? 

- assertJ 에서 `검증 단계`를 도와주는 메소드이며 , `org.assertj.core.api.Assertions.` 라이브러리에 존재한다.  
  - 검증단계란, 실행 단계가 예상한 대로 동작하는지 검증하는 단계이다. 
- 모든 테스트 코드는 asssertThat() 메서드에서 출발한다. 
- AssertJ에서 제공하는 다양한 메서드를 assertThat()을 이용해 **체이닝 메소드 패턴**을 이용해 **가독성 좋은** 테스트 코드를 작성할 수 있게 한다. 
  - Junit 은 연쇄 호출이기 때문에 가독성이 떨어진다. 

### 형태 

```java
// 1. Junit 인 경우 
public static <T> void assertThat(T actual, Matcher<? super T> matcher)
// actual 인자에 검증대상(`실행 단계`의 결과)을 넣고,
// 이와 비교하는 로직(matcher)을 주입받아 `검증 단계`를 수행
assertThat( actual, matcher )
    

// 2. assertJ 인 경우 
sertThat(테스트 타켓).메소드1().메소드2().메소드3();
```

### 예제

```java
// 1. Junit 
assertThat("Sample string.", is(not(startsWith("Test")))); //  연쇄 호출로 인해 가독성이 떨어진다. 


// 2. AssertJ 
@Test void a_few_simple_assertions() {
	assertThat("The Lord of the Rings").isNotNull() 
	.startsWith("The") 
    .contains("Lord") 
    .endsWith("Rings"); 
}

assertThat("Hello, world! Nice to meet you.") // 주어진 "Hello, world! Nice to meet you."라는 문자열은
				.isNotEmpty() // 비어있지 않고
				.contains("Nice") // "Nice"를 포함하고
				.contains("world") // "world"도 포함하고
				.doesNotContain("ZZZ") // "ZZZ"는 포함하지 않으며
				.startsWith("Hell") // "Hell"로 시작하고
				.endsWith("u.") // "u."로 끝나며
				.isEqualTo("Hello, world! Nice to meet you."); // "Hello, world! Nice to meet you."과 일치합니다.

assertThat(3.14d) // 주어진 3.14라는 숫자는
				.isPositive() // 양수이고
				.isGreaterThan(3) // 3보다 크며
				.isLessThan(4) // 4보다 작습니다
				.isEqualTo(3, offset(1d)) // 오프셋 1 기준으로 3과 같고
				.isEqualTo(3.1, offset(0.1d)) // 오프셋 0.1 기준으로 3.1과 같으며
				.isEqualTo(3.14); // 오프셋 없이는 3.14와 같습니다
```

