

# < Level 1 > 

# 강원도에 위치한 생산공장 목록 출력 

> SQL문 LIKE 개념 이용 

---

## 문제설명 

- 다음은 식품공장의 정보를 담은 `FOOD_FACTORY` 테이블입니다. `FOOD_FACTORY` 테이블은 다음과 같으며 `FACTORY_ID`, `FACTORY_NAME`, `ADDRESS`, `TLNO`는 각각 공장 ID, 공장 이름, 주소, 전화번호를 의미합니다.

  | Column name  | Type         | Nullable |
  | ------------ | ------------ | -------- |
  | FACTORY_ID   | VARCHAR(10)  | FALSE    |
  | FACTORY_NAME | VARCHAR(50)  | FALSE    |
  | ADDRESS      | VARCHAR(100) | FALSE    |
  | TLNO         | VARCHAR(20)  | TRUE     |


## 제한사항 

- `FOOD_FACTORY` 테이블에서 강원도에 위치한 식품공장의 공장 ID, 공장 이름, 주소를 조회하는 SQL문을 작성해주세요. 이때 결과는 공장 ID를 기준으로 오름차순 정렬해주세요.

## 입출력 예

| FACTORY_ID | FACTORY_NAME   | ADDRESS                             |
| ---------- | -------------- | ----------------------------------- |
| FT19980003 | (주)맛있는라면 | 강원도 정선군 남면 칠현로 679       |
| FT20100003 | (주)맛있는음료 | 강원도 원주시 문막읍 문막공단길 154 |
| FT20100004 | (주)맛있는국   | 강원도 평창군 봉평면 진조길 227-35  |

## 풀이 

1. 내가 푼 풀이 

   ```SQL
   SELECT FACTORY_ID, FACTORY_NAME, ADDRESS FROM FOOD_FACTORY WHERE ADDRESS LIKE '%강원도%'
   ```
   
   


---

## 사용된 개념

1. LIKE
   - LIKE를 사용하기 위해서는 앞에 WHERE 필드명 이 와야한다. 
   - % : 해당 단어가 포함된 것을 찾는다. 
     - a% : a로 시작하는 단어 찾기
     - %a : a로 끝나는 단어 찾기 
   - \_ : 특정 위치부터 검색 
     - WHERE name LIKE '__a%'   =>  세 번째 문자가 a인 모든 이름을 출력 
