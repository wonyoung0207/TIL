

# < Level 2 > 

# 중성화 여부 파악하기

> IF문

---

## 문제설명 

- 보호소의 동물이 중성화되었는지 아닌지 파악하려 합니다. 중성화된 동물은 `SEX_UPON_INTAKE` 컬럼에 'Neutered' 또는 'Spayed'라는 단어가 들어있습니다. 동물의 아이디와 이름, 중성화 여부를 아이디 순으로 조회하는 SQL문을 작성해주세요. 이때 중성화가 되어있다면 'O', 아니라면 'X'라고 표시해주세요.

  | ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME      | SEX_UPON_INTAKE |
  | --------- | ----------- | ------------------- | ---------------- | --------- | --------------- |
  | A355753   | Dog         | 2015-09-10 13:14:00 | Normal           | Elijah    | Neutered Male   |
  | A373219   | Cat         | 2014-07-29 11:43:00 | Normal           | Ella      | Spayed Female   |
  | A382192   | Dog         | 2015-03-13 13:14:00 | Normal           | Maxwell 2 | Intact Maleㄴ   |


## 제한사항 

- 중성화한 동물: Elijah, Ella
- 중성화하지 않은 동물: Maxwell 2

## 입출력 예

| ANIMAL_ID | NAME      | 중성화 |
| --------- | --------- | ------ |
| A355753   | Elijah    | O      |
| A373219   | Ella      | O      |
| A382192   | Maxwell 2 | X      |

## 풀이 

1. 내가 푼 풀이 

   ```java
   SELECT ANIMAL_ID, NAME, IF(SEX_UPON_INTAKE LIKE '%Neutered%' OR SEX_UPON_INTAKE LIKE '%Spayed%', 'O', 'X') as '중성화' FROM ANIMAL_INS ORDER BY ANIMAL_ID ASC
   ```

2. INSRT()사용

   ```SQL
   SELECT ANIMAL_ID, name,if(instr(SEX_UPON_INTAKE, 'intact'),'X','O') '중성화'
   from ANIMAL_ins
   order by Animal_id;
   ```

3. substring_index 이용한 풀이 

   ```sql
   SELECT animal_id, name, 
       case substring_index(sex_upon_intake, " ", 1)
           when "intact" then "X"
           else "O"
           end as "중성화"
   from animal_ins
   order by 1;
   ```

4. IN() 사용

   ```sql
   SELECT ANIMAL_Id,NAME,IF(SEX_UPON_INTAKE IN('Neutered Male','Spayed Male','Neutered Female','Spayed Female'),'O','X') 중성화 
   FROM ANIMAL_INS
   ```

5. Case WHEN THEN 사용

   ```sql
   SELECT ANIMAL_ID, NAME, 
   CASE WHEN SEX_UPON_INTAKE LIKE '%Neutered%' THEN 'O'
        WHEN SEX_UPON_INTAKE LIKE '%Spayed%' THEN 'O'
        ELSE 'X' END
   FROM ANIMAL_INS;
   ```

   


---

## 사용된 개념

1. IF() 
   - 조건문 필요시 사용 
   - if( 조건식, true일경우 출력, false 일경우 출력)
