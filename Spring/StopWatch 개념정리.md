# StopWatch 개념정리

---

>[참고 사이트1](https://java.ihoney.pe.kr/506)

## StopWatch

### 정의

- **총 실행시간** 중 설정한 **구간이 얼마의 시간을 차지**하는지 퍼센트로 알려준다. 

```java
@Test
public void testAddLongAndBigDecimal() {
    BigDecimal bigDecimal = BigDecimal.valueOf(0, 0);
    Long longType = 0L;

    stopWatch.start("Long type");
    for(int i = 0; i < 1_000_000; i++) {
        longType += 1L;
    }
    stopWatch.stop();

    stopWatch.start("BigDecimal type"); 
    for(int i = 0; i < 1_000_000; i++) {
        bigDecimal = bigDecimal.add(BigDecimal.ONE);
    }
    stopWatch.stop();
    System.out.println(stopWatch.shortSummary());
    System.out.println(stopWatch.getTotalTimeMillis());
    System.out.println(stopWatch.prettyPrint());
}
```

- 결과 

```
-----------------------------------------
ms     %     Task name
-----------------------------------------
00017  040%  Long type
00025  060%  BigDecimal type
```

