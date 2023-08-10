# 트랜잭션 쿼리 이용

---

>[참고 사이트1](https://coding-factory.tistory.com/82)

## 트랜잭션

- 하나의 처리를 여러 단계로 다루는 기능을 트랜잭션이라고 한다. 
  -  하나의 처리를 Commit이라는 명령어를 수행하기전 마지막으로 한번 더 확인할 수 있는 기회를 줌으로써 좀 더 안정적인 데이터베이스 작업을 가능하게 한다. 
  -  하나의 트랜잭션은 Commit되거나 Rollback된다.

### 예시

```sql
-- 트랜잭션 사용법
BEGIN TRAN -- 트랜잭션 시작
ROLLBACK TRAN -- 트랜잭션 이전상태로 ROLL BACK
COMMIT TRAN -- 트랜잭션 완료 

-- Example 1
START TRANSACTION; -- 트랜잭션 시작

UPDATE products -- products 테이블에서 id가 1인 제품의 가격을 500으로 갱신
SET price = 500
WHERE id = 1;

IF (SELECT price FROM products WHERE id = 2) > 600 THEN -- 가격이 600보다 큰 경우에만 변경 사항을 커밋
    COMMIT; -- 변경 사항을 커밋하여 트랜잭션을 확정
ELSE
    ROLLBACK; -- 변경 사항을 롤백하여 트랜잭션을 취소
END IF;

----------------------------------------------------------------------------------------------

-- Example 2
BEGIN TRAN
UPDATE My_Table SET VAT = AM * 0.1 , DTS=NEOE.SF_SYSDATE(GETDATE()) WHERE CD_COMPANY ='0327' 
SELECT * FROM My_Table WHERE COMPANY ='0327'
COMMIT TRAN
```



