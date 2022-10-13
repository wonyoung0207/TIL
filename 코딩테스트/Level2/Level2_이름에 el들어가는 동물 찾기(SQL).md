

# < Level 2 > 

# 이름에 el들어가는 동물 찾기 

> LIKE, ORDER BY

---

## 문제설명 

- 보호소에 돌아가신 할머니가 기르던 개를 찾는 사람이 찾아왔습니다. 이 사람이 말하길 할머니가 기르던 개는 이름에 'el'이 들어간다고 합니다. 동물 보호소에 들어온 동물 이름 중, 이름에 "EL"이 들어가는 개의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 이름 순으로 조회해주세요. 단, 이름의 대소문자는 구분하지 않습니다.

  | ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME         | SEX_UPON_INTAKE |
  | --------- | ----------- | ------------------- | ---------------- | ------------ | --------------- |
  | A355753   | Dog         | 2015-09-10 13:14:00 | Normal           | Elijah       | Neutered Male   |
  | A352872   | Dog         | 2015-07-09 17:51:00 | Aged             | Peanutbutter | Neutered Male   |
  | A353259   | Dog         | 2016-05-08 12:57:00 | Injured          | Bj           | Neutered Male   |
  | A373219   | Cat         | 2014-07-29 11:43:00 | Normal           | Ella         | Spayed Female   |
  | A382192   | Dog         | 2015-03-13 13:14:00 | Normal           | Maxwell 2    | Intact Male     |


## 제한사항 

- 이름에 'el'이 들어가는 동물은 Elijah, Ella, Maxwell 2입니다.
- 이 중, 개는 Elijah, Maxwell 2입니다.

## 입출력 예

| ANIMAL_ID | NAME      |
| --------- | --------- |
| A355753   | Elijah    |
| A382192   | Maxwell 2 |

## 풀이 

1. 내가 푼 풀이 

   ```java
   SELECT ANIMAL_ID,NAME FROM ANIMAL_INS
   WHERE ANIMAL_TYPE = 'Dog'
   AND
   NAME LIKE '%EL%' OR '%el%'
   ORDER BY NAME
   ```
   


---

## 사용된 개념
