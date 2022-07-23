# Day56

---

> NCP (Naver Cloud Platform)
>
> NCP 서버로 파일 옮기기, 서버 구동방법, workbench 설정
>
> [Final Project 브레인 스토밍](https://minsiks.notion.site/minsiks/Multi-Cam-Final-Project-1feec3f3a5f54f0db4c4e097220f0aab) 

# NCP

## 공인 IP 신청 

1. Server 메뉴-> Public IP
2. 전세계 어디서든 접속할 수 있는 주소 

## Apach Tomcat Download

- [참고 사이트](https://cafe.naver.com/2022webservice?iframe_url=/MyCafeIntro.nhn%3Fclubid=30692828)

- ```
  wget http://archive.apache.org/dist/tomcat/tomcat-8/v8.5.27/bin/apache-tomcat-8.5.27.tar.gz
  ```

- tomcat 실행되고 있는지 확인 

  - ps -ef | grep tomcat


## 파일 서버로 올리기

- cmd 에서 해당 명령어 사용  ,NCP 공인 IP 필요.
- scp -P [외부 포트번호] a.zip root@[공인IP]:/root
  - ex) scp -P 60003 day05.war root@27.96.128.120:/root

## Workbench 설정

- Hostname : NCP의 공인 ip
- Port : 3306
- UserName : admin1
- password : 111111


## 서버 구동

1. CMD로 서버에 파일 저장 

   - scp -P 60003 day05-1.war root@27.96.128.120:/root
   - 공인 ip 를 이용해서 /root에 파일을 저장한다. 

2. apache-tomcat의 webapps 에 파일 복사 

   - tomcat 서버 실행시 해당 폴더의 ROOT 가 실행되기 때문에 해당 폴더로 실행시킬 파일을 옮겨야 한다. 
   - 방법
     - cp /root/day05-1.war . ' 를 사용해  현재 폴더에 복사 

3. war 파일을 해당 폴더에 압축풀기 

   - ```bash
     unzip day05-1.war 
     ```

4. 서버 구동 

   - apache-tomcat-8.5.27 밑에 -> bin -> ' ./startup.sh ' 으로 시작시킴
   - 시작시키면 war파일 자동으로 풀림 

5. 서버 호출 

   - 49.50.175.217:8080 호출
   - 49.50.175.217  : NCP 의 공인 IP 주소 

6. ROOT 로 파일명 변경

   - apache-tomcat-8.5.27 밑에 -> wepapps 밑에있는 ROOT 가 자동으로 호출된다. 

   - 따라서 실행할 파일명을 ROOT 로 변경해주면 tomcat서버 호출시 자동으로 해당 파일이 실행된다. 

   - day05파일의 파일명을 ROOT 로 변경 
     
     ```
     mv day05 ROOT
     //mv 기존파일명 바꿀파일명
     ```

7. 서버 다운

   - apache-tomcat-8.5.27 밑에 -> bin -> ' ./shutdown.sh '  을 사용해서 서버를 다운시킬 수 있다. 

- eclipse application.property 설정

  - 공인 ip 사용 

    ```pro
    spring.datasource.url=jdbc:mysql://49.50.175.217:3306/shopdb?serverTimezone=Asia/Seoul
    ```

# Final Project 

> 주제 선정 브레인 스토밍 
>
> 팀 : 김민식, 김세연, 장효준, 안원영

## 1. 아이디어 회의

------

### 1) 봉사활동 모집/ 참여 사이트

- 구인구직 서비스 플랫폼을 응용한 봉사활동 모집/참여 통합 및 다양한 이벤트 배치
- 실제 봉사활동 모집공고들을 나열시키고 정부의 공식적인 홈페이지와 다른 세련된 UI를 제공
- 지역별 / 지도 상 구분 지어 동적 이미지화시켜 보여준다
- 구현 가능 기술 : 지도 API, 챗봇, 모집공고 분류, 애니메이션 효과 등
- 내용 레퍼런스
  - [1365 자원봉사포털](https://www.1365.go.kr/vols/main.do)
- 서비스 플랫폼 레퍼런스 
  - [채용 정보 | 원티드 (wanted.co.kr)](https://www.wanted.co.kr/jobsfeed?utm_source=google&utm_medium=sa&utm_campaign=kr_recruit_web_sa_signup_brand&utm_term=원티드&utm_content=brand&gclid=CjwKCAjwzeqVBhAoEiwAOrEmzZjWhTHcERvqyBc4i691o911fb-xXQAAxdN2e5bGa_R5eGfRQl_uoRoChaIQAvD_BwE)
  - [채용 정보 | 원티드](https://www.wanted.co.kr/jobsfeed?utm_source=google&utm_medium=sa&utm_campaign=kr_recruit_web_sa_signup_brand&utm_term=원티드&utm_content=brand&gclid=CjwKCAjwzeqVBhAoEiwAOrEmzZjWhTHcERvqyBc4i691o911fb-xXQAAxdN2e5bGa_R5eGfRQl_uoRoChaIQAvD_BwE)
- UI 레퍼런스 
  - [캠핑톡 | 쉽고 편리한 캠핑장 예약, 캠핑톡 (campingtalk.me)](https://www.campingtalk.me/main?gclid=CjwKCAjwzeqVBhAoEiwAOrEmzdmMtH86wSDmtSmqnxVr8Wc_PygpHxp04VILO-st_lzzEh2WmYCGiRoCWwYQAvD_BwE)
  - [쉽고 편리한 캠핑장 예약, 캠핑톡](https://www.campingtalk.me/main?gclid=CjwKCAjwzeqVBhAoEiwAOrEmzdmMtH86wSDmtSmqnxVr8Wc_PygpHxp04VILO-st_lzzEh2WmYCGiRoCWwYQAvD_BwE)

------

### 2) 악기 중고거래 쇼핑몰

- 직접 쇼핑몰에서 거래와 직접 거래 모두 가능
- 악기거래 품목과 판매자의 위치를 고려하여 지도상으로 표시
- 악기와 관련된 이벤트나 커뮤니티 창 구성
- 지금까지 했던 포맷과 유사
- 구현 가능 기술 : 지도 API, 챗봇, 결제 API 등
- 내용 레퍼런스
  - [중고악기 장터 프리미엄 | 뮬 (mule.co.kr)](https://www.mule.co.kr/bbs/market)
  - [중고악기 장터 프리미엄 | 뮬](https://www.mule.co.kr/bbs/market)
- 서비스 플랫폼 레퍼런스 
  - [당신 근처의 당근마켓 (daangn.com)](https://www.daangn.com/)
  - [당신 근처의 당근마켓](https://www.daangn.com/)

------

### 3) 연습실 대관 서비스 플랫폼

- 댄스 연습실 대관 플랫폼밖에 없는 것에 착안
- 악기 및 댄스 연습실 대관을 직접 할 수 있고 그 외 촬영 스튜디오 까지 대관 가능
- 각 항목마다 색을 달리해서 지도에도 표기
- 구현 가능 기술 : 지도 API, 챗봇, 결제 API 등
- 내용 및 서비스 플랫폼 레퍼런스
  - [스페이스클라우드 | No.1 생활 공간대여 플랫폼 (spacecloud.kr)](https://www.spacecloud.kr/theme/1048)
  - [커버댄스는 여기에서! 춤추기 좋은 연습실 - 스페이스클라우드](https://www.spacecloud.kr/theme/1048)
- 지도 api 활용 레퍼런스
  - [스페이스클라우드 | No.1 생활 공간대여 플랫폼 (spacecloud.kr)](https://www.spacecloud.kr/theme/1048)
  - [커버댄스는 여기에서! 춤추기 좋은 연습실 - 스페이스클라우드](https://www.spacecloud.kr/theme/1048)

------

### 4) 숙박 시설 예약 시스템

- [에어비앤비: 휴가용 임대 숙소, 통나무집, 비치 하우스, 독특한 숙소 및 체험](https://www.airbnb.co.kr/)

------

### 5) 음악편집 /믹싱/ 마스터링 / 작곡의뢰 사이트

- 크몽이라는 비슷한 류의 사이트가 존재하지만 카테고리가 광범위하여 좀 더 디테일하게 분류 가능
- 믹싱/ 마스터링은 알음알음 하거나 직접 의뢰를 맡겨야 하는 불편 존재
- 구현 가능 기술 : 카테고리 분류, 결제 API, 챗봇 등
- 내용 및 서비스 플랫폼 레퍼런스
  -  [ No.1 크몽 (kmong.com)](https://kmong.com/category/718?utm_source=google&utm_medium=cpc&utm_campaign=7&gclid=CjwKCAjwzeqVBhAoEiwAOrEmzY0Txbw8R5zYpkkWJoJfFHYiuy26Pq4qgQaUVqyGDQMgfVWJCfbM8BoCq9cQAvD_BwE)

### 6) 사설 환전소 가격비교 사이트

- 각지의 환전소의 가격을 비교 하여 실시간으로 환률을 받아와 적용시켜 가격 비교를 진행
  - ex) 종로환전소 1$당 1367원 / 명동 1$당 1292원
- 구현 가능 기술  : 실시간 서버로 환율 받아서 적용(?), 지도 API, 반응형 웹 등
- 프로젝트 레퍼런스 
  - [[가슴속 3천원\] 붕어빵에 누구보다 진심이었던 한국인의 사이드 프로젝트 | by Depromeet(디프만) | Depromeet | Medium](https://medium.com/depromeet/가슴속-3천원-붕어빵에-누구보다-진심이었던-대한민국인의-사이드-프로젝트-2a3f714026b3)
  - [[가슴속 3천원\] 붕어빵에 누구보다 진심이었던 대한민국인의 사이드 프로젝트](https://medium.com/depromeet/가슴속-3천원-붕어빵에-누구보다-진심이었던-대한민국인의-사이드-프로젝트-2a3f714026b3)

### 7) 부동산 중개

- 카카오 맵 or 국토교통부 + 네이버 챗봇 사용+ 네이버 글자인식 어쩌구 사용
- 아파트 매매 공공 데이터
  - [[파이썬\] 부동산 API로 아파트 매매 실거래가 구하기](https://eslife.tistory.com/1100)
- 국토교통부 api
  - [오픈API](https://www.vworld.kr/dev/v4api.do)

### 8) 관광안내 사이트

- 공공api + 카카오맵 + 네이버 api(트렌드 검색, 블로그)
- 관광지 빅데이
  - [한국관광공사_관광빅데이터정보서비스](https://www.data.go.kr/data/15081754/openapi.do)
  - [Trip To Korea|Korea Travel|tripCheckiner](https://www.tripcheckiner.com/)

### 9) 화장품 판매 사이트

- 네이버 ai 얼굴인식을 활용
- 사진으로 나이 추측 -->스킨로션같은거 추천
- 사진으로 유명인 닮은 꼴 & 인종 --> 유명인 유형으로 해당하는 얼굴에 맞는 메이크업이랑 톤 그런거 추천?

### 10) 중고거래

- 중고거래 사이트(and 커뮤니티, dm) = 카카오 맵

### 11) 영화 추천 사이트

- 현재 상영중인 영화 순위 
  - [영화진흥위원회 오픈API](http://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do)
- 영화들 정보와 그에 비슷한 영화 추천

