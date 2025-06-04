# git 서버별 인증서 문제해결

---

>

## 정리

- 사내 gitlab 인증서가 변경되어 적용하던 중 사내 Gitlab 말고 **Github 사이트 자체**에 pull, push, fetch 가 안되는 문제가 발생했다. 
  - 사내 Gitlab은 됨.... 
- 그래서 문제를 찾아보다 보니 Root-ca 인증서에 대해 알게되었다. 
  - 즉, **한 번에 모든 HTTPS에 사내 server.crt를 지정하면, 외부 공인 인증서(GitHub, GitLab.com 등)는 무조건 신뢰 실패** 하는 문제였다. 
- 따라서 반드시 도메인별(`http."<url>".sslCAInfo`)로 지정해야한다. 

## 문제 상황

- git config 에 다음 설정을 진행했었다. 

  ```bash
  git config --global http.sslCAInfo "C:/GitLabCRT/server.crt"
  git config --get http.sslCAInfo
  ```

- `http.sslcainfo=C:/GitLabCRT/server.crt` 이 설정은 **모든 git HTTPS 통신**에 **“C:/GitLabCRT/server.crt”** 파일을 “신뢰 루트 인증서”로 사용하도록 만든다. 

- 이 파일은 사내 gitlab 서버(예: http://서버사내IP 등)를 위한 **사설(내부망) 인증서**

- 그런데 **개인 github(https://github.com/...)**에 접속할 때  이 “사설 인증서”만 신뢰하고, **원래 GitHub의 공인 루트 CA들은 신뢰하지 못하게된다.**

- 그 결과, github에 `git pull`, `git push` 할 때 **“SSL certificate problem: unable to get local issuer certificate”**  와 같은 인증서 신뢰 에러가 발생

## 문제 발생 원인

- `http.sslcainfo`는 **“CA 번들”이 아니라 “특정 인증서 1장”만 사용하는 효과**이다.
  - 즉, **GitHub는 공인 루트 CA에 의해 서명**되어 있는데, 내 PC에서는 **오로지 server.crt(사내용)**만 신뢰하기 때문에 GitHub와 SSL 통신이 실패하게 됐다.
- 반대로, 만약 ca-bundle.crt(공인 루트 전체 번들)를 쓰면 사내 서버의 자체서명 인증서는 신뢰하지 못하는 상황이다. 

## 해결방법

- 가장 좋은 방법은 “도메인별(서버별) 인증서 지정” 이다. 
- 즉,
  - **github.com** → 공인 루트 CA 번들(=최신 ca-bundle.crt/cacert.pem) 사용
  - **사내 gitlab** → 사내용 server.crt만 사용
- **전역/로컬에 `http.sslcainfo` 지정이 없으면, Git은 “설치 폴더 내의 기본 루트 인증서 번들 파일”을 사용한다.** 

```bash
# 1. git 설정 확인 
git config --global --list

# 2. 전역 http 통신으로 등록되어있는 인증서 삭제 
git config --global --unset http.sslcainfo

# 3. 사내 인증서를 개별 http 통신에만 사용하도록 변경 
git config --global http."https://사내IP/".sslCAInfo "C:/GitLabCRT/server.crt"

# 4. 전역 http 통신으로 등록되어있는 인증서 없는지 확인 (없으면 디폴트로 잡혀서 통신가능하게 됨)
git config --global --list
```

## 보안 무시 

- | 옵션                              | 용도                        | 보안 영향 | 사용 권장     |
  | --------------------------------- | --------------------------- | --------- | ------------- |
  | http.allowInsecureProtocols true  | http(비암호화) 연결 허용    | **위험**  | 내부망/임시만 |
  | http.https://.../.sslVerify false | 특정 호스트의 SSL 검사 무시 | **위험**  | 내부망/임시만 |

```bash
# http 프로토콜 비암호화 허용 (Git 2.37+)
git config --global http.allowInsecureProtocols true

# SSL 인증서 검증 비활성화 (해당 호스트에만 적용하려면 --global 빼고 URL 부분만 설정)
git config --global http.https://<사내IP:port>/.sslVerify false
```

