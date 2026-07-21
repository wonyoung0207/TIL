### Docker run 옵션 종류

1. `--name`

   - 컨테이너 이름 설정

     ```java
     docker run -d -it --name containername imagename bash
     // container 이름을 containername으로 설정 
     ```

2. `--restart`

   - 컨테이너 종료 시, 재시작 정책 설정

     ```java
     docker run -itd --name containername imagename bash // 이미지 imgename을 실행시켜 컨테이너 생성 후 이름을 containername으로 설정
     // -d : 실행시 background에서 실행 
     // bash : bash로 실행 
     ```

3. `--rm`

   - 프로세스(container) 종료시 컨테이너 자동 제거

     ```java
     docker run --rm ubuntu:20.04
     // docker stop containerName
     ```

4. `-p`

   - 호스트와 컨테이너 간의 포트(port) 배포(publish)/바인드(bind)를 위해서 사용

   - 즉, 포트 포워딩에 사용되는 옵션이다. 

     - 이때 주의할점은 포트포워딩 되는 **port번호 중 뒤에 오는 port는** 해당 컨테이너 자체에서 port를 정해놨기 때문에 **지정된 port 번호를 사용**해야한다. 
     - 예를들어, mariadb는 port를 3306 을 사용한다. 

     ```java
     docker run -d --name saintmaria2 -p 2727:3306 mariadb:latest
     // docker host의 2727포트로 요청이 오면 docker 내부의 3306번 포트를 사용중인 container와 연결해준다.
     ```

5. `--restart=always`

   - docker 서버 재부팅 시 설정 container 자동 시작 

     ```java
     docker run -itd --name saintmaria --restart=always -p 23741:23741 --env MARIADB_ROOT_PASSWORD=changeme -e TZ=Asia/Seoul mariadb:latest
     // docker 서버 재부팅 시 해당 컨테이너 실행 
     ```

6. `-e TZ=Asia/Seoul`

   - 해당 container의 환경옵션인 timezone을 현재 local 시간인 서울로 변경한다. 

     ```java
     docker run --name saintmaria -e TZ=Asia/Seou mariadb:latest
     ```

7. `-itd`

   - 컨테이너를 종료하지 않은체로, 터미널의 입력을 계속해서 컨테이너로 전달하기 위해서 사용

     ```java
     docker run -it --name containername imagename bash
     // -itd : d를 붙이면 백그라운드 실행으로 되어 docker container 명령 프롬프트로 넘어가지 않는다. 
     ```

8. `/bin/bash/`

   - 처음 컨테이너를 run 할 때 /bin/bash를 지정해주지 않으면 `docker attach containerName` 을 이용할 때 bash Shell에 접근이 불가능 하다. 

   - 따라서 attach 를 사용하고 싶다면 run 시에 어떤 프롬프트를 사용할 것인지 지정해줘야 한다. 

     - attach : 해당 컨테이너 내부에 접근하게 해주는 명령어이다.  -> `#` 이 붙으면서 컨테이너에 직접적인 명령을 내릴 수 있게 내부 프롬프트로 들어간다. 

   - 하지만 run 할때 /bin/bash 를 붙이면 안된다. 

     - **그래서 attach 거의 안쓰고 `docker exec -it` 기능을 사용해서 내부로 명령어를 전달한다.** 

     ```java
     docker run -itd --name saintmaria mariadb:latest /bin/bash
     ```

9. `-v`

   - 호스트 시스템과 컨테이너 사이에서 데이터를 공유하고 영속성을 확보할 수 있다. 

     - 즉, container에서 사용된 데이터를 호스트 시스템( 윈도우 경로 )의 원하는 경로에 공유하여 저장할 수 있는 기능이다. 

       ```java
       docker run -d --name mysql-container -v C:\Users\user\Desktop\dbdata:/var/lib/mysql mysql:latest
       //C:\Users\user\Desktop\dbdata 의 경로에 container 에서 사용되는 mysql 관련 DB 데이터가 똑같이 저장된다. 
       // 해당 명령어는 컨테이너 생성시 지정해주는것이 좋다. ( 후에 해도 되지만 지정 후 데이터부터 저장됨 )
       ```





---

## 실습 문제 

- MariaDB 컨테이너 생성

  - 공식 이미지를 통한 생성

   - 컨테이너 이름은 saintmaria

   - 서버 재부팅 시 컨테이너 자동 시작

   - 포트 포워딩을 하되, 기본 포트는 보안상 사유로 사용 불허

   - Password : changeme 설정

   - run command와 함께 timezone을 local로 설정

   - 데이터는 서버의 /home/각자 계정/dbdata에 저장되게끔

- 참고 사이트 
  - https://dev-ljw1126.tistory.com/8
  - https://velog.io/@nohsangwoo/docker-mysql-port-%EB%B3%80%EA%B2%BD%ED%95%98%EA%B8%B0
  - https://ironmask43.tistory.com/102

```
docker run -d --name saintmaria --restart=always -p 2727:3306 --env MARIADB_ROOT_PASSWORD=changeme -e TZ=Asia/Seoul -v C:\Users\user\Desktop\dbdata:/var/lib/mysql mariadb:latest
```