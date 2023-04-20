# xml파일의 AOP 태그 개념정리 

---

>이 글에서는 **xml의 aop 태그에 대해 학습한다.** 

## aop ( 관점 지향 프로그래밍 )

### 정의 

- 핵심 기능과 공통 기능을 분리 시켜놓고, **공통 기능**을 필요로 하는 핵심 기능들에서 사용하는 방식
  - **코드의 재사용**을 편리하게 할 수 있게 만듬 

### 사용하는 곳 

- 스프링에서 AOP 구현은 Proxy를 이용
  - 예를 들어, 공통 기능을 핵심 기능이 **시작하기 전**과 **끝난 후**에 사용을 하겠다고 설정을 해두었을 때
  - aop 태그를 이용해 공통 기능을 핵심기능의 시작전과 끝난 후 부분에 쉽게 넣었다 뺐다 할 수 있다. 
- **공통 기능과 핵심 기능이 직접적으로 접촉하는 것이 아니라 Proxy를 통해서 수행**

### aop Element 종류

1. \<aop:config>
   1. AOP 설정에서 **가장 루트**가 되는 엘리먼트
   2. \<aop:config>는 여러번 사용할 수 있으며 하위에는 **포인트컷**과 **aspect 엘리먼트가 위치**할 수 있다.
   3. 크게 2의 하위 엘리먼트로 나눌 수 있다. 
      - **pointcut** : **핵심** 기능 엘리먼트 
      - **advisor** : **공통** 기능 엘리먼트 
2. \<aop:pointcut>
   1. 관심사에 해당하는 메소드이며 어떤 부분을 기준으로 할 것인지 설정하는 부분
      - 핵심 기능 엘리먼트라고 봐도 된다. 
   2. 여러개 정의를 할 수 있으며 유일한 ID를 할당하여 aspect 를 설정할 때 포인트컷을 참조하는 용도로 사용된다.
3. \<aop:aspect>
   1. **핵심 관심**(비지니스 로직)에 해당하는 **포인트컷 메소드**와 **횡단 관심(AOP)**에 해당하는 **어드바이스** 메소드를 **결합**하기 위해 사용된다.
4. \<aop:advisor>
   1. 포인트컷과 어드바이스를 결합하는 용도로 사용 
   2. aspect와 같은 기능을 하지만 aspect의 경우엔 ID+Method를 꼭 알아야 접근이 가능하다. 
   3. 하지만 어드바이스의 객체 아이디를 모르거나 아이디가 확인이 안되면 애스팩트를 설정할 수 없기 때문에 advisor를 사용한다. 

### aop Advidsor Element 종류

1. \<aop:before>
   - 메서드 실행 전에 advice실행
2. \<aop:after-returning> 
   - 정상적으로 메서드 실행 후에 advice실행
3. \<aop:after-throwing> 
   - 메서드 실행중 exception 발생 시 advice실행
4. \<aop:after> 
   - 메서드 실행중 exception이 발생하여도 advice실행
5. \<aop:around> 
   - 메서드 실행 전/후 밑 exception 발생시 advice 실행
6. \<aop : config> 
   - aop 설정자 

### 예시 

```xml
// 1. Root Element가 될 aop:config
<aop:config>
    //2. pointcut 지정
    <aop:pointcut expression="execution(포인트컷 표현식)" id="allPointcut"/>

    //3. aspect Element 설정
    <aop:aspect ref="log">
        // 4. Advice Element 설정
        // 핵심기능인 pointcut 의 설정 메소드가 실행하기 전에 aop:before의 메소드인 beforeLog() 가 실행된다. 
        <aop:before method="beforeLog" pointcut-ref="allPointcut"/>
    </aop:aspect>
</aop:config>
```

1. \<aop:config>로 AOP 설정을 한다.
2. \<aop:aspect>의 ref 속성에 advisor 클래스의 빈 id를 지정한다.

3. \<aop:pointcut>의 expression에 execution 명시자를 사용해서 특정 관심사를 지정한다.

4. execution(* method1())과 같이 설정하면 모든 패키지, 모든 클래스의 method1() 메소드 호출이 관심사가 된다.

5. advisor 을 이용해 관심사의 전, 후 등등에 실행하고 싶은 aop를 지정할 수 있다. 
