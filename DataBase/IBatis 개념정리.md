# IBatis 개념정리

---

>IBatis에 대해 정리해본다. 
>
>[참고 사이트1](https://yongku.tistory.com/entry/IBATISXML-%EB%8F%99%EC%A0%81-%ED%83%9C%EA%B7%B8-%EC%A0%95%EB%A6%AC)
>
>[참고 사이트2](https://velog.io/@gillog/MyBatis-iBatis-MyBatis-%EB%B9%84%EA%B5%90-%EC%A0%95%EB%A6%ACDynamic-Query)

## IBatis

### 정의

- SQL을 기반으로 한 데이터베이스(DB)와 자바(Java) 등을 연결시켜 주는 역할을 하는 영속성 **프레임워크**이다. 
  - 이러한 연결은 프로그램의 소스코드에서 **SQL 문장을 분리**하여 **별도의 XML 파일로 저장**하고 이 **둘을 서로 연결시켜주는 방식**으로 작동한다.

### IBATIS와 MYBATIS 차이

- **IBATIS**는 MYBATIS의 **전버전**으로 IBATIS의 버전은 ~2.3 버전까지고, MYBATIS는 IBATIS가 2.5버전으로 업데이트되면서 이름이 변경되었다.
  - IBATIS에서 MYBATIS로 변경되면서 Apache Project 팀에서 Google Code팀으로 이동되었고, 이에 따라 이름과 문법이 많이 변경되었다.
- **패키지 내부구조**도 변경되었는데 `iBATIS`의 패키지 구조 `com.ibatis.*`에서 `MyBatis` 패키지 구조`org.apache.ibatis.*`로 변경 되었다.
- **JDK** 요구버전 변경 
  - Java 요구버전도 `iBATIS`는 JDK 1.4 이상에서 사용 가능하지만, `MyBatis`는 JDK 1.5 이상에서 사용 가능하도록 변경되었다.
  - *MyBatis 3.2 이상 버전은 JDK 1.6 이상 요구*

---

## IBatis - XML 동적쿼리 

- Dynamic 쿼리 라고도 한다. 
  - `iBatis`와 `MyBatis`에서 지원하는 **`Dynamic Query`는 상황에 따라 분기 처리를 통해 SQL을 동적으로 만드는 것**이다. 
- **태그의 형태**로 사용된다. 

### 사용되는 속성 

1. `prepend` : 태그 조건에 일치하여 **sql문에 선행**하여 붙을 속성

   - 따라서 태그 조건이 먼저 실행된 후 **추가되는 속성**이다. 

   - 예시

     ```xml
     <!-- 태그 조건 : property의 값이 Y와 같을때  -->
     <!-- prepend : 태그 조건이 만족했을 때 'EQUIP_TYPE = 1' 앞에 AND 가 붙는다.  -->
     <isEqual prepend="AND" property="useYn" compareValue="Y">
         EQUIP_TYPE = 1
     </isEqual>
     
     <!-- 태그 조건 : property 값이 비지 않았을 때  -->
     <!-- prepend : EQUIP_TYPE = 1 앞에 AND 가 붙는다. -->
     <isNotEmpty property="search_val" prepend="AND">
         EQUIP_TYPE = 1
     </isNotEmpty>
     ```

2. `property` : 매개 변수 명

3. `compareProperty` : 비교할 다른 매개 변수 명

4. `compareValue` : 비교 대상이 될 값

- 속성값 예시 

  ```xml
  <!-- useYn 이 Y 일때만 EQUIP_TYPE =1 조건을 실행 -->
  WHERE 1=1
  <isEqual prepend="AND" property="useYn" compareValue="Y">
      EQUIP_TYPE = 1
  </isEqual>
  ```

### 시작 공통 태그 

1. \<**sqlMap** namespace="apig_apiApplication">
   - 하나의 XML 파일 내에서 SQL 구문과 Java 클래스를 **그룹화**하는 데 사용된다. 
2. \<**typeAlias** alias="appVO" type="com.mesim.apig.apiApplication.vo.ApiApplicationVO"/>
   - Java 클래스의 별칭(alias)과 해당 클래스를 정의하는 패키지 경로(type)를 설정한다. 
3. \<**sql** id="app_tb">APIG_APPLICATION\</sql>
   - SQL 구문의 id와 해당 SQL 구문을 나타낸다. 
   - SQL 구문의 id는 해당 SQL을 참조할 때 사용한다.

### 태그 종류

#### 1. 단일 태그 

| Tag                        | Note                                        |
| -------------------------- | ------------------------------------------- |
| `<isPropertyAvailable>`    | property값이 유효할 경우 쿼리를 실행        |
| `<isNotPropertyAvailable>` | property값이 유효하지 않을 경우 쿼리를 실행 |
| `<isNull>`                 | property값이 null일 경우 쿼리를 실행        |
| `<isNotNull>`              | property값이 null이 아닐 경우 쿼리를 실행   |
| `<isEmpty>`                | property값이 비어있을경우 쿼리를 실행       |
| `<isNotEmpty>`             | property값이 비어있지 않을 경우 쿼리를 실행 |

#### 2. 동적 태그 

1. \<isEqual>

   - property의 값이 같을때만 태그내 쿼리를 실행합니다.

     ```xml
     WHERE 1=1 
     <isEqual prepend="AND" property="useYn" compareValue="Y">
         EQUIP_TYPE = 1
     </isEqual>
     <!-- useYn 이 Y 일때만 EQUIP_TYPE =1 조건을 실행합니다. 실행시 EQUIP_TYPE=1 앞에 AND 조건이 붙는다.  -->
     ```

2. \<isNotEqual>

   - property의 값이 같지 않을 때만 태그내 쿼리를 실행합니다.

     ```xml
     WHERE 1=1
     <isNotEqul prepend="AND" property="useYn" compareValue="N">
             EQUIP_TYPE = 1
     </isNotEqual>
     <!-- useYn 이 N이 아닐 때만 EQUIP_TYPE=1 조건을 실행합니다. 실행시 EQUIP_TYPE=1 앞에 AND 조건이 붙는다. -->
     ```

3. \<isGreaterThan>

   - property의 값이 비교값보다 클경우 쿼리를 실행합니다.

     ```xml
     WHERE 1=1
     <isGreaterThan prepend="AND" property="age" compareValue="19">
              JOIN_YN = 'Y'
     </isGreaterThan>
     <!-- age 값이 19 보다 클경우 JOIN_YN='Y' 조건을 실행합니다. 실행시 JOIN_YN = 'Y' 앞에 AND 조건이 붙는다.-->
     ```

4. \<isGreaterEqual>

   - property의 값이 비교값보다 같거나 클경우 쿼리를 실행합니다.

     ```xml
     WHERE 1=1
     <isGreaterEqual prepend="AND" property="age" compareValue="18">
               JOIN_YN = 'Y'
     </isGreaterEqual>
     <!-- age 값이 18 이거나 이보다 클경우 JOIN_YN='Y' 조건을 실행합니다. 실행시 JOIN_YN = 'Y' 앞에 AND 조건이 붙는다.-->
     ```

5. \<isLessEqual>

   - property의 값이 비교값보다 작거나 같을 경우 쿼리를 실행합니다.

     ```xml
     WHERE 1=1
     <isLessEqual prepend="AND" property="age" compareValue="18">
             JOIN_YN = 'N'
     </isLessEqual>
     <!-- age 값이 18 이거나 이보다 작을경우 JOIN_YN='N' 조건을 실행합니다. 실행시 JOIN_YN = 'N' 앞에 AND 조건이 붙는다. -->
     ```

#### 3. 파라미터 조건 태그 

1. \<isParameterPresent>

   - 파라메터가 있을 경우 쿼리를 실행

     ```xml
     <isParameterPresent prepend="WHERE">           
         1=1
     </isParameterPresent>
     <!-- 파라메터값이 넘어왔을 경우에만 WHERE 조건 붙음 -->
     ```

2. \<isNotParameterPresent>

   - 파라메터값이 없을 경우 쿼리를 실행

     ```xml
     WHERE 1=1<isNotParameterPresent prepend="AND">
          TYPE = 'DEFAULT'
     <isNotParameterPresent>
     <!-- 파라메터값이 없을 경우에만 TYPE = 'DEFAULT' 쿼리 실행 -->
     ```

3. \<iterate>

   - 배열 타입의 파라메터를 받을 때 활용

   - `<iterate>` 태그의 `property` 속성에는 반복할 컬렉션 변수(리스트나 배열) 를 지정한다. 

     - `open` 속성은 반복문 시작 전에 출력할 문자열이며, `close` 속성은 반복문 종료 후에 출력할 문자열이다. 
     
     ```xml
     WHERE 1=1
     <isNotEmpty prepend="AND" property="empIdArray">
         EMP_ID IN
         <iterate open="(" close=")" conjunction="," property="empIdArray">
            #empIdArray[]#
         </iterate>
     </isNotEmpty>
     <!-- empIdArray배열에 값이 있을경우 'AND EMP_ID IN ...' 실행  -->
     <!-- 배열의 값을 빼내어 콤마로 구분하여 괄호 '(' , ')' 내에 넣는다. -->
     <!-- ex) ('111', '222', 333', '444') -->
     ```

4. \<dynamic>

  - 하위 태그에 일치되는 내용이 존재할 경우 where절을 붙인다. ( 가장 처음 일치요소의 prepend="AND" 는 생략된다. ) 

    ```xml
    <dynamic prepend="WHERE">
        <isEqual prepend="AND" property="empId" comapareValue="123">
                    VACATION = 'TRUE'
        </isEqual>
    </dynamic>
    <!-- empId 파라메터 값 123이라면 <isEqual>태그의 prepend는 생략되고 -->
    <!-- WHERE 절이 붙어 WHERE VACATION = 'TRUE' 쿼리가 실행된다. -->
    ```

    

 