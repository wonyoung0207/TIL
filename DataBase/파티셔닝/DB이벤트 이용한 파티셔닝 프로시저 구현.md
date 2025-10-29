

# DB이벤트 이용한 파티셔닝 프로시저 구현

---

>

## 구현 계기

- DB 이벤트 이용해 월말마다 파티셔닝을 하는 기능을 개발했엇다. 
- **하지만 에러로 인해 파티셔닝이 되지않아 데이터가 유실되었다.** 
  - 파티셔닝을 하기 위해선 pmax를 삭제 후 다음 파티셔닝을 추가해야하는 로직이 존재한다. 
  - 에러로 인해 pmax 에 데이터들이 쌓였다. (원래라면 파티셔닝된 곳에 데이터가 저장되고 pmax에는 데이터가 없어야 한다 )
  - 그 결과 pmax 가 삭제되어 데이터가 유실되었다. 
- 그래서 해당 기능을 보완하기 위해 구현 계획을 세웠다. 

## 구현 계획

##### Event 실행 (매일 새벽 3시 실행)

1. 말일인지 체크( 말일 아닌 경우 event 중지 )
2. 혹시 있을 pmax 데이터 누락 최소화 위해 새벽 3시 실행 
3. 실행 로그 기록 

##### 프로시저 실행 

1. pmax 존재 여부 확인
   - 기존 pmax 분할
2. 다음달 파티션 존재 여부 확인
   1. 다음달 파티션 있는경우 -> 스킵 
   2. 다음달 파티션 추가 실행
3. 파티션 분할
   1. pmax 를 이용한 `REORGANIZE PARTITION` 으로 pmax 데이터 소실 방지 
   2. 종료 로그
4. InnoDB 파티션 통계 재계산
   1. 안하면 통계 바로 안잡힘 

## 로그 테이블

```sql
-- daegu5g_dev.TB_ED_PARTITION_HISTORY_LOG definition

CREATE TABLE `TB_ED_PARTITION_HISTORY_LOG` (
  `id` int NOT NULL AUTO_INCREMENT,
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '로그 시간',
  `action_type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '작업 유형 (ADD / DROP / SKIP / DONE 등)',
  `partition_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '대상 파티션명',
  `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '로그 메시지',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=306 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='파티션 관리 프로시저 실행 이력 로그';
```

## DB 이벤트

```sql
-- 이벤트 삭제 
DROP EVENT IF EXISTS event_manage_monthly_partitions;

-- 이벤트 등록 
CREATE EVENT event_manage_monthly_partitions
ON SCHEDULE EVERY 1 DAY
STARTS (
  TIMESTAMP(
    CONCAT(
      DATE_FORMAT(
        IF (TIME(NOW()) < '03:00:00', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 DAY)), -- 03시 정각기준 실행 날짜 정하기
        '%Y-%m-%d'
      ),
      ' 03:00:00'
    )
  )
)
ON COMPLETION PRESERVE
DO
    CALL proc_manage_monthly_partitions();


-- 이벤트 목록 
SHOW EVENTS;

-- 바로 프로시저 호출 
CALL daegu5g_dev.proc_manage_monthly_partitions();
```

## 프로시저

```sql
CREATE DEFINER=`user5g`@`%` PROCEDURE `daegu5g_dev`.`proc_manage_monthly_partitions`()
BEGIN
  /* ===== 변수 선언 ===== */
  DECLARE next_part_name   VARCHAR(6);
  DECLARE next_part_value  VARCHAR(32);
  DECLARE pmax_exists      INT DEFAULT 0;
  DECLARE v_action         VARCHAR(50);
  DECLARE v_message        VARCHAR(500);

  DECLARE schema_name VARCHAR(64) DEFAULT 'Wony';
  DECLARE var_table_name  VARCHAR(64) DEFAULT 'TB_ED_HT_TEST';

  /* ===== 에러 핸들러 ===== */
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    GET DIAGNOSTICS CONDITION 1
      @p1 = RETURNED_SQLSTATE,
      @p2 = MESSAGE_TEXT;

    SET v_action  = 'ERROR';
    SET v_message = CONCAT(
        'SQLSTATE=', @p1,
        ', MSG=', @p2,
        ', AT=', NOW(),
        ', STEP=', IFNULL(@current_step, 'UNKNOWN'),
        ', SQL=', IFNULL(@debug_sql, '')
    );

    INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
    VALUES (v_action, IFNULL(@current_step, ''), LEFT(v_message, 500));

    RESIGNAL;
  END;

  /* ===== 메인 로직 ===== */
  main_block: BEGIN
  	
	
    SET @current_step := 'INIT';
    SET @debug_sql := NULL;

    /* 0) pmax 존재 보장 (항상 선행) */
    SET @current_step := 'CHECK_PMAX';
    SELECT COUNT(*) INTO pmax_exists
    FROM information_schema.PARTITIONS
    WHERE TABLE_SCHEMA   = schema_name
      AND TABLE_NAME     = var_table_name
      AND PARTITION_NAME = 'pmax';

    IF pmax_exists = 0 THEN
      SET @current_step := 'ADD_PMAX';
      SET @debug_sql = CONCAT(
        'ALTER TABLE ', schema_name, '.', var_table_name,
        ' ADD PARTITION (PARTITION pmax VALUES LESS THAN (MAXVALUE))'
      );
      PREPARE stmt FROM @debug_sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

      INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
      VALUES ('ADD_PMAX', 'pmax', 'Added missing pmax partition.');
      SET @debug_sql := NULL;
    END IF;
      
  	-- 캐시된 메타데이터 강제 무효화
	SET @current_step := 'REFRESH_METADATA';
	SET @debug_sql := CONCAT('ANALYZE TABLE ', schema_name, '.', var_table_name);
	PREPARE stmt FROM @debug_sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;


    /* 1) 다음달 파티션 키/경계 계산 */
    SET @current_step := 'CALC_NEXT';
    SET next_part_name  := DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 1 MONTH), '%Y%m');   -- 현재달 기준 (요청 반영)
    SET next_part_value := CONCAT(DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 2 MONTH), '%Y%m'), '01000000000');

    /* 2) 다음달 파티션 존재 확인 */
    SET @current_step := 'CHECK_NEXT_PARTITION';
    SET @part_exists := 0;
    
    SELECT COUNT(*) INTO @part_exists
    FROM information_schema.PARTITIONS
    WHERE TABLE_SCHEMA   = schema_name
      AND TABLE_NAME     = var_table_name
      AND PARTITION_NAME = CONCAT('p', next_part_name);
	
	-- 실제 들어간 값 즉시 로깅
	INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
	VALUES ('DEBUG', CONCAT('p', next_part_name), CONCAT('CHECK_NEXT_PARTITION part_exists=', @part_exists));

    IF @part_exists = 1 THEN
      INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
      VALUES ('SKIP', CONCAT('p', next_part_name),CONCAT(var_table_name, ': ', 'Next month partition already exists.'));

      SET @current_step := 'DONE';
      INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
      VALUES ('DONE', '', 'Monthly partition management: nothing to do (already exists).');

      LEAVE main_block;
    END IF;

    /* 3) 경계 증가성 체크 */
    SET @current_step := 'CHECK_INCREASING';
    SELECT MAX(PARTITION_DESCRIPTION) INTO @current_max_value
    FROM information_schema.PARTITIONS
    WHERE TABLE_SCHEMA = schema_name
      AND TABLE_NAME   = var_table_name
      AND PARTITION_NAME <> 'pmax';

    IF @current_max_value IS NOT NULL AND next_part_value <= @current_max_value THEN
      SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Computed next_part_value is not greater than current max partition.';
    END IF;

    /* 4) REORGANIZE pmax → (pYYYYMM, pmax) */
    SET @current_step := 'REORGANIZE_PMAX';
    SET @debug_sql = CONCAT(
      'ALTER TABLE ', schema_name, '.', var_table_name,
      ' REORGANIZE PARTITION pmax INTO (',
        'PARTITION p', next_part_name, ' VALUES LESS THAN (''', next_part_value, '''), ',
        'PARTITION pmax VALUES LESS THAN (MAXVALUE))'
    );
    PREPARE stmt FROM @debug_sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

    INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
    VALUES ('ADD', CONCAT('p', next_part_name),
            CONCAT('Created by REORGANIZE from pmax. Boundary < ', next_part_value, ' >.'));

    /* 5) 완료 로그 */
    SET @current_step := 'DONE';
    SET @debug_sql := NULL;
    INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
    VALUES ('DONE', '', CONCAT(var_table_name, ': ',' Monthly partition management (reorganize pmax) completed.'));

    /* 6) InnoDB 파티션 통계 재계산 */
    SET @current_step := 'ANALYZE';
    SET @debug_sql := CONCAT('ANALYZE TABLE ', schema_name, '.', var_table_name);
    PREPARE stmt FROM @debug_sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

    INSERT INTO TB_ED_PARTITION_HISTORY_LOG (action_type, partition_name, message)
    VALUES ('ANALYZE', var_table_name,
            CONCAT(var_table_name, ': ',' InnoDB table and partition statistics recalculated (ANALYZE TABLE executed).'));

  END main_block;

END
```

