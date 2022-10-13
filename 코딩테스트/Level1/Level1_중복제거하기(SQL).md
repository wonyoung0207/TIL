

# < Level 1 > 

# 동물 수 구하기 

> 서브쿼리 사용 

---

## 문제설명 

- 동물 보호소에 들어온 동물의 이름은 몇 개인지 조회하는 SQL 문을 작성해주세요. 이때 이름이 NULL인 경우는 집계하지 않으며 중복되는 이름은 하나로 칩니다.

## 제한사항 

- 보호소에 들어온 동물의 이름은 NULL(없음), `*Sam`, `*Sam`, `*Sweetie`입니다. 이 중 NULL과 중복되는 이름을 고려하면, 보호소에 들어온 동물 이름의 수는 2입니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

## 입출력 예

| count |
| ----- |
| 2     |

## 풀이 

1. 내가 푼 풀이 

   ```SQL
   SELECT count(name_group.name) AS count FROM 
   (SELECT NAME FROM ANIMAL_INS WHERE NAME IS NOT NULL GROUP BY NAME) name_group
   ```

2. 특이한 풀이 

   ```JAVA
   SELECT COUNT(DISTINCT NAME) FROM ANIMAL_INS
   ```


---

## 사용된 개념

1. COUNT 함수 
   - COUNT 는 * 이 아니면 NULL 값을 세지 않는다. 

2. NULL 처리 
   - NULL은 비교연산으로 비교할 수 없다. 

3. **Group By**

   + 컬럼 데이터를 그룹화 할 수 있는 명령어 

   + **" ~별 "**이 나오면 무조건 그룹 해야함

     + ex) groupName 별 구매 고객의 수를 구하시오. 

   + 해당 명령어 사용 하면 select * 의 형식이 아닌 그룹바이 한 "**필드명 , 그룹바이 함수**" 형식으로 입력해야한다. 

   + group by에서 조건을 사용하려면 where 이 아닌 having을 사용한다. 

   + group BY 의 순서도 중요하다. 

     + ex) GROUP BY num. groupName 
       + num 으로 먼저 그룹화하고, 그다음 groupName으로 그룹화 한다. 

   + ```SQL
     SELECT userID, ROUND(AVG(price),1) AS pavg FROM buytbl
     GROUP BY userID
     HAVING pavg > 100;
     
     # userID 와 groupName을 이용해 그룹화 하고, userID 에 BBK, KBS 가 들어가있는 것만 출력 
     SELECT userID, groupName , SUM(price*amount) AS usum FROM buytbl
     GROUP BY userID, groupName
     HAVING userID IN ('BBK','KBS') 
     AND
     groupName IS NOT NULL
     ORDER BY userID;
     
     ```
