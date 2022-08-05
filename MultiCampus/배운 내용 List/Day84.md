# Day84

---

# Final Project

>Google OAuth 로그인 구현
>

### 스프링부트 시큐리티

- [인프런 강의](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0/dashboard)

  - [깃허브 자료](https://github.com/codingspecialist/-Springboot-Security-OAuth2.0-V3)

- JWT ( JSON Web Token)
  - 세션ID의 역할은 

- ```null
  Resource Owner : 액세스 중인 리소스 유저
  - 사용자
  
  Client : Resource Owner를 대신하여 보호된 리소스에 액세스하는 응용프로그램
  - 앱
  
  Resource server : Client의 요청을 수락하고 응답할 수 있는 서버
  - facebook
  
  Authorization server : Resource server가 액세스 토큰을 발급하는 서버
  - facebook
  
  Authorization grant : 클라이언트가 엑세스 토큰을 얻을떄 사용하는 자격 증명
  
  Authorization code : access token을 발급받기 전에 필요한 code
  - 클라이언트 Id, 클라이언트 Secret
  
  Access token : 보호된 리소스에 액세스하는 데 사용되는 credentials(자격)
  - 권한을 나타내는 문자열 타입
  
  Scope : 주어진 액세스 토큰을 사용하여 액세스할 수 있는 리소스 범위
  ```
