
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


SELECT * FROm emp;

# 1. 부서별, 직급별 연봉 평균을 구하시오
SELECT deptno, titleno, AVG(salary) FROM emp
GROUP BY deptno,titleno;

# 2. 입사 년도 별 월급 평균을 구하시오
SELECT date_format(hdate,'%Y') AS shdate, AVG(salary) AS savg FROM emp
GROUP BY shdate;


# 3.부서별 입사 월을 기준으로 연봉의 합을 구하시오
SELECT deptno, date_format(hdate, '%m') AS mhdate, SUM(salary) AS ssum FROM emp
GROUP BY deptno, mhdate;

# 4. 이영업이 속한 부서의 연봉의 평균을 구하시오
SELECT deptno FROM emp
WHERE empname = '이영업';

SELECT AVG(salary) FROM emp
WHERE deptno = (SELECT deptno FROM emp
WHERE empname = '이영업');


# 5. 홍영자 직급과 같은 직원들의 연봉 평균보다 많이 받는 직원을 구하시오 
SELECT AVG(salary) FROM emp 
WHERE deptno = (SELECT deptno FROM emp WHERE empname = '홍영자');

SELECT * FROM emp
WHERE salary > (
SELECT AVG(salary) FROM emp 
WHERE deptno = (SELECT deptno FROM emp WHERE empname = '홍영자')
);

# 6. 회사내 매니저는 총 몇명인지 구하시오
SELECT manager FROM emp
GROUP BY manager;

SELECT COUNT(DISTINCT(manager)) AS cnt_manager FROM emp;

# 7. 2000-01-01 부터 2002-12-31 일까지 입사한 직원들의 연봉 평균을 구하시오
SELECT * FROM emp
WHERE hdate BETWEEN '2000-01-01' and '2002-12-31';

SELECT ROUND(AVG(salary)) AS avg_salary FROM emp
WHERE hdate BETWEEN '2000-01-01' and '2002-12-31';


SELECT * FROM emp;
# 1. 오늘 날짜 기준으로 입사 일부터 며칠이 지났고 몇달이 지났는지 출력
SELECT empname , datediff(NOW(), hdate) AS work_day, 
period_diff(DATE_FORMAT(NOW(),'%Y%m') , date_format(hDate,'%Y%m')) AS work_month
FROM emp;


# 2. 직원들 연봉이 4000 이상이면 high, 2500 이상이면 middle, 2500이하면 lw
SELECT empname , salary,
CASE
	WHEN salary >= 4000 THEN 'high'
    WHEN salary >= 2500 THEN 'middle'
    ELSE 'low'
END AS Slevel
FROM emp;


# 3. 부서별 연봉 평균의 합을 구하시오 
SELECT * , AVG(salary) FROM emp WHERE deptno=20;
SELECT deptno ,ROUND(AVG(salary),1) AS savg FROM emp
GROUP BY deptno;

# 가상 테이블 이용
WITH temp(deptno, savg)
AS
(SELECT deptno, AVG(salary) AS savg FROM emp
GROUP BY deptno)
SELECT SUM(savg) FROM temp;

# 서브쿼리 이용 
SELECT SUM(a.savg) FROM
(SELECT deptno, ROUND(AVG(salary),1) AS savg FROM emp
GROUP BY deptno) a;



# 4. 부서 별 입사일 평균을 구하시오
SELECT deptno ,empname ,datediff(NOW(),hdate) FROM emp;

# 가상 테이블 이용 
WITH temp(deptno,empname, ddate)
AS
	(SELECT deptno, empname, datediff(NOW(),hdate) FROM emp)
SELECT deptno, ROUND(AVG(ddate),0) FROM temp
GROUP BY deptno;

# 쿼리이용
SELECT deptno, ROUND(AVG(datediff(NOW(), hdate)),0) AS ddate FROM emp
GROUP BY deptno;


# 5. 이말숙 직원과 같은 해에 입사한 직원을 조회
SELECT * FROM emp WHERE empname='이말숙';
SELECT empname, hdate FROM emp;
SELECT empname, hdate FROM emp WHERE date_format(hdate,'%Y') = (SELECT date_format(hdate,'%Y') FROM emp WHERE empname='이말숙');


# 6. 부서별 최고 임금을 받는 직원의 평균을 구하고, 그 평균 보다 많이 받는 직원을 조회하시오
SELECT deptno ,empname, MAX(salary) FROM emp 
GROUP BY deptno;

# 가상테이블 이용 
WITH temp(deptno,empname,salary, max)
AS
(SELECT deptno ,empname, salary, MAX(salary) AS max FROM emp 
GROUP BY deptno)
SELECT * FROM temp 
WHERE salary > (SELECT AVG(max) FROM temp);

# 서브쿼리 이용 
SELECT empname,salary FROM emp
WHERE salary >
(SELECT AVG(a.smax) FROM (
SELECT deptno,MAX(salary) AS smax FROM emp
GROUP BY deptno) a);


# 6. 이름이 '홍' 과 '이' 로 시작하는 사람들 평균 연봉을 구하시오
SELECT AVG(a.salary) AS 'hongAVG' , AVG(b.salary) AS 'leeAVG' FROM 
(SELECT salary FROM emp WHERE empname LIKE '홍%') a , (SELECT salary FROM emp WHERE empname LIKE '이%') b;

SELECT AVG(salary) FROM emp WHERE empname LIKE '홍%';
SELECT empname,salary FROM emp WHERE empname LIKE '홍%' or empname LIKE '이%';

# 7.문제 성이 김씨인 직원들의 평균 연봉을 구하고 이들 평균 연봉 보다 낮은 연봉의 직원의 근속일수를 구하시오. (근속일수는 현재-입사일)
SELECT empname,DATEDIFF(NOW(),hdate) FROM emp
WHERE salary < (
SELECT AVG(salary) FROM emp
WHERE empname like '김%');


# 8. 킹(1001)이 관리하는 직원들의 연봉 평균의 합을 구하시오
SELECT AVG(salary) FROM emp
WHERE manager = (SELECT empno FROM emp WHERE empname= '킹');

# 가상 테이블 사용
WITH temp(sameManager)
AS
(SELECT salary AS sameManager FROM emp WHERE manager = (SELECT empno FROM emp WHERE empname= '킹'))
SELECT AVG(sameManager) FROM temp;


# 9. 입사일이 24년 지난 직원들의 연봉의 평균을 구하시오.
SELECT * FROM emp;

SELECT * FROM emp
WHERE period_diff(date_format(NOW(), '%Y%m'),
date_format(hdate,'%Y%m')) > (24 * 12);

SELECT ROUND(AVG(a.salary),1) AS avg FROM
(SELECT salary FROM emp 
WHERE period_diff(date_format(NOW(), '%Y%m'),
date_format(hdate,'%Y%m')) > (24 * 12)) a;


# 10. 부서별 입사일이 제일 오래된 직원들 중 연봉이 제일 낮은 사람을 구하시오.
SELECT * FROM 
(SELECT deptno ,empname,salary, MAX(datediff(NOW(), hdate)) AS maxWorkDay FROM emp 
GROUP BY deptno) A;

SELECT MIN(salary) FROM emp;

SELECT * ,datediff(NOW(),hdate) FROM emp;

SELECT empname, salary  FROM emp
WHERE salary = (SELECT MIN(A.salary) FROM 
(SELECT deptno ,empname,salary, MAX(datediff(NOW(), hdate)) AS maxWorkDay FROM emp 
GROUP BY deptno) A);



# 11. 직급별 가장 높은 연봉의 직원들을 구하시오.
SELECT * FROM 
(SELECT titleno, MAX(salary), empname FROM emp
GROUP BY titleno) A;

SELECT titleno, empname, MAX(salary) FROM emp
GROUP BY titleno;

# 12. 이말숙 직원보다 늦게 입사한 직원들의 연봉의 합은 구하시오.




