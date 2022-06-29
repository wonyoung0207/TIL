# Day56

---

> NCP (Naver Cloud Platform)
>
> NCP 서버로 파일 옮기기, 서버 구동방법, workbench 설정

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

- scp -P [외부 포트번호] a.zip root@[포트 포워딩 포트ip]:/root
  - ex) scp -P 60003 day05.war root@27.96.128.120:/root

## Workbench 설정

- Hostname : NCP의 공인 ip
- Port : 3306
- UserName : admin1
- password : 111111


## 서버 구동

1. CMD로 서버에 파일 저장 

   - scp -P 60003 day05-1.war root@27.96.128.120:/root
   - 포워딩 ip (공인 ip 아님) 를 이용해서 /root에 파일을 저장한다. 

2. apache-tomcat의 webapps 에 파일 복사 

   - tomcat 서버 실행시 해당 폴더의 ROOT 가 실행되기 때문에 해당 폴더로 실행시킬 파일을 옮겨야 한다. 
   - 방법
     - cp /root/day05-1.war . ' 를 사용해  현재 폴더에 복사 

3. 서버 구동 

   - apache-tomcat-8.5.27 밑에 -> bin -> ' ./startup.sh ' 으로 시작시킴
   - 시작시키면 war파일 자동으로 풀림 

4. 서버 호출 

   - 49.50.175.217:8080 호출
   - 49.50.175.217  : NCP 의 공인 IP 주소 

5. ROOT 로 파일명 변경

   - apache-tomcat-8.5.27 밑에 -> wepapps 밑에있는 ROOT 가 자동으로 호출된다. 

   - 따라서 실행할 파일명을 ROOT 로 변경해주면 tomcat서버 호출시 자동으로 해당 파일이 실행된다. 

   - day05파일의 파일명을 ROOT 로 변경 
     
     ```
     mv day05 ROOT
     ```

6. 서버 다운

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

- [브레인 스토밍 진행 ](https://padlet.com/tidnjrk010/obsul80ccbcf9wkt)

