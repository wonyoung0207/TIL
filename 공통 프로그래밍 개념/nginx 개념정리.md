# nginx 개념정리

---

>

## nginx 란? 

1. Nginx(엔진엑스)는 고성능 HTTP 및 리버스 프록시 서버, 로드 밸런서, 메일 프록시 역할을 하는 오픈 소스 **웹 서버**이다. 
2. 즉, 프론트엔드 소프트웨어의 운영을 도와주는 웹 서버 이다. 

## 특징

- 가벼운 구조와 비동기 이벤트 기반 아키텍처로 **빠르고 효율적인 동작**
- 정적 파일 제공(HTML, CSS, JS 등)에 최적화
- **리버스 프록시(Reverse Proxy) 및 로드 밸런서** 기능 제공
- 고성능 및 높은 동시 연결 처리(비동기 이벤트 기반 처리)

## 주요 용도

- 로드 밸런싱 (Load Balancing)
  - 내가 사용한 방법은 주로 로드 벨런싱에 대한 개념이다. 

## `alias` vs `root` 차이

1. root
   1. **상대 경로**를 사용하는 방법
2. alias 
   1. **절대경로**를 사용하는 방법

```bash
# 밑의 두 호출 결과는 같다. (사용법만 다를 뿐)

# 1. root 사용
location /static/ {
    root home/target;
}
# 요청URL: /static/image.png → 실제 파일 경로(로드벨런싱): /home/target/static/image.png 

# 2. alias 사용 
location /static/ {
    alias /home/target/static/;
}
# 요청: /static/image.png → 실제 파일 경로(로드벨런싱): /home/target/static/image.png 
```

## location 의 index 파일 

1. **Nginx의 `index` 지시어 역할**

   1. `index index.html;` 의 의미

   2. `index` 지시어는 **디렉토리에 접근할 때 기본으로 제공할 파일**을 지정

   3. 특정 파일을 요청하면 `index.html`이 적용되지 않음!

      1. `index`는 **디렉토리를 요청할 때만 적용**됨.

   4. 예를 들어, 

      ```bash
      index index.html index.htm;
      ```

       이라고 설정하면,

      - `index.html`이 있으면 제공
      - 없으면 `index.htm`을 찾음

2. ✅**`index`가 동작하는 경우(디렉터리 호출)**

   1. 디렉토리 요청 시 `index.html` 반환
      1. 요청: `http://localhost/smartids/console/`
      2. 실제 반환 파일: `twin/smartids/console/index.html`
      3. `smartids/console/`은 디렉토리이므로, `index.html`을 자동으로 찾아 제공

   ```bash
   location /smartids/console/ {
       root twin;  
       index index.html;
   }
   ```

3. ❌ **`index`가 동작하지 않는 경우 (디렉터리 호출이 아니라 파일 호출이기 때문에) **

   1. 특정 파일 요청 시 `index` 무시됨
      1. 요청: `http://localhost/smartids/console/somefile.html`
      2. 실제 반환 파일: `twin/smartids/console/somefile.html` (파일이 존재하는 경우)
      3. 파일이 없으면? `404 Not Found`

   ```bash
   location /smartids/console/ {
       root twin;  
       index index.html;
   }
   ```

4. `index`와 `try_files` 차이점

   1. `index`는 **디렉토리를 요청했을 때 기본으로 제공할 파일**
      1. 특정 파일 요청 (`somefile.html`) 시에는 **index가 적용되지 않음**
   2. `try_files`는 특정파일 요청시 동작
   3. 즉, `http://localhost/smartids/console/`으로 접속하면 `index.html`이 뜨지만, `http://localhost/smartids/console/somefile.html`을 요청하면 `somefile.html`을 직접 찾는 것

## Backend API 호출

1. API 호출시 nginx 에 의해 로드벨런싱이 일어나 벡엔드의 API 주소로 호출하도록 할 수 있다. 

##### API 로드벨런싱

1. `/api/` 요청시 backend 로 요청 전달 

```bash
# http://localhost/api/users 요청시 로드벨런싱 
location /api/ { 
    proxy_pass http://backend;  # /api/로 시작하는 모든 요청을 backend로 전달
    # http://localhost/api/users 요청이 오면→ http://backend/api/user 로 요청 
}
```

##### 복수의 API 로드벨런싱

1. `/twin/api/`로 시작하는 요청이 오면 **rewrite 적용**
   1. 예) `twin/api/users` → `/twin/users`로 변경
   2. 변경된 URL을 `http://backend` 서버로 전달
   3. 최종 요청: `http://backend/twin/users`
2. **`^~` → `/twin/api/`로 시작하는 모든 요청**을 가장 우선 처리
   1. `break` → 변경된 URL을 그대로 유지하고 `proxy_pass` 실행

```bash
# http://localhost/twin/api/users 요청시 로드벨런싱 
location ^~ /twin/api/ {
    rewrite ^/twin/api/(.*)$ /twin/$1 break;
    proxy_pass http://backend; # backend로 /twin/users 로 요청 
}
```

##### 리다이렉트

1. 정확히 `/twin` 요청 들어오면 `/twin/` 으로 리다이렉트 
   1. `=` 연산자는 **정확히 `/twin` 요청일 때만** 적용

```bash
# http://localhost/twin 호출시 리다이렉트
location = /twin {
    rewrite /twin /twin/ redirect; # http://localhost/twin/ 으로 다시 호출 
}
```

| `location` 설정 | 설명                                                 |
| --------------- | ---------------------------------------------------- |
| `/api/`         | `/api/`로 시작하는 요청을 `backend-server`로 전달    |
| `= /twin`       | 정확히 `/twin`이면 `/twin/`로 리다이렉트             |
| `^~ /twin/api/` | `/twin/api/`를 `/twin/`으로 바꾼 후 `backend`로 전달 |

## Nginx 설정 파일(`nginx.conf`) 블록 

1. 웹 서버인 nginx를 구동할 때 읽히는 파일로, 주로 로드벨런싱(root 경로 지정 및 api 호출) 에 대해 설명하겠다. 
2. Nginx 설정 파일은 여러 개의 **디렉티브(Directive)**로 구성되며, 블록을 사용하여 계층 구조를 가진다.
3. 전역 블록
   1. Nginx 전체의 동작을 제어하는 설정
4. `events` 블록
   1. Nginx가 클라이언트와 연결을 처리하는 방식을 정의
5. `http` 블록
   1. 웹 서버의 HTTP 관련 설정을 정의하는 영역 
6. `server` 블록
   1. Nginx가 특정 포트와 도메인에서 요청을 처리하는 방식을 정의 (로드벨런싱)
7. `location` 블록
   1. 특정 URL 경로에 대한 요청을 처리하는 방법을 정의
   2. `try_files` (파일 존재 여부 확인)
      1. 요청된 파일이 존재하는지 확인하고, 없으면 대체 파일을 반환
   3. `proxy_pass` (리버스 프록시)
      1. Nginx가 요청을 다른 서버로 전달하도록 설정

## Nginx 설정 파일(`nginx.conf`) 예시

```bash
worker_processes auto;  # 사용 가능한 CPU 코어 개수만큼 프로세스 실행

events {
    worker_connections 1024;  # 한 프로세스가 처리할 수 있는 최대 동시 연결 수
}

http {
    include mime.types;  # MIME 타입 설정
    default_type application/octet-stream;

    sendfile on;  # 파일 전송 최적화
    keepalive_timeout 65;  # Keep-Alive 타임아웃 설정
    
	upstream mediaserver {
		server 127.0.0.1:5119 max_fails=3 fail_timeout=1s;
		keepalive 1024;
	}
	
	upstream backend {
		server 127.0.0.1:2001 max_fails=3 fail_timeout=1s;
		keepalive 1024;
	}


	# 이 부분이 각각의 서버(프론트 엔드 소프트웨어) 설정값이다. 
    server {
        listen 80;  # 웹 서버 설정 포트 -> 80에서 요청 수락
        server_name wonyTest;  # 처리할 도메인 이름

        location / {
            root /home/target;  # 정적 파일 제공 (실제 경로 /home/target/)
            index index.html;  # root 경로 밑에 있는 index.html을 반환 
            try_files $uri $uri/ /smartids/dashboard/notFound.html;  
        }
        
        # http://localhost/smartids/dashboard/ 호출시 로드벨런싱 ( 파일 찾기가 아니라 디렉토리 요청이기 때문에 index호출)
        location /smartids/dashboard/ {
			root twin;  # 디렉토리 경로 : twin/smartids/dashboard/ 경로의 하위에서 
			index index.html;
			try_files $uri $uri/ smartids/dashboard/notFound.html;  # 만약 index.html 없다면 smartids/dashboard/notFound.html 파일로 대체 (/smartids/dashboard 하면 절대경로로 바뀜)
			charset utf-8;
		}

		# http://localhost/smartids/console/b.html 호출시 로드벨런싱
		location /smartids/console/ {
			root twin;  # 디렉토리 경로 : twin/smartids/console/ 경로의 하위에서 b.html 파일을 찾음 
			index index.html; # 파일요청이기 때문에 index 동작 x
			try_files $uri $uri/ smartids/console/notFound.html;  # 찾는파일 없으면 smartids/console/notFound.html 파일로 대체
			charset utf-8;
		}

		# /api/로 시작하는 모든 요청을 backend-server로 전달
        location /api/ {
            proxy_pass http://backend;  # 백엔드 서버로 요청 전달
        }
        
        	
		location = /twin { # /twin 요청이 들어오면 → /twin/로 리다이렉트
			rewrite /twin /twin/ redirect;
		}
		
		location ^~ /twin/api/ { # /twin/api/users or /twin/api/equip 요청이 들어오면 로드벨런싱 
			rewrite ^/twin/api/(.*)$ /twin/$1 break;
			proxy_pass http://backend; # /twin/users/ or /twin/equip/ 으로 변경 후 backend 요청 
		}
    }
}
```

