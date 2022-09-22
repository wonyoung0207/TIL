

# < Level 1 > 

# SQL 다루기    

> SQL 문 

---

## 1번 문제설명 

- 동물 보호소에 들어온 모든 동물의 아이디와 이름을 ANIMAL_ID순으로 조회하는 SQL문을 작성해주세요. SQL을 실행하면 다음과 같이 출력되어야 합니다.


## 입출력 예

| ANIMAL_ID | NAME         |
| --------- | ------------ |
| A349996   | Sugar        |
| A350276   | Jewel        |
| A350375   | Meo          |
| A352555   | Harley       |
| A352713   | Gia          |
| A352872   | Peanutbutter |
| A353259   | Bj           |

## 풀이 

```java
SELECT ANIMAL_ID, NAME From ANIMAL_INS
ORDER BY ANIMAL_ID;
```

---



## 2번 문제설명 

- 동물 보호소에 가장 먼저 들어온 동물의 이름을 조회하는 SQL 문을 작성해주세요.

## 입출력 예

| NAME |
| :--: |
| Jack |

## 풀이

```sql
SELECT NAME FROM ANIMAL_INS ORDER BY DATETIME LIMIT 1
```

## 사용된 개념

- ORDER BY : 정렬
- LIMIT : 갯수 제한 
  - limit 1 : 1개만 반환 
  - limit 2, 3 : 2행부터 3개 반환 

---



## 3번 문제설명 

- 동물 보호소에 들어온 동물 중 아픈 동물의 아이디와 이름을 조회하는 SQL 문을 작성해주세요. 이때 결과는 아이디 순으로 조회해주세요.

## 입출력 예

| ANIMAL_ID | NAME     |
| --------- | -------- |
| A367012   | Miller   |
| A381217   | Cherokee |

## 풀이

```sql
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE INTAKE_CONDITION = 'Sick'
```



---



## 4번 문제설명 

- 동물 보호소에 들어온 동물 중 젊은 동물의 아이디와 이름을 조회하는 SQL 문을 작성해주세요. 이때 결과는 아이디 순으로 조회해주세요.

## 입출력 예

| ANIMAL_ID | NAME     |
| --------- | -------- |
| A365172   | Diablo   |
| A367012   | Miller   |
| A381217   | Cherokee |

## 풀이

```sql
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE INTAKE_CONDITION NOT LIKE '%Aged%' ORDER BY ANIMAL_ID
```

## 사용된 개념 

- LIKE : 특정 문자 찾기 
  - % : 해당 단어가 포함된 것을 찾는다. 
    - a% : a로 시작하는 단어 찾기
    - %a : a로 끝나는 단어 찾기 
  - \_ : 특정 위치부터 검색 
    - WHERE name LIKE '__a%'   =>  세 번째 문자가 a인 모든 이름을 출력 
- INSERT ( 찾을 필드 , 찾을 값)
  - NSTR 함수는 특정 문자열을 찾은 위치를 정수형(숫자)으로 반환한다.  문자열을 찾으면 1이상, 못 찾으면 0을 반환한다.
    -  WHERE INSTR(ename, 'MI') > 0



---



## 5번 문제설명 

- 동물 보호소에 들어온 모든 동물의 아이디와 이름, 보호 시작일을 이름 순으로 조회하는 SQL문을 작성해주세요. 단, 이름이 같은 동물 중에서는 보호를 나중에 시작한 동물을 먼저 보여줘야 합니다.

## 제한사항

1. 이름을 사전 순으로 정렬하면 다음과 같으며, 'Jewel', 'Raven', 'Sugar'
2. 'Raven'이라는 이름을 가진 개와 고양이가 있으므로, 이 중에서는 보호를 나중에 시작한 개를 먼저 조회합니다.

## 데이터 형태 

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME  | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ----- | --------------- |
| A349996   | Cat         | 2018-01-22 14:32:00 | Normal           | Sugar | Neutered Male   |
| A350276   | Cat         | 2017-08-13 13:50:00 | Normal           | Jewel | Spayed Female   |
| A396810   | Dog         | 2016-08-22 16:13:00 | Injured          | Raven | Spayed Female   |
| A410668   | Cat         | 2015-11-19 13:41:00 | Normal           | Raven | Spayed Female   |

## 풀이

```sql
SELECT ANIMAL_ID, NAME, DATETIME FROM ANIMAL_INS ORDER BY NAME, DATETIME DESC
```

## 사용된 개념 

- ORDER BY  중첩
  - ORDER BY (첫 정렬 필드), (두번째 정렬 필드) 
- DESC : 내림차순
- ASC ( 디폴트 ) : 오름차순 

---



## 6번 문제설명 

- 가장 최근에 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요.
- 가장 늦게 들어온 동물은 Anna이고, Anna는 2013-11-18 17:03:00에 들어왔습니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

## 제한사항

1. 이름을 사전 순으로 정렬하면 다음과 같으며, 'Jewel', 'Raven', 'Sugar'
2. 'Raven'이라는 이름을 가진 개와 고양이가 있으므로, 이 중에서는 보호를 나중에 시작한 개를 먼저 조회합니다.

## 데이터 형태 

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME     | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | -------- | --------------- |
| A399552   | Dog         | 2013-10-14 15:38:00 | Normal           | Jack     | Neutered Male   |
| A379998   | Dog         | 2013-10-23 11:42:00 | Normal           | Disciple | Intact Male     |
| A370852   | Dog         | 2013-11-03 15:04:00 | Normal           | Katie    | Spayed Female   |
| A403564   | Dog         | 2013-11-18 17:03:00 | Normal           | Anna     | Spayed Female   |

## 풀이

```sql
SELECT DATETIME FROM ANIMAL_INS ORDER BY DATETIME DESC LIMIT 1
```

