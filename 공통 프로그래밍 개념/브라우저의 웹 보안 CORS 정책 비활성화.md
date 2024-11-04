# 브라우저의 웹 보안 CORS 정책 비활성화

---

>

## 소개

1. 해당 문서는 **Google Chrome에서 CORS (Cross-Origin Resource Sharing) 정책을 비활성화** 하는 방법에 대한 이야기이다. 

## cors란? 

1. CORS는 **동일 출처 정책(Same-Origin Policy)**라는 보안 규칙을 보완하기 위해 만들어졌다.  
2. 동일 출처 정책은 웹 페이지가 **다른 도메인에 있는 리소스에 접근하는 것을 제한**하여, 악의적인 사이트가 사용자의 민감한 데이터에 접근하지 못하도록 보호하는 역할을 한다. 

## CORS 비활성화 방법

#### 1. Chrome 설치된 위치로 이동 

1. 대부분 `C:\Program Files\Google\Chrome\Application` 의 경로에 존재한다. 

#### 2. 해당 경로에서 cmd 열기 

#### 3. 명령어 입력

1. **`--disable-web-security`**
   1. **CORS와 관련된 웹 보안 정책을 비활성화**
   2. 다른 도메인에서 요청할 때 CORS 에러가 발생하지 않는다. 
2. `--user-data-dir="C:\chrome_dev"`: 
   1. Chrome이 새롭게 만든 사용자 데이터 디렉토리를 사용하게 한다.
   2. 기존 설정에 영향을 주지 않기 위해, 임시로 사용할 새 프로필을 지정

```
"C:\Program Files\Google\Chrome\Application\chrome.exe" --disable-web-security --user-data-dir="C:\chrome_dev"
```

