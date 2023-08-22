# Mybatis 표기법 자동 매핑

---

>[참고 사이트1](https://hayden-archive.tistory.com/326)
>
>[참고 사이트2](https://deersoul6662.tistory.com/232)
>
>[mybatis 공식 문서](https://mybatis.org/mybatis-3/ko/configuration.html)

## 사용이유

- **Spring에서 Mybatis 사용시** DTO (Java 객체인 VO 를 뜻한다. ) 를 통해 결과값을 전달한다. 
  - 이때 DB의 필드명으로 많이 사용되는 **`스네이크 케이스` 를 Java에서 사용하는 표기법인 `파스칼 케이스` 로의 매핑작업이 필요하게 된다.** 
  - 예를들어, 쿼리에서는 `user_name` 으로 DTO에 담게 되는데, DTO는 Java의 표기법인 `userName` 으로 변수를 사용한다. 
    - 이때 변환하지 않는다면 vo 필드명과 DB 컬럼명이 달라서 null값으로 나옴
  - 따라서 xml 에서 result 값으로 DTO에 담아줄 때, `파스칼 케이스`로 변환하는 작업이 필요하게 된다. 
- 이런 표현식 변환을 Spring에서 설정 하나로 자동처리 해줄 수 있다. 

## 변경 방법

### 1. mybatis-config.xml 설정 파일에 조건 추가

```xml
<configuration>
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```

### 2. resultMap 이용 

- 해당 쿼리에서 리턴할 데이터를 mybatis에서 제공하는 resultMap 에 담아서 쿼리가 종료될 때 리턴하면 된다.
  - HashMap 형태로 받든, VO로 형태로 받든 resultMap을 사용하면 자유롭게 변환이 가능하다.

```xml
<mapper namespace="{dao path}">
    <resultMap id="selectInfo" type="{vo path}">
        <result property="userId" column="USER_ID"/>
        <result property="userName" column="USER_NAME"/>
    </resultMap>
 
    <select id="getUserInfo" resultMap="selectInfo">
        SELECT 
            USER_ID,USER_NAME
        FROM user
    </select>
</mapper>
```

### 3. 필드명마다 Alias를 이용

- 가장 이해하기 쉽지만, 나중에 유지보수에 정말 안좋음

```xml
<mapper namespace="{dao path}">
    <select id="getUserInfo" resultType="java.util.Map">
        SELECT 
            USER_ID AS "userId",
            USER_NAME AS "userName"
        FROM user
    </select>
</mapper>
```

### 4. apllication.properties에 명시 

- 가장 쉬운 방법이다. 

```properties
mybatis.configuration.map-underscore-to-camel-case=true
```
