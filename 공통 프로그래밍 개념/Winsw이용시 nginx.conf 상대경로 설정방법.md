# winsw이용시 nginx.conf 상대경로 설정방법 

---

>

## 정리

1. winsw.xml 이용 모든 서비스 기준위치 : 
   1. winswx.xml 
2. nginx.conf 기준 위치 : 
   1. winsw.xml 에 파라미터 `-p` 로 설정한 위치 
   2. 즉, 나같은 경우는 `nginx.exe` 가 있는 위치 

## 1. 폴더 구조 예시

```
F:\window_git_project\app\
    ├─ infra\
    │    ├─ winsw\
    │    │     └─ winsw.xml
    │    └─ nginx\
    │          └─ nginx.exe 
    │          └─ conf\
    │                └─ nginx.conf
    └─ Project\
         └─ backend\
         └─ frontend\
         └─ logs\
```

---

## 2. nginx 서비스 실행 옵션

- 옵션 종류 
  - **`-p <prefix>`** : nginx의 기준(base) 폴더(=prefix) 지정
  - **`-c <conf파일>`** : 사용할 nginx.conf 파일 직접 지정
  - 두 옵션을 함께 쓰면,
    - **모든 상대경로는 -p로 지정한 prefix를 기준**으로 해석
    - **nginx.conf의 위치는 -p 기준 상대경로 또는 절대경로**로 지정

- WinSW 서비스 설정 예시:

    ```xml
    <executable>../nginx/nginx.exe</executable>
    <arguments>-p ../nginx</arguments>
    ```
- 이 설정의 **작업 디렉토리 기준점**:  

  - `winsw.xml`이 위치한 폴더 
    - (위 예시에서는 `...app\infra\winsw\`)

  - `-p ../nginx` 
    - prefix는  `...app\infra\nginx\`  (nginx.conf 내의 **모든 상대경로 해석 기준**이 됨)


---

## 3. 상대경로 해석의 원리

- **nginx.conf 내의 access_log, error_log, root 등은 모두 prefix(-p로 지정한 경로) 기준 상대경로로 해석된다.**
  - 즉, 현재 폴더 구조로 치면 `winsw.xml` 실행 위치에 `../nginx` 가 붙은 경로이다

- 폴더 경로는 반드시 prefix 기준으로 맞춰야 하며, 실제 원하는 파일 위치까지의 상대 경로를 정확히 계산해야 함.

---

## 4. 실제 상대경로 작성 예시

### access_log, error_log  상대경로

- 실제 저장 위치:  
  
  - `F:\...\app\project\logs\`
  
- nginx.conf 내 설정:

    ```nginx
    access_log ../../project/logs/access.log;
    error_log  ../../project/logs/error.log;
    ```

- 해석:  

  - `infra\nginx\..\..\project\logs\access.log`  


###  root 디렉토리 상대 경로 

- 실제 위치:  
  
  - `F:\...\app\project\frontend\twin\`
  
- **nginx.conf 내 설정:**

    ```nginx
    root ../../project/frontend/twin;
    ```

- **해석:**  

  - `infra\nginx\..\..\project\frontend\twin`  


---

## 5. 실무 팁/유의사항

- **모든 상대경로는 prefix(-p 옵션의 경로) 기준으로 해석됨.**
- 로그, 정적 파일 등 폴더는 반드시 **미리 생성**되어 있어야 함.
  - 폴더 없으면 경로 에러남.(자동생성 못하기 때문)

- 임시폴더(temp/client_body_temp 등)도 필요시 직접 만들어야 함.
- 윈도우 환경에서도 경로 구분자는 슬래시(`/`) 사용을 권장(nginx가 인식 가능).

---

## 7. 핵심 요약

- winsw 와 nginx 를 같이 쓴다면 항상 시작위치는 winsw.xml 의 위치이다. 
- 여기서 winsw.xml 에 작성한 `-p` 옵션으로 인해 nginx.conf 파일의 상대경로 위치가 달라진다. 
- **즉, nginx.conf 기준 위치 는**
  - winsw.xml에서 -p 로 이동한 위치 : `../nginx` 