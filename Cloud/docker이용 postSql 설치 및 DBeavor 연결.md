# docker로  postgreSql 사용

---

> [참고 사이트1](https://velog.io/@dailylifecoding/how-to-connect-dbeavor-to-docker-postgresql)
>
> [docker 에 postgreSql DB 세팅](https://shyvana.tistory.com/8)

## 1. postgreSql 이미지 다운

- docker Hub에 있는 postgreSql 다운 

  ```bash
  dokcer pull postgres
  ```

## 2. 컨테이너 실행

- docker 이미지를 이용해 컨테이너 생성

  ``` bash
  # 예시 
  docker run -itd --name [컨테이너명] -p 5432:5432 --restart=always -e POSTGRES_NAME=[DB 설정 이름] -e POSTGRES_PASSWORD=[DB 접속 비밀번호] [실행할 image] 
  
  # 컨테이너 실행 
  docker run -itd --name postgresDB -p 5432:5432 --restart=always -e POSTGRES_NAME=postgres -e POSTGRES_PASSWORD=1234 postgres
  ```

## 3. 접속 방법

1. 접속 명령어
   - ` docker exec -it postgresDB /bin/bash ` 
     -  docker 컨테이너 접속 
   - `psql -h localhost -U postgres`
     - postgres 컨테이너에서 postgresDB 로 접속 
     - `psql -U postgres` 로도 가능 
2. 명령어 의미 
   1. `psql`
      - PostgreSQL 데이터베이스에 접속하기 위한 명령어
      - 이 명령어는 터미널에서 PostgreSQL 데이터베이스 서버에 접속하고 SQL 쿼리를 실행할 수 있는 인터페이스를 제공한다. 
   2. `-h localhost`
      - PostgreSQL 서버의 호스트를 지정하는 옵션
      - 여기서 "localhost"는 로컬 컴퓨터를 의미하며, 명령이 실행되는 컴퓨터에 설치된 PostgreSQL 서버에 연결하려는 것을 나타낸다.
      - 만약 다른 호스트에 PostgreSQL 서버가 설치되어 있다면 해당 호스트의 IP 주소나 호스트 이름을 사용해야 한다. 
   3. `-U postgres`
      - 접속하려는 PostgreSQL 데이터베이스의 사용자 이름을 뜻한다 .
      - 여기서 "postgres"는 일반적으로 PostgreSQL을 설치할 때 기본적으로 생성되는 슈퍼유저(superuser) 계정의 이름이다. 
   4. -i
      - 사용자가 입출력 할 수 있는 상태
   5. -t 
      - 가상 터미널 환경을 에뮬레이션 하겠다.
   6. -d
      - 컨테이너를 일반 프로세스가 아닌 데몬프로세스로 실행하여 프로세스가 끝나도 유지되도록 한다.(백그라운드)

## 4. 슈퍼 계정 추가

```postgresql
$ docker exec -it pgsql bash -- bash 접속
/# psql -U postgres -- postgres db 접속

-- +++++++++++++++++ 쿼리 시작 +++++++++++++++++ 
-- DB 목록 조회 
postgres=# \l

-- 사용자 생성 
postgres=# CREATE USER userId WITH SUPERUSER CREATEDB LOGIN ENCRYPTED PASSWORD '1234'; -- 슈퍼 유저계정 생성

-- 사용자 리스트 확인 
SELECT * FROM PG_USER
```

