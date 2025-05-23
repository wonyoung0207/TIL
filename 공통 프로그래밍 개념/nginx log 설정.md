# **nginx 로그 설정 완벽 정리**

------

## 1. **로그 종류**

### error_log

- **에러(오류/경고/정보) 로그 기록**
- nginx 전체, http, server, location 블록마다 별도 지정 가능
- 로그 레벨(warn, error, info 등) 지정 가능
   예시: `error_log logs/nginx/error.log warn;`

### access_log

- **클라이언트의 모든 요청(접근) 기록**
- http, server, location 블록마다 별도 지정 가능
- 로그 포맷(로그에 남길 항목)을 지정 가능
   예시: `access_log logs/nginx/access.log main;`

## 2. **기본 동작 및 주의사항**

### error_log 기본 경로 (이 문제때문에 많은 시간소요됨...)

- nginx 프로세스 시작 시 "logs/error.log"에 로그를 남기려 시도
- 이 logs 폴더는 **nginx 실행 디렉토리(혹은 서비스 작업 디렉토리) 기준**
- **logs 폴더가 없으면 nginx 실행 자체가 실패(emerg 에러)**
- error_log 경로는 nginx.conf에서 추가로 얼마든지 지정 가능
   (ex: http, server, location 블록별로 지정)

### access_log 기본 경로

- 기본값: "logs/access.log"
- 폴더가 없으면 로그 기록 안 됨(실행은 됨, 에러만 발생)
- 필요시 블록별로 로그 끄기/파일 변경 가능

## 3. **사용 예시**

```conf
http {
    # 전체 공통 로그
    access_log  logs/nginx/access.log main;
    error_log   logs/nginx/error.log warn;

    server {
        listen 80;
        server_name mysite.com;

        # 이 서버만 별도 로그로 기록
        access_log  ../logs/site1_access.log;
        error_log   ../logs/site1_error.log error;

        location /private {
            # 이 위치에서는 로그 기록하지 않음
            access_log off;
        }
    }
}
```

## 4. **로그 레벨**

- **error_log**는 레벨 지정 가능
  - `debug`, `info`, `notice`, `warn`, `error`, `crit`, `alert`, `emerg`
  - 레벨을 올릴수록 상세 로그
- **access_log**는 `off`로 비활성화 가능

## 5. **로그 경로 해석 기준**

- **상대경로:**
  - `nginx 실행 디렉토리`(혹은 서비스 작업 디렉토리)
  - `-p` 옵션 사용 시: prefix 폴더 기준
- **절대경로:**
  - 어디에서나 명확하게 동작

## 6. **자주 쓰는 패턴**

- **전체 접근 로그 끄기**

  ```
  access_log off;
  ```

- **특정 서버/경로만 로그 남기기**

  ```
  server {
      access_log ../logs/server1.log;
  }
  ```

- **에러로그만 레벨별 분리**

  ```
  error_log logs/nginx/error.log warn;
  server {
      error_log ../logs/server1_error.log error;
  }
  ```

## 7. **실무 주의사항 & 팁**

- **logs 폴더는 반드시 미리 만들어야 함**
- 로그 파일을 여러 개로 분리할 때는,
   경로가 겹치지 않게 주의(폴더도 각각 미리 생성)
- **nginx -t** 명령으로 설정 체크
- 운영 환경에서 로그파일 롤링(분할, 압축)도 필수
   (nginx 자체에는 logrotate 기능 없음 → OS 도구(cron/logrotate) 활용)
- 서비스 실행 계정의 "쓰기 권한"도 필수로 체크
- WinSW 등 서비스 환경에서는 작업디렉토리 기준 경로 반드시 주의!

## 문제발생

##### 1. 기본 error_log는 완전히 off 할 수 없다. 

- nginx는 프로세스가 기동될 때, 설정파일 파싱 전에 무조건 "logs/error.log"로 로그를 남기려 시도한다. 

- nginx.conf에서 main, http, server, location 등 모든 블록에 명시해도, 프로세스 초기화 단계에서의 기본 logs/error.log는 완전히 off가 불가

  ```
  error_log off;
  ```

  