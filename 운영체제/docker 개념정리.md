# docker 개념정리
---
>[나도코딩 docker 강의](https://www.youtube.com/watch?v=Ps8HDIAyPD0&list=PLuHgQVnccGMDeMJsGq2O-55Ymtx0IdKWf)
## docker
### 정의
- 애플리케이션을 모든 종속성과 함께 **컨테이너** 형태로 패키징하기 위해 사용한다.
  - 모든 애플리케이션이 **격리된 환경**에서 작동할 수 있다.
  - 리눅스 운영체제의 container 기능을 사용한다.
- 따라서 **하나의 컴퓨터 환경에서 여러개의 애플리케이션을 독립적으로 패키징하고 컨트롤 할 수있는 환경을 제공**한다. 
### 구조
<img src="./images/docker 구조.png" width="500">

1. **docker hub** 
   - window의 app store 같은 기능을 한다. 
   - docker에서 사용할 수 있는 기능(image)을 받을 수 있는 곳
2. **image**
   - window의 program 과 같은 개념 
   - 실행할 수 있는 파일 
   - image를 run(실행)하게 되면 image가 container가 된다
3. **container**
   - window의 process 와 같은 개념
   - 실행되도록 설정되어있는 기능들이 실행된다 
4. 정리
   - docker 는 하나의 컴퓨터 안에서 여러가지 애플리케이션을 container 라는 개념을 통해 독립적으로 실행하고 관리할 수있는 환경을 제공한다. 

### docker hub 로부터 image 가져오기 
- cmd 창을 열고 `docker pull [가져올 image이름]` 의 형식으로 입력한다. 
- docker 가 지원하고 있는 이미지는 [docker hub 공식 사이트](https://hub.docker.com/search?q=) 에 가면 볼 수 있다.
- 입력하면 docker hub로부터 받은 image를 docker desktop(어플리케이션)을 통해 확인할 수있다. 
### docker 관련 명령어 
1. run 
   1. 명령어 이용 
      1. `docker run [image 이름]`
   2. docker desktop 이용
      1. 인터페이스 - image 목록 - run 하고싶은 image 옆에 run 버튼 클릭 
2. stop 
   1. `docker stop [container 이름 ]` 
   2. 하지만 stop 했다고 해서 해당 컨테이너가 삭제되는것이 아니다. 
   3. `docker ps -a ` 를 통해 컨테이너가 삭제되지 않은것을 확인할 수 있다. 
3. 다시 run 
   1. `docker start [container 이름]`
4. log
   1. conatiner의 log 동작을 확인하는 명령어
   2. `docker logs -f [container 이름]`
      - \-f 를 입력하면 연속적으로 들어오는 log를 끊김없이 볼 수 있다. 
5. rm
   1. container 를 삭제하고 싶을떄 사용한다. 
   2. `docker rm [container 이름]`
   3. 이떄 실행중인 container는 stop으로 멈춰야 삭제할 수 있다. 
      1. 그냥 삭제하고 싶다면 rm 명령어 뒤에 `--force` 를 붙여준다.
6. rmi
   1. docker의 image를 삭제하는 방법
   2. `docker rmi [image 이름]`
   3. 연결되어있는 container가 있다면 --force 명령어를 붙여 삭제해준다. 
      - 단 이렇게 삭제 시 연결되어있던 container가 삭제되지는 않는다. 
### docker host
<img src="./images/docker host구조.png" width="700">

- 하나의 docker host에는 여러개의 container가 만들어질 수 있다. 
  - 따라서 docker는 host를 거쳐 container에 접근할 수 있는 구조를 가지고 있다.
  - `docker run -p 80:80 httpd` 의 명령어를 사용해  host와 container의 포트를 맵핑하는데, 이것을 **'포트 포워딩'** 이라고 한다. 
- 포트 포워딩 
  - docker host의 port 와 container 의 port 번호를 맵핑해주는 것
  - 만약 host의 8080 포트로 서버 연결요청이 들어오면 미리 설정해놓은 container 의 80포트와 포트포워딩되어 웹 서버가 연결된다.
  - **따라서 특정한 포트로 들어오는 데이터 패킷을 다른 포트로 바꿔서 다시 전송해주는 작업이다.**
- 호스트의 특정 포트로 연결 요청이 들어오면 연결되어있는 container 포트로 자동으로 연결해준다. 

### Container 수정방법
1. docker desktop 이용
   1. container 창에서 실행중인 container의 CLI를 클릭한다. 
   2. 그럼 하나의 shell이 뜨는데 이곳에서 해당 container에 직접적으로 명령을 전달할 수 있다. 
2. cmd 이용 
   1. `docker exec ws3 /bin/sh` 
      1. ws3에 한번의 명령어를 전달할 떄 사용하는 방법 
      2. sh 는 쉘을 뜻하며, bash로 변경해도 무관하다. 
   2. `docker exec -it ws3 /bin/sh`
      1. ws3라는 container 에 연속적으로 명령을 전달할 때 사용하는 방법 
3. 수정 방법
   1. 앞의 방법으로 수정하고 싶은 container의 CLI로 들어간다. 
   2. image 다운받은 곳에있는 menual 을 보면 파일의 위치를 찾아가는 방법이 나온다. 
      1. httpd 라는 image의 index.html은 `/usr/local/apache2/htdocs/` 에 있기 때문에 해당 폴더로 이동해 파일의 내용을 변경할 수 있다 .
   3. NANO 에디터를 다운받아 해당 에디터로 파일을 수정한다. 
### Container 와 local 파일을 연결해서 수정하기 
- docker를 사용하는 이유가 쉽게 쓰고 지우기 위해서인데 docker 안에서 너무 많은 작업을 하는것은 좋지 않다. 
  - 따라서 local 에서 파일을 수정하고 해당 파일을 **docker의 container 와 연결해 사용하는 방법**을 쓰는것이 좋다. 
- `docker run -p 8888:80 -v ~/Desktop/dockerTest/docker/htdocs:/usr/local/apache2/htdocs/ httpd`
  - '8888:80' : host의 8888 포트로 연결들어온것을 container의 80 포트와 연결한다. 
  - \~ : 현재 로컬 디렉토리를 뜻한다. 
  - \-v : 를 이용해 local desktop과 docker의 container를 연결할 수 있다. 
  - ' : '  : htdocs 파일과 docker의 htdocs파일을 연결하여 image를 container로 만든다. 
  - 따라서 해당 명령어를 통해 로컬에 있는 파일을 docker에서 구동할 수 있다. local에서 내용을 수정하면 docker에서도 연동되어 내용이 변경된다. 