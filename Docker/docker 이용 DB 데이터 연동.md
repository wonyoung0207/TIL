## 실행 흐름 

```
1. mariadb:11.4 이미지 준비

2. mariadb_data named volume 확인
   └─ 없으면 새로 생성

3. 볼륨 마운트
   mariadb_data → /var/lib/mysql
   ./database/init → /docker-entrypoint-initdb.d
   ./database/conf.d → /etc/mysql/conf.d

4. /var/lib/mysql 상태 확인
   └─ 비어 있음

5. MariaDB 초기화
   ├─ root 비밀번호 설정
   ├─ appdb 생성
   ├─ appuser 생성
   └─ appuser 권한 부여

6. 초기 SQL 실행
   ├─ 01-schema.sql
   ├─ 02-data.sql
   └─ 03-grants.sql

7. DB 파일을 mariadb_data에 저장

8. MariaDB 정식 실행

9. healthcheck 실행

10. healthy 상태가 되면 Backend 연결
```

- 두번째 실행 부터는

  ```
  mariadb_data에 기존 DB 존재
  → DB 초기화 생략
  → 환경변수 기반 DB/사용자 생성 생략
  → init SQL 실행 생략
  → 기존 DB를 그대로 실행
  ```

  