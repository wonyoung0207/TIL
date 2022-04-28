-- CREATE schema companydb;

-- DROP TABLE emp;
-- DROP TABLE dept;
-- DROP TABLE title;

CREATE TABLE dept(
deptno CHAR(2) PRIMARY KEY,
deptname VARCHAR(20),
deptloc VARCHAR(20) 
);
CREATE TABLE title(
titleno CHAR(2) PRIMARY KEY,
titlename VARCHAR(20) 
);
CREATE TABLE emp(
empno CHAR(4) PRIMARY KEY,
titleno CHAR(2), 
deptno CHAR(2),
empname VARCHAR(10),
manager CHAR(4),
salary INT(5),
hdate DATE
);
INSERT INTO emp VALUES ('1001','40','10', '킹',NULL, 5000,'1997-01-01' );
INSERT INTO emp VALUES ('1002','30','20', '이영업','1001', 4300,'1998-01-01' );
INSERT INTO emp VALUES ('1003','30','30', '김생산','1001', 4800,'1999-01-01' );
INSERT INTO emp VALUES ('1004','30','40', '홍연구','1001', 4500,'1999-12-01' );
INSERT INTO emp VALUES ('1005','20','20', '이말숙','1002', 3300,'2000-01-01' );
INSERT INTO emp VALUES ('1006','10','20', '김말숫','1002', 2800,'2001-01-01' );
INSERT INTO emp VALUES ('1007','20','30', '홍영자','1003', 3500,'2000-12-01' );
INSERT INTO emp VALUES ('1008','10','30', '이영자','1003', 2300,'2002-05-01' );
INSERT INTO emp VALUES ('1009','20','40', '김강국','1004', 3800,'2001-01-01' );
INSERT INTO emp VALUES ('1010','10','40', '홍정국','1004', 2500,'2002-12-01' );
INSERT INTO dept VALUES ('10', '관리부', '서울');
INSERT INTO dept VALUES ('20', '생산부', '부산');
INSERT INTO dept VALUES ('30', '영업부', '대구');
INSERT INTO dept VALUES ('40', '기술부', '대전');
INSERT INTO title VALUES ('40','대표');
INSERT INTO title VALUES ('30','팀장');
INSERT INTO title VALUES ('20','대리');
INSERT INTO title VALUES ('10','사원');


SELECT * FROM emp;
SELECT * FROM dept;

CREATE VIEW v_emp
AS 
SELECT empno, empname FROM emp;

SELECT * FROM v_emp;


SELECT empno,empname,salary, salary*2 AS fee FROM emp;

use martdb;

use companydb;

show table status;
# 테이블의 현재 속성을 보두 보여준다. 
-- ㅇㅇ

SHOW DATABASES;


use sqldb;
select * from usertbl;

select * from emp;


# 1. 직원 중에 manager가 없는 직원을 조회 
SELECT * from emp
WHERE manager is null;

# 2. 직원들의 월급과 세금(*0.15)을 조회하시오. 
# 월급 컬럼명은 month 로, 세금 컬럼명은 fee 로 조회
# 단, fee가 많은 순으로 정렬
SELECT salary AS month, (salary*0.15) AS fee FROM emp ORDER BY salary*0.15 DESC;

# 3. 01월과 12월에 입사한 직원들 중 이씨를 조회
SELECT * FROM emp
WHERE empname LIKE '이%' 
AND
date_format(hDate,'%m') IN ('01','12');

# 4. 홍영자 직원의 입사 일보다 나중에 입사한 직원들을 조회 \
SELECT * FROM emp 
WHERE hDate > (SELECT hDate FROM emp WHERE empname = '홍영자');
#SELECT hDate FROM emp WHERE empname = '홍영자';#2000-12-01

# 5. 월급의 범위가 3000 에서 4500 인 직원 중, manager가 김생산과 같은 직원들을 조회 
SELECT * FROM emp
WHERE salary BETWEEN 3000 AND 4500
AND
manager = (SELECT manager FROM emp
WHERE empname = '김생산'
);



