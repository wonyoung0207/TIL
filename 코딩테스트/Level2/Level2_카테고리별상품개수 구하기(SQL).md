

# < Level 2 > 

# 카테고리별상품개수 구하기 

> SUBSTRING(), COUNT()

---

## 문제설명 

- `PRODUCT` 테이블에서 상품 카테고리 코드(`PRODUCT_CODE` 앞 2자리) 별 상품 개수를 출력하는 SQL문을 작성해주세요. 결과는 상품 카테고리 코드를 기준으로 오름차순 정렬해주세요.

- 상품 별로 중복되지 않는 8자리 상품코드 값을 가지며, 앞 2자리는 카테고리 코드를 의미합니다.

  | PRODUCT_ID | PRODUCT_CODE | PRICE |
  | ---------- | ------------ | ----- |
  | 1          | A1000011     | 10000 |
  | 2          | A1000045     | 9000  |
  | 3          | C3000002     | 22000 |
  | 4          | C3000006     | 15000 |
  | 5          | C3000010     | 30000 |
  | 6          | K1000023     | 17000 |


## 제한사항 

## 입출력 예

- 상품 카테고리 코드 별 상품은 아래와 같으므로,
  - `A1`: `PRODUCT_ID`가 1, 2 인 상품
  - `C3`: `PRODUCT_ID`가 3, 4, 5 인 상품
  - `K1`: `PRODUCT_ID`가 6 인 상품

| CATEGORY | PRODUCTS |
| -------- | -------- |
| A1       | 2        |
| C3       | 3        |
| K1       | 1        |

## 풀이 

1. 내가 푼 풀이 

   ```java
   SELECT SUBSTRING(PRODUCT_CODE, 1, 2) AS CATEGORY, COUNT(SUBSTRING(PRODUCT_CODE, 1, 2)) AS PRODUCTS FROM PRODUCT
   GROUP BY SUBSTRING(PRODUCT_CODE, 1, 2)
   ```
   


---

## 사용된 개념

1. 문자열 추출

   1. SUBSTRING(str, 시작지점, 문자길이 ) OR SUBSTR(str, 시작지점, 문자길이 )

      - 시작지점은 1부터 시작 

      ```SQL
      SELECT SUBSTRING(PRODUCT_CODE, 1, 2) FROM PRODUCT
      -- 처음 2글자 출력 
      ```

   2. SUBSTRING() 과 SUBSTR() 차이점

      1. SUBSTR이라고 써도 되고, SUBSTRING으로 써도 된다. 둘은 완전히 동일하다.
