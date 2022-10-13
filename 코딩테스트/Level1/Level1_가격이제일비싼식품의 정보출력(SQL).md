

# < Level 1 > 

# 가격이 제일 비싼 식품의 정보 출력 

> ORDER BY 와 LIMIT 개념 사용 

---

## 문제설명 

- `FOOD_PRODUCT` 테이블에서 가격이 제일 비싼 식품의 식품 ID, 식품 이름, 식품분류, 식품 가격을 조회하는 SQL문을 작성해주세요.


## 제한사항 

## 입출력 예

| PRODUCT_ID | PRODUCT_NAME | PRODUCT_CD | CATEGORY | PRICE |
| ---------- | ------------ | ---------- | -------- | ----- |
| P0020      | 맛있는산초유 | CD_OL00010 | 식용유   | 6500  |

## 풀이 

1. 내가 푼 풀이 

   ```SQL
   SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE FROM FOOD_PRODUCT ORDER BY PRICE DESC LIMIT 1
   ```
   
   


---

## 사용된 개념

