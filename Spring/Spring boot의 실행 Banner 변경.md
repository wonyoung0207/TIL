# Spring Boot 의 실행 Banner 변경

---

>[참고 사이트1](https://atoz-develop.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-%EB%B0%B0%EB%84%88-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0-%EB%B3%80%ED%99%98-%EC%82%AC%EC%9D%B4%ED%8A%B8-%EC%B6%94%EC%B2%9C-%EB%B0%8F-color-%EB%B3%80%EA%B2%BD)
>
>[배너 사이트1](https://devops.datenkollektiv.de/banner.txt/index.html)
>
>[배너 사이트2](https://patorjk.com/software/taag/#p=&f=Graceful&t=Type%20Something%20)

## Banner 만드는 방법

1. `banner.txt` 파일 생성 후 원하는 메시지 작성
   1.  이 파일은 Spring Boot 실행 시 자동으로 로딩됨
   2. **'text to ascii art', 'spring boot banner generator'** 등으로 검색
      1. 나는 "larry3d" 로 함 
2. banner.txt에 동적으로 정보 넣기



## banner.txt에 동적으로 정보 넣기

1. Spring Boot는 `banner.txt`에서 **특정 변수들을 자동으로 치환**해 표출한다. 

| 변수                               | 설명                            |
| ---------------------------------- | ------------------------------- |
| `${application.version}`           | `MANIFEST.MF`에 정의된 앱 버전  |
| `${spring-boot.version}`           | 현재 사용 중인 Spring Boot 버전 |
| `${application.formatted-version}` | `v1.0.0` 형식 등으로 출력       |
| `${java.version}`                  | 현재 Java 버전                  |

## `.properties`로 배너 위치 변경 (선택 사항)

```properties
# application.properties
spring.banner.location=classpath:/my-custom-banner.txt
```

## 배너 비활성화 (끄기)

```properties
# application.properties
spring.main.banner-mode=off
```