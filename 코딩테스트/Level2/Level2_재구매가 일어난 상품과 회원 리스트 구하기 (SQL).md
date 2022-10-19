

# < Level 2 > 

# 제목 예시 

> 사용 개념 간소화 

---

## 문제설명 

- `ONLINE_SALE` 테이블에서 동일한 회원이 동일한 상품을 재구매한 데이터를 구하여, 재구매한 회원 ID와 재구매한 상품 ID를 출력하는 SQL문을 작성해주세요. 결과는 회원 ID를 기준으로 오름차순 정렬해주시고 회원 ID가 같다면 상품 ID를 기준으로 내림차순 정렬해주세요.

| ONLINE_SALE_ID | USER_ID | PRODUCT_ID | SALES_AMOUNT | SALES_DATE |
| -------------- | ------- | ---------- | ------------ | ---------- |
| 1              | 1       | 3          | 2            | 2022-02-25 |
| 2              | 1       | 4          | 1            | 2022-03-01 |
| 4              | 2       | 4          | 2            | 2022-03-12 |
| 3              | 1       | 3          | 3            | 2022-03-31 |
| 5              | 3       | 5          | 1            | 2022-04-03 |
| 6              | 2       | 4          | 1            | 2022-04-06 |
| 2              | 1       | 4          | 2            | 2022-05-11 |


## 제한사항 

- `USER_ID` 가 1인 유저가 `PRODUCT_ID` 가 3, 4인 상품들을 재구매하고, `USER_ID` 가 2인 유저가 `PRODUCT_ID` 가 4인 상품을 재구매 하였으므로, 다음과 같이 결과가 나와야합니다.

## 입출력 예

| USER_ID | PRODUCT_ID |
| ------- | ---------- |
| 1       | 4          |
| 1       | 3          |
| 2       | 4          |

## 풀이 

1. 내가 푼 풀이 

   ```sql
   SELECT USER_ID,PRODUCT_ID
   FROM ONLINE_SALE
   GROUP BY USER_ID,PRODUCT_ID
   HAVING COUNT(*) >= 2
   ORDER BY USER_ID, PRODUCT_ID DESC
   ```
   
   | USER_ID | PRODUCT_ID |
   | ------- | ---------- |
   | 15      | 12         |
   | 119     | 12         |
   
   1. 필드 다르게 추출한 결과 
   
      ```SQL
      SELECT o.USER_ID, o.PRODUCT_ID
      FROM ONLINE_SALE o
      INNER JOIN 
      (SELECT USER_ID,PRODUCT_ID FROM ONLINE_SALE
      GROUP BY USER_ID,PRODUCT_ID
      HAVING COUNT(*) >= 2) n ON n.USER_ID = o.USER_ID
      ORDER BY USER_ID, PRODUCT_ID DESC
      ```
   
      | USER_ID | PRODUCT_ID |
      | ------- | ---------- |
      | 15      | 12         |
      | 15      | 12         |
      | 119     | 12         |
      | 119     | 12         |
   


---

## 사용된 개념

