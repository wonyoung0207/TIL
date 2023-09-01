# docker 관련 명령어

---

>

## 명령어

### run 

1. 명령어 이용 
   1. `docker run [image 이름]`
2. docker desktop 이용
   1. 인터페이스 - image 목록 - run 하고싶은 image 옆에 run 버튼 클릭 

### stop 

1. `docker stop [container 이름 ]` 
2. 하지만 stop 했다고 해서 해당 컨테이너가 삭제되는것이 아니다. 
3. `docker ps -a ` 를 통해 컨테이너가 삭제되지 않은것을 확인할 수 있다. 

### log

1. conatiner의 log 동작을 확인하는 명령어
2. `docker logs -f [container 이름]`
   - \-f 를 입력하면 연속적으로 들어오는 log를 끊김없이 볼 수 있다. 

### rm

1. container 를 삭제하고 싶을떄 사용한다. 
2. `docker rm [container 이름]`
3. 이떄 실행중인 container는 stop으로 멈춰야 삭제할 수 있다. 
   1. 그냥 삭제하고 싶다면 rm 명령어 뒤에 `--force` 를 붙여준다.

### rmi

1. docker의 image를 삭제하는 방법
2. `docker rmi [image 이름]`
3. 연결되어있는 container가 있다면 --force 명령어를 붙여 삭제해준다. 
   - 단 이렇게 삭제 시 연결되어있던 container가 삭제되지는 않는다. 

### touch & cat

1. touch 사용

   - 파일만 생성

     ```java
     // touch '생성할 파일명'
     touch testFile.txt
     ```

2. cat 사용

   - 파일 읽기로 사용되지만 생성도 가능 

   - 파일 생성과 동시에 내용 입력 가능 

     ```java
     // 파일 생성 및 내용작성
      cat > testFile.txt // 자동으로 줄바꿈되면서 입력받음 
      파일내용.... // 다 입력했으면 Ctrl + d 로 입력좋요
          
     // 파일 읽기
     cat testFile.txt
     ```

### commit 

- **docker 컨테이너를 이미지로 변환**하는 과정 

  ```java
  // docker commit [컨테이너이름] [이미지화 시킬 이름]:[tag정보(버전정보)]
  docker commit ubu2 ubu2_image:v1
  ```

### export & save

- docker container & image 내보내기 

1. export 

   - container 파일로 내보내기 

   - `docker export -o [내보내고싶은 파일 형태] [컨테이너이름] 

     ```java
     docker export -o ubu1_container.tar ubu1
     // -o 옵션은 출력 파일이름 지정옵션 
     // 로컬 pc의 cmd 창 경로에 .tar 파일로 저장된다. 
     ```

2. save

   - image 파일로 내보내기 

   - 이미지를 .tar파일로 내보내기 

     ```java
     // docker save [옵션] [파일명] [이미지명]
     docker save -o ubu_sa2.tar ubu2_image
     ```

<img src="./images/docker 명령어 관계도.png" width="400"

### import & load

1. import

   - container 로된 파일 docker image로 가져오기 

   - container 인 .tar 파일을 image로 생성하는 명령어 

     ```java
     // docker import [파일명.tar] [생성이미지 이름]
     docker import ubu1_container.tar utu1_image
     ```

2. load

   - image로 된 파일 image로 가져오기

   - .tar파일이 image 인경우 image 생성하는 명령어 

     ```java
     // docker load -i [파일명.tar]
     docker load -i ubu2_image.tar
     ```

---

### docker Desktop에서 TLS 설정 체크 안하고 cmd에서 명령어 사용하는 방법

1. docker를 start로 시작하기 

   ```cmd
   service docker start
   service docker stop
   ```

2. 사용할 docker 명령어 입력 

   - docker container 실행 

     ```cmd
     $ docker start hello
     ```

   - 실행한 docker container에 접속 ( bin/bash 로 접속 )

     ```cmd
     $ docker attach hello
     ```

   - 가지고 있는 docker 이미지 확인 

     ```cmd
     $ docker images
     ```

   - docker images 목록 확인 

     ```cmd
     $ docker ps -a 
     ```

   - docker 컨테이너 설치 및 실행 

     ```cmd
     $ docker run -it --name hello centos
     or
     $ docker run -it --name hello centos /bin/bash
     ```

     - **run**
       - 이미지를 컨테이너로 생성 후 실행
       - 생성만 원하면 create
     - **-i(interactive), -t(Pseudo-tty)**
       - 실행된 Bash Shell에 입력 및 출력을 할 수 있음
     - **--name**
       - 컨테이너의 이름을 직접 지정 like 닉네임
     - **/bin/bash**
       - centos 안의 /bin/bash 실행

   - docker container 삭제 

     ```cmd
     $ docker rm containerName
     ```

   - docker image 삭제 

     ```cmd
     $ docker rmi containerName
     ```

---

### docker의 리눅스에서 Java 환경설정 에러 

- `source /etc/profile `명령어 실행시 경로 인식 불가 오류 

  - 이유 

    - CLASSPASS 의 경로가 잘못되어있었음

      ```
      export JAVA_HOME=/root/jdk1.8.0_251
      export PATH=$JAVA_HOME/bin:$PATH
      export CLASSPATH=.:$JAVA_HOME/lib.tools.jar
      ```

  - 해결

    - 경로 설정 고쳐줌 

      ```cmd
      export JAVA_HOME=/root/jdk1.8.0_251
      export PATH=$JAVA_HOME/bin:$PATH
      export CLASSPATH=$JAVA_HOME/lib:$CLASSPATH
      ```

---

