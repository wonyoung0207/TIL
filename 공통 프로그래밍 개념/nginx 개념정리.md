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
      1. 요청: `http://localhost/dualPass/console/`
      2. 실제 반환 파일: `wonyAPI/dualPass/console/index.html`
      3. `dualPass/console/`은 디렉토리이므로, `index.html`을 자동으로 찾아 제공

   ```bash
   location /dualPass/console/ {
       root wonyAPI;  
       index index.html;
   }
   ```

3. ❌ **`index`가 동작하지 않는 경우 (디렉터리 호출이 아니라 파일 호출이기 때문에) **

   1. 특정 파일 요청 시 `index` 무시됨
      1. 요청: `http://localhost/dualPass/console/somefile.html`
      2. 실제 반환 파일: `wonyAPI/dualPass/console/somefile.html` (파일이 존재하는 경우)
      3. 파일이 없으면? `404 Not Found`

   ```bash
   location /dualPass/console/ {
       root wonyAPI;  
       index index.html;
   }
   ```

4. `index`와 `try_files` 차이점

   1. `index`는 **디렉토리를 요청했을 때 기본으로 제공할 파일**
      1. 특정 파일 요청 (`somefile.html`) 시에는 **index가 적용되지 않음**
   2. `try_files`는 특정파일 요청시 동작
   3. 즉, `http://localhost/dualPass/console/`으로 접속하면 `index.html`이 뜨지만, `http://localhost/dualPass/console/somefile.html`을 요청하면 `somefile.html`을 직접 찾는 것

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

1. `/wonyAPI/api/`로 시작하는 요청이 오면 **rewrite 적용**
   1. 예) `wonyAPI/api/users` → `/wonyAPI/users`로 변경
   2. 변경된 URL을 `http://backend` 서버로 전달
   3. 최종 요청: `http://backend/wonyAPI/users`
2. **`^~` → `/wonyAPI/api/`로 시작하는 모든 요청**을 가장 우선 처리
   1. `break` → 변경된 URL을 그대로 유지하고 `proxy_pass` 실행

```bash
# http://localhost/wonyAPI/api/users 요청시 로드벨런싱 
location ^~ /wonyAPI/api/ {
    rewrite ^/wonyAPI/api/(.*)$ /wonyAPI/$1 break;
    proxy_pass http://backend; # backend로 /wonyAPI/users 로 요청 
}
```

##### 리다이렉트

1. 정확히 `/wonyAPI` 요청 들어오면 `/wonyAPI/` 으로 리다이렉트 
   1. `=` 연산자는 **정확히 `/wonyAPI` 요청일 때만** 적용

```bash
# http://localhost/wonyAPI 호출시 리다이렉트
location = /wonyAPI {
    rewrite /wonyAPI /wonyAPI/ redirect; # http://localhost/wonyAPI/ 으로 다시 호출 
}
```

| `location` 설정    | 설명                                                       |
| ------------------ | ---------------------------------------------------------- |
| `/api/`            | `/api/`로 시작하는 요청을 `backend-server`로 전달          |
| `= /wonyAPI`       | 정확히 `/wonyAPI`이면 `/wonyAPI/`로 리다이렉트             |
| `^~ /wonyAPI/api/` | `/wonyAPI/api/`를 `/wonyAPI/`으로 바꾼 후 `backend`로 전달 |

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

##### Log 설정

1. NGINX는 기본적으로 `logs/` 폴더가 없으면 로그 파일을 생성하지 않음.

   1. 따라서 `logs/site1/` 및 `logs/site2/` 폴더를 미리 생성해야 함.

   ```bash
   server {
       listen 34020;
       server_name site2;
   
       access_log C:/nginx-logs/site2/access.log;
       error_log C:/nginx-logs/site2/error.log;
   
       location / {
           root $LOCAL_PATH/$SITE_ROOT;
           index index.html;
           charset utf-8;
       }
   }
   ```

##### 변수설정 set 과 map

1. URL을 하나의 변수처럼 사용하고자 할 때 사용 
   1. 유희할점은 전역, 지역 변수의 개념이 존재해 조심해야함 
2. `set`을 `server` 내부 사용
   1. `set`으로 설정된 `$TWIN_PATH`는 해당 `server` 블록 안에서만 유효
   2. 다른 `server` 블록에서는 `$TWIN_PATH`를 사용할 수 없음
   3. 즉, `server` 블록마다 개별적인 변수처럼 동작
3. `set`을 `server` 외부 사용 ( http 밑에 사용 )
   1. NGINX에서는 **`http` 블록에서 `set`을 사용할 수 없음.**
   2. 즉, `set`은 `server`, `location`, `if` 블록 안에서만 동작함.
   3. `http` 블록에서 공통 변수를 설정하려면 `map`을 사용해야 함.
4.  **여러 `server` 블록에서 공통 변수를 사용하려면 `map`을 활용**

##### include 사용 conf 불러오기 

1. 절대 경로:
   - `/etc/nginx/` (Linux)
   - `C:\nginx\conf\` (Windows)
   - `/usr/local/nginx/conf/` (기본 NGINX 설치 경로)
2. 상대 경로:
   - `include conf/nginx.conf;` → `nginx.exe`가 실행된 **현재 작업 디렉토리**에서 `conf/nginx.conf`를 찾음

##### 사용자 계정 지정

1.  `serviceaccount` 태그는 Windows 서비스 실행 시 사용할 사용자 계정을 설정하는 옵션

2. 즉, 관리자 권한이 필요한 경우, 특정 계정을 사용하여 서비스가 실행되도록 설정 가능.

   ```bash
   <serviceaccount>
       <username>Administrator</username>
       <password>ajict@12</password>
       <allowservicelogon>true</allowservicelogon>
   </serviceaccount>
   ```

##### 자동실행 

1. `onfailure` 태그는 서비스가 비정상적으로 종료될 경우 수행할 동작을 정의하는 설정

2. 일반적으로 서비스 장애 발생 시 자동으로 재시작하도록 설정할 수 있음.

   ```bash
   # 서비스가 충돌하거나 비정상 종료되면 5초 후 자동으로 다시 시작
   <onfailure action="restart" delay="5000"/>
   ```

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
            try_files $uri $uri/ /dualPass/dashboard/notFound.html;  
        }
        
        # http://localhost/dualPass/dashboard/ 호출시 로드벨런싱 ( 파일 찾기가 아니라 디렉토리 요청이기 때문에 index호출)
        location /dualPass/dashboard/ {
			root wonyAPI;  # 디렉토리 경로 : wonyAPI/dualPass/dashboard/ 경로의 하위에서 
			index index.html;
			try_files $uri $uri/ dualPass/dashboard/notFound.html;  # 만약 index.html 없다면 dualPass/dashboard/notFound.html 파일로 대체 (/dualPass/dashboard 하면 절대경로로 바뀜)
			charset utf-8;
		}

		# http://localhost/dualPass/console/b.html 호출시 로드벨런싱
		location /dualPass/console/ {
			root wonyAPI;  # 디렉토리 경로 : wonyAPI/dualPass/console/ 경로의 하위에서 b.html 파일을 찾음 
			index index.html; # 파일요청이기 때문에 index 동작 x
			try_files $uri $uri/ dualPass/console/notFound.html;  # 찾는파일 없으면 dualPass/console/notFound.html 파일로 대체
			charset utf-8;
		}

		# /api/로 시작하는 모든 요청을 backend-server로 전달
        location /api/ {
            proxy_pass http://backend;  # 백엔드 서버로 요청 전달
        }
        
        	
		location = /wonyAPI { # /wonyAPI 요청이 들어오면 → /wonyAPI/로 리다이렉트
			rewrite /wonyAPI /wonyAPI/ redirect;
		}
		
		location ^~ /wonyAPI/api/ { # /wonyAPI/api/users or /wonyAPI/api/equip 요청이 들어오면 로드벨런싱 
			rewrite ^/wonyAPI/api/(.*)$ /wonyAPI/$1 break;
			proxy_pass http://backend; # /wonyAPI/users/ or /wonyAPI/equip/ 으로 변경 후 backend 요청 
		}
    }
}
```

## 멀티 프론트 적용 

1. **두개의 서버** 프로세서를 NginX 로 띄운다
2. port : 9999 
   1. 프론트 표출 역할
   2. `localhost:9999/dual/dashboard` 나 `localhost:9999/dual/console` 로 URL 을 치면 Nginx 가 잡아서  nginx 디렉토리에 있는 `wonyAPI/dual/dashboard or console/` 에 있는 파일을 보여준다. 
3. port : 8888
   1. 주로 API 콜을 Backend로 proxy 하는 역할 
   2. `localhost:8888/wonyAPI/api/` 처럼 port 8888 로 오는 요청들을 잡아서 설정해놓은 대로 proxy 해줌. 


```bash
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;

events {
    worker_connections  1024;
}


http {

	include       mime.types;
    default_type  application/octet-stream;
	
	sendfile        on;

    keepalive_timeout  65;
	
	upstream mediaserver {
		server 127.0.0.1:5119 max_fails=3 fail_timeout=1s;
		keepalive 1024;
	}
	
	upstream backend {
		server 127.0.0.1:5555 max_fails=3 fail_timeout=1s;
		keepalive 1024;
	}

	server {
        listen       9999;
        server_name  localhost;

		location /dual/dashboard/ {
			root wonyAPI;  
			index index.html;
			try_files $uri $uri/ /dual/dashboard/notFound.html; 
			charset utf-8;
		}


		location /dual/console/ {
			root wonyAPI;  
			index index.html;
			try_files $uri $uri/ /dual/console/notFound.html;  
			charset utf-8;
		}
	}

	server {
		listen       8888;
		server_name  localhost;
	
		location = /wonyAPI {
			rewrite /wonyAPI /wonyAPI/ redirect;
		}
		
		location ^~ /wonyAPI/api/ {
			rewrite ^/wonyAPI/api/(.*)$ /wonyAPI/$1 break;
			proxy_pass http://backend;
		}
		# testProxy 요청을 127.0.0.1:9079로 프록시 패스
		location /testProxy/ {
			proxy_pass http://127.0.0.1:1234;
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		}
		
		location ^~ /wonyAPI/relay/ {
			proxy_pass http://backend;
		}
		
		location ^~ /wonyAPI/websocket {
			proxy_http_version 1.1;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "upgrade";
			proxy_set_header Host $host;
			
			proxy_pass http://backend;
		}

		location ^~ /netty {
			proxy_http_version 1.1;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "upgrade";
			proxy_set_header Host $host;

			# proxy_pass http://$server_addr:34048/wonyAPI/websocket;
			proxy_pass http://localhost:34048/wonyAPI/websocket;
		}

		
		location ^~ /webrtc_playnow {
			proxy_http_version 1.1;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "upgrade";
			proxy_set_header Host $host;
			
			proxy_pass http://mediaserver;
		}

		location /wonyAPI/ {
			rewrite ^/wonyAPI/(.*)$ /$1;
		}

    
        error_page  404              /404.html;
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
	
    }
}

```

