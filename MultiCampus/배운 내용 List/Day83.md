# Day82

---

# Final Project

>Google OAuth 로그인 구현
>

### 스프링부트 시큐리티

- [인프런 강의](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0/dashboard)

  - [깃허브 자료](https://github.com/codingspecialist/-Springboot-Security-OAuth2.0-V3)

- scope의 의미

  - 원래 기본값은 openid, email, profile이다.
  - 하지만 openid가 scope의 값으로 들어가면 Open Id Provider로 인식하게 된다.
  - 그렇게 된다면 openid 서비스를 지원하는 구글과 같은 서비스와, 그렇지 않은 네이버와 카카오 등의 서비스를 나눠서 따로 만들어 줘야 한다.
  - 그렇기 때문에 강제로 openid를 제외한 email과 profile값만 넣어준다.

- 구글

  - 해당 [주소](https://console.cloud.google.com/)로 이동한다.
  - 프로젝트 선택 탭을 클릭한다.
  - 새 프로젝트 버튼을 누른다.
  - 프로젝트 이름
    - 다른 이름과 관련 없이 자유로운 이름
  - 해당 프로젝트로 이동한다.
  - 왼쪽 위의 메뉴 탭을 누른다.
  - API및 서비스 카테고리에 마우스를 올려둔다.
  - 사용자 인증 정보 를 클릭한다.
  - 사용자 인증 정보 만들기를 눌러준다.
  - OAuth 클라이언트 ID를 선택해 준다.
  - 동의 화면 구성을 눌러준다.
    - 애플리케이션 이름을 적어준다.
      - 구글 로그인 시에, 사용자가 보게 될 이름
      - 이 역시 그냥 이름이니 맘대로 지어도 된다.
    - 지원 이메일
      - 사용자 동의 화면에서 노출될 이메일
      - 주로 help 이메일같은걸 사용한다.
      - 하지만 여기서는 그냥 본인의 이메일을 적으면 된다.
    - Google API의 범위
      - 구글 서비스에서 사용할 범위의 목록
      - 구글 서비스에서 정보를 가져올 때, 어떤것들을 가져올지 여부
  - 모든 설정이 끝났다면 OAuth 클라이언트 ID 만들기 화면으로 이동한다.
  - 애플리케이션 유형을 웹 애플리케이션으로 설정해 준다.
  - 승인된 리디렉션 URI
    - 서비스에서 인증을 성공 했을 때 리다이렉트할 URL
    - 스프링 부트2 시큐리티에서는 /login/oauth2/code/{소셜 서비스 코드}를 지운하고 있다.
      - 해당 URL은 시큐리티에서 규현되어 있다.
    - 아직 개발중이니 http://localhost:80/login/oauth2/code/google 로 등록해 준다.

- 네이버

  - 해당 [주소](https://developers.naver.com/apps/#/register?api=nvlogin)로 이동해 준다.

  - 빈칸들을 채운다.

    - 애플리케이션 이름

      - 그냥 이름

    - 사용 API

      - 네이버 아이디로 로그인

    - 제공 정보 선택

      - 이름, 이메일, 프로필 사진을 필수로 가져온다.
      - 우리가 네이버로 부터 받아올 정보를 의미한다.

    - 환경

      - PC 웹

    - 서비스 URL

      - 현재 개발 단계 이기 때문에 https://localhost:8080/ 을 적어준다.
      - 

    - Callback URL

      - 구글의 리다이렉션 URL과 같다.

      - properties 파일에 있는 redirect-uri와 같은값으로 등록해줘야한다. 

        ```properties
        spring.security.oauth2.client.registration.naver.redirect-uri=http://localhost:80/login/oauth2/code/naver 
        ```

      - http://localhost:8080/login/oauth2/code/naver 로 등록해 준다.

- 구글 API 

  - 구글 api는 테스트 상태로 사용할 경우 테스트 사용자로 등록한 id만 로그인이 가능합니다. 앱을 게시해야만 모든 구글 유저 대상으로 서비스가 가능해집니다..
  
  - 사용하기 위해서는 "OAuth동의화면 - 테스트 사용자 " 에 추가해줘야한다. 
  
  - https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=youraccess_token 받기 json을 얻을 수 있습니다.
  
  - Google 계정 ID에 대한 사람 가져오기
  
    ```java
    Person profile = peopleService.people().get("people/account_id")
        .setPersonFields("names,emailAddresses")
        .execute();
    ```

