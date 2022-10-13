

# < Level 2 > 

# 오랜기간 보호한 동물 

> LIMIT, INNER JOIN, DATEDIFF, 사용 

---

## 문제설명 

- `ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다.

- `ANIMAL_OUTS` 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블입니다. 

  

- ANIMAL_INS

  | ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME       | SEX_UPON_INTAKE |
  | --------- | ----------- | ------------------- | ---------------- | ---------- | --------------- |
  | A354597   | Cat         | 2014-05-02 12:16:00 | Normal           | Ariel      | Spayed Female   |
  | A362707   | Dog         | 2016-01-27 12:27:00 | Sick             | Girly Girl | Spayed Female   |
  | A370507   | Cat         | 2014-10-27 14:43:00 | Normal           | Emily      | Spayed Female   |
  | A414513   | Dog         | 2016-06-07 09:17:00 | Normal           | Rocky      | Neutered Male   |

- ANIMAL_OUTS

  | ANIMAL_ID | ANIMAL_TYPE | DATETIME            | NAME       | SEX_UPON_OUTCOME |
  | --------- | ----------- | ------------------- | ---------- | ---------------- |
  | A354597   | Cat         | 2014-06-03 12:30:00 | Ariel      | Spayed Female    |
  | A362707   | Dog         | 2017-01-10 10:44:00 | Girly Girl | Spayed Female    |
  | A370507   | Cat         | 2015-08-15 09:24:00 | Emily      | Spayed Female    |

## 제한사항 

- 입양을 간 동물 중, 보호 기간이 가장 길었던 동물 두 마리의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 기간이 긴 순으로 조회해야 합니다.

## 입출력 예

| ANIMAL_ID | NAME       |
| --------- | ---------- |
| A362707   | Girly Girl |
| A370507   | Emily      |

## 풀이 

1. 내가 푼 풀이 

   ```SQL
   SELECT i.ANIMAL_ID, i.NAME FROM ANIMAL_INS i
   INNER JOIN ANIMAL_OUTS o ON i.animal_id = o.animal_id
   ORDER BY datediff(o.datetime, i.datetime) DESC
   LIMIT 2
   ```

2. LEFT JOIN

   ```SQL
   SELECT I.ANIMAL_ID, I.NAME
   from ANIMAL_INS I
   left join ANIMAL_OUTS O on I.ANIMAL_ID = O.ANIMAL_ID
   where O.DATETIME is not null
   order by datediff(I.DATETIME, O.DATETIME)
   limit 2
   ```

3. DIFF 사용안함

   ```SQL
   SELECT ANIMAL_ID, INS.NAME
   FROM ANIMAL_INS AS INS
   INNER JOIN ANIMAL_OUTS AS OUTS
   USING (ANIMAL_ID)
   ORDER BY OUTS.DATETIME - INS.DATETIME DESC
   LIMIT 2 ;
   ```

4. NOT NULL 처리 

   ```SQL
   SELECT I.ANIMAL_ID, I.NAME
   from ANIMAL_INS I
   left join ANIMAL_OUTS O on I.ANIMAL_ID = O.ANIMAL_ID
   where O.DATETIME is not null
   order by datediff(I.DATETIME, O.DATETIME)
   limit 2
   ```

   


---

## 사용된 개념

1. DATEDIFF(A,B)

   - A-B 를진행하여 일수를 계산한다. 

2. DATEDIFF가 아닌 그냥 빼기 할 경우 안되는 이유

   - 직접 연산을 하게되면 DATETIME 타입 데이터를 숫자형으로 변환하여 연산하기 때문에, 정확한 시간 차이를 구할수 없다.

3. JOIN 

   - JOIN 이용방법

     1. INNER JOIN 테이블 ON A필드 = B필드
     2. INNER JOIN 테이블 USING (공통필드)

   - JOIN 종류

     - **INNER JOIN**

       + JOIN한 테이블의 모든 필드 정보를 이용할 수 있다. 
         
         ```sql
         # 세개의 테이블에 있는 정보를 가져옴
         SELECT c.id, cu.name, i.name, i.price, (c.num * i.price) AS totalPrice FROM cart c 
         INNER JOIN cust cu ON c.custid = cu.id
         INNER JOIN item i ON c.itemid = i.id;
         ```
       
     - **OUTER JOIN = ( LEFT, RIGHT JOIN )** 
     
       + 어느 한쪽에만 데이터가 존재할 떄 사용한다. 
       
       + 기준을 정해 출력할 수 있다. 
       
         ```sql
         # outer join -> 한쪽에 있는 것을 꺼낼때 사용한다. 
         SELECT * FROM emp e
         RIGHT OUTER JOIN title t ON e.titleno = t.titleno;# 오른쪽 title을 기준으로 출력한다. 
         
         SELECT * FROM emp e
         LEFT OUTER JOIN title t ON e.titleno = t.titleno;# 왼쪽 emp 기준으로 출력한다. 
         
         # FULL OUTER : 첫번째 결과와 두번째 결과를 합친다. 
         SELECT * FROM emp e
         LEFT OUTER JOIN title t ON e.titleno = t.titleno
         UNION
         SELECT * FROM emp e
         RIGHT OUTER JOIN title t ON e.titleno = t.titleno;
         ```

