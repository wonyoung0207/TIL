

# < Level 1 > 

# 식품분류별 가장 비싼 식품의 정보 조회 

> 서브쿼리 , MAX() , IN(), GROUP BY 사용 

---

## 문제설명 

- `FOOD_PRODUCT` 테이블에서 식품분류별로 가격이 제일 비싼 식품의 분류, 가격, 이름을 조회하는 SQL문을 작성해주세요. 이때 식품분류가 '과자', '국', '김치', '식용유'인 경우만 출력시켜 주시고 결과는 식품 가격을 기준으로 내림차순 정렬해주세요.'

| Column name  | Type        | Nullable |
| ------------ | ----------- | -------- |
| PRODUCT_ID   | VARCHAR(10) | FALSE    |
| PRODUCT_NAME | VARCHAR(50) | FALSE    |
| PRODUCT_CD   | VARCHAR(10) | TRUE     |
| CATEGORY     | VARCHAR(10) | TRUE     |
| PRICE        | NUMBER      | TRUE     |


## 제한사항 

- 

## 입출력 예

| CATEGORY | MAX_PRICE | PRODUCT_NAME |
| -------- | --------- | ------------ |
| 식용유   | 6500      | 맛있는산초유 |
| 과자     | 1800      | 맛있는맛동산 |

## 풀이 

1. 내가 푼 풀이 

   ```SQL
   SELECT a.CATEGORY, a.MAX_PRICE, a.PRODUCT_NAME FROM (
   SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE ,PRODUCT_NAME FROM FOOD_PRODUCT GROUP BY CATEGORY
   ) a
   WHERE CATEGORY IN ('국', '김치','과자','식용유')
   ORDER BY a.MAX_PRICE DESC
   ```

2. 다른사람 풀이 

   ```SQL
   SELECT CATEGORY
   ,PRICE MAX_PRICE
   ,PRODUCT_NAME
   FROM FOOD_PRODUCT
   WHERE CATEGORY IN ('과자','국','김치','식용유')
   AND PRICE IN (SELECT MAX(PRICE) FROM FOOD_PRODUCT GROUP BY CATEGORY)
   ORDER BY PRICE DESC
   ```

   


---

## 사용된 개념

1. IN() 함수
   - 여려개의 단어를 찾을 때 유용하다. 
