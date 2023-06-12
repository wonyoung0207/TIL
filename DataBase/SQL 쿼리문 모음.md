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

