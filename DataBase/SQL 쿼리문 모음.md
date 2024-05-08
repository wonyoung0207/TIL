# SQL 쿼리문 모음

---

> [참고 사이트1](https://sorrow16.tistory.com/155)

## 형태에 따라 다른 값 리턴하는 방법

- **case when then** 문법 사용

  ```sql
  select case t3.tech 
   	when 'C' then '영상'
   	when 'R' then '레이더'
   	when 'S' then '소리'
   	else tech 
   	end as position_string
  from tb_ids_equip t3;
  ```

-  `CASE` 문은 특정 조건에 따라 값을 선택하고 반환하는 조건부 표현식이다. 

   -  각 `WHEN` 절에서는 "tech" 필드의 값이 해당하는 경우에 대한 변환 값을 지정한다.
   -  마지막 `ELSE` 절은 위에서 지정한 값 이외의 값이 나올 경우 기존의 "tech" 값을 그대로 반환한다. 

- `END` 키워드는 `CASE` 문의 끝을 나타내는 역할을 한다. 

  - `END` 키워드는 `CASE` 문이 어디에서 끝나는지 명시적으로 나타내어 문법적인 정확성을 유지하고, 쿼리 실행 엔진에게 `CASE` 문의 끝을 알려준다. 

## coalesce(col1, col2, '대체할 값')

- 데이터베이스 쿼리에서 NULL 값 처리에 유용한 함수로, 인자로 온 값들 중 첫번째로 null이 아닌 값을 찾아 리턴한다. 

  - 따라서 null값이 오면 그 뒤의 값을 보고 null 이 아니면 해당 값을, null이면 또 뒤의 값을 보면서 나아간다. 

- 예시

  ```sql
  SELECT COALESCE(col1, col2, 'kkkk') AS result
  FROM table;
  ```

- 순서

  1. col1이 null이라면 col2로 넘어간다. 
     - 만약 col1이 null 이 아니라면 col1이 리턴된다. 
  2. col2가 null 이라면 'kkkk' 문자열이 리턴된다 ( 왜냐하면 'kkkk' 는 null이 아니기 때문에)
     - null 이 아니라면 col2 가 리턴된다. 

## to_char()

1. **숫자 지정 형식**

   | 지정 형식 | 설명                 | 예                            | 결과     |
   | --------- | -------------------- | ----------------------------- | -------- |
   | 9         | 9로 출력 자릿수 지정 | TO_CHAR(salary, '99999999')   | 24000    |
   | 0         | 자릿수만큼 0을 출력  | TO_CHAR(salary, '09999999')   | 00024000 |
   | $         | 달러 기호            | TO_CHAR(salary, '$9999999')   | $24,000  |
   | L         | 지역 화폐 기호(원)   | TO_CHAR(salary, 'L9999999')   | \24,000  |
   | .         | 명시한 위치에 소수점 | TO_CHAR(salary, '999999.99')  | 24000.00 |
   | ,         | 명시한 위치에 쉼표   | TO_CHAR(salary, '9,9999,999') | 24,000   |

2. 숫자 자리수 표시 형식 지정

   ```sql
   -- 소수 2자리까지 표시. 단,10 이상의 값은 #으로 표시됨 
   to_char(coalesce(t1.position, 0), '0.00k') -- 결과 : 0.20 -> 0.20k ,   85 -> #.##k
   
    -- 소수 2자리까지 표시. 이때 9를 앞에 붙여주면 출력 자릿수를 지정할 수 있다. 
   to_char(coalesce(t1.position, 0), '90.00k') -- 결과 : 0.20 -> 0.20k ,   85 -> 85.00k
   ```

## TO_NUMBER

- TO_NUMBER는 숫자 타입의 문자열을 숫자 데이터 타입으로 변환하는 함수이다.

- 출력 결과는 변하지 않고 데이터 타입만 바뀐다. 데이터 타입이 바뀐 데이터 값은 숫자 타입 함수에서 사용할 수 있다.

- 예시

  ```SQL
  -- TO_NUMBER(number)
  SELECT TO_NUMBER('123') FROM dual;
  ```

## TO_DATE

- TO_DATE는 날짜를 나타내는 문자열을 명시된 날짜로 변환하는 함수 이다. 

- 주로 출력 값을 명시된 형태로 나타나게 하거나 날짜를 계산할 때 사용한다. 

- 예시	

  ```sql
  -- TO_DATE(문자열, '지정 형식')
  SELECT TO_DATE('20210421', 'YYMMDD') FROM dual;
  ```

## Interval 

- 시간 더하기 빼기에 이용 

- 예시 

  ```sql 
  -- postgresql 기준 쿼리 
  -- 하루전날 날짜 출력 
  select now() - Interval '1 day'
  SELECT TO_DATE('20210421', 'YYMMDDHHmm') - interval '3 hour' FROM dual;
  ```


## row_number()

- 각 행에 대해 순서를 지정하는 데 사용한다. 

- 분석함수의 일종으로, 주로 over() 와 함께 쓰인다. 

- 예시

  ```sql
  -- 직업을 기준으로 그룹화 
  -- 직군(job) 별 급여(sal)가 낮은 순으로 순번을 표시
  SELECT empno
      , ename
      , job
      , sal
      , ROW_NUMBER() OVER(PARTITION BY job ORDER BY sal) AS rn
  FROM emp
  WHERE job IN ('MANAGER', 'SALESMAN')
  ORDER BY job
  ```


## partition by 

- 데이터를 파티션으로 나누는 데 사용

- 주로 over() 안에 사용되며, group By 와 비슷하게 동작한다. 

- 예시

  ```sql
  -- 직업을 기준으로 그룹화 
  -- 직업별 급여의 Max값만 표시 
  SELECT empno
   , ename
   , job
   , sal
   , ROW_NUMBER() OVER(PARTITION BY job ORDER BY sal) AS rn
  FROM emp
  WHERE job IN ('MANAGER', 'SALESMAN')
  ORDER BY job
  ```


## rank()

- 순위를 나타낼 때 표시

- 분석함수의 일종으로, 주로 over() 와 함께 사용된다. 

- `row_number()` vs `rank()` vs `DENSE_rank()`

  - row_number() 
    - 행의 순서를 매겨주는 함수로, order by 에 의해서 결과가 달라진다. 
    - 즉, **중복값이더라도 관계없이 순위 매김** 
    - `1위 -> 2위 -> 3위 -> 4위 -> 5위`
  - RANK()
    - 기준을 가지고 순위를 매기고 같은값이 있다면 동일한 순서로 나타낸다. 
    - 즉, **중복 순위 개수만큼 다음 순위 값을 증가 시킴**
    - `1위 -> 2위 -> 2위 -> 2위 -> 5위`
  - DENSE_RANK()
    - **중복 순위가 존재해도 순차적으로 다음 순위 값을 표시함** 
    - `1위 -> 2위 -> 2위 -> 2위 -> 3위`

- 예시 

  ```sql
  -- RANK 함수를 사용하여 순위를 부여할 때는 동일한 급여(sal)인 경우 동일한 순위를 표시한
  SELECT empno
   , ename
   , job
   , sal
   , RANK() OVER(PARTITION BY job ORDER BY sal) AS rnk
  FROM emp
  WHERE job IN ('MANAGER', 'SALESMAN')
  ORDER BY job
  ```


## 소수점 자릿수 제한

- ROUND(소수값, 반올림할 자릿수)

  - 소수점 이하 자릿수를 반올림 한다. 즉, 반올림 자릿수가 2이면 소수 2자리 까지 표현하되, 소수 3번째 자리에서 반올림하여 표기한다. 
  - 예를들어, ROUND(1.234, 2) 인경우 1.23가 출력된다. 

- 예시

  ```sql
  CONCAT(
          ROUND(
              COALESCE(position * 1000, 0) -- null 인경우 0, 그 외는 ( position * 1000 )으로 출력
          , 0), -- 소수점 이하 자릿수를 반올림하여 정수로 변환
          'm'
  ) AS position;
  ```

  

