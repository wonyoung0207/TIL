# WITH 와 VIEW 차이점

---

## VIEW

### 정의

- 하나 이상의 기본 테이블이나 다른 뷰를 이용하여 생성되는 **가상 테이블** 

### 특징 

- 한번 만들어 놓으면 DROP 할때까지 없어지지 않는다. 

### 장점

- 논리적 독립성을 제공
- 여러 사용자의 다양한 데이터 요구를 지원
- 데이터 접근 제어 

### 단점

- 삽입 , 삭제 , 갱신 연산에 제한이 있음

### 사용법

```sql
create view empavgsal as select avg(salary) as salavg from employee ; 

-- DML 문의 사용이 제한됨
update empavgsal set salavg = salavg+10; 
-- 오류이유 : 뷰가 집단연산의 경우 뷰를 통한 갱신 연산이 불가능함 
```



---

## WITH

### 정의

- 이름이 부여된 서브쿼리

### 특징

- 한번 실행할 쿼리문 내에 정의되어 있을 경우, 그 쿼리문 안에서만 실행된다. 

### 장점

- 큰장점은 한번 WITH절의 내용을 한번에 올려놓고 계속 재사용한다는것
  -  WITH절에 구문을 여러번 참조하는 쿼리를 만들수록 그 효과가 배로 증가
- 복잡한 SQL에서 동일 블록에 대해 **반복적으로 SQL문을 사용하는 경우** 그 블록에 이름을 부여하여 재사용 할 수 있게 함
  - 따라서 여러 쿼리문이 필요한 경우 **반복사용해야하는 테이블**을 가상 테이블로 만들어 **연속적으로 사용**할 수 있다. 

### 사용법

```sql
-- 반복해서 사용할 수 있음 
WITH EXAMPLE1 AS ( -- 첫번째 Table
SELECT 'EX1' A FROM DUAL 
UNION ALL
SELECT 'EX2' FROM DUAL
), -- with 연결 부분 
EXAMPLE2 AS ( -- 두번째 Table
SELECT 'EX3' A FROM DUAL 
UNION ALL
SELECT A FROM EXAMPLE1 -- 첫번째 가상 테이블 바로 사용 가능 
)
 
SELECT * FROM EXAMPLE2
```

---

## 정리 

### View  사용

- view는 drop 할때까지 남아 있어 계속해서 단일 가상 테이블로 사용할 경우 사용하면 좋다.

### WITH 사용

- with는 하나의 실행쿼리 안에서만 생성된다. 
- 또한 하나의 with 쿼리문으로 계속해서 붙여가며 앞에 만든 가상테이블을 바로 쿼리문안에서 사용할 수 있다. 

