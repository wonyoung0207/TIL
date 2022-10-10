

# < Level 1 > 

# 최솟값 구하기 

> 이름변경 및 LIMIT, ORDER BY 사용 

---

## 문제설명 

- 동물 보호소에 가장 먼저 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요.

## 제한사항 

- 필드명 변경 

## 입출력 예

| 시간                |
| ------------------- |
| 2013-10-14 15:38:00 |

## 풀이 

1. 내가 푼 풀이 

   ```SQL
   SELECT DATETIME AS 시간 FROM ANIMAL_INS ORDER BY DATETIME LIMIT 1
   ```
   


---

## 사용된 개념

