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

- 구조

  - 도커의 구조는 크게 두 가지로 나누어진다. 

  1. 클라이언트로서의 도커
  2. 서버로서의 도커

1. 서버 도커 
   - 실제로 **컨테이너를 생성하고 실행하며 이미지를 관리하는 주체**는 서버도커이고, 이는 **dockerd** 프로세스로서 동작한다. 
   - 도커 엔진은 외부에서 API 입력을 받아 도커 엔진의 기능을 수행하는데, 도커 프로세스가 실행되어 서버로서 입력을 받을 준비가 된 상태를 **도커 데몬**이라고 이야기 한다. 
2. 클라이언트 도커 
   - 도커 데몬은 API 입력을 받아 도커 엔진의 기능을 수행하는데, **이 API를 사용할 수 있도록 CLI(Command Line Interface)를 제공**하는 것이 도커 클라이언트이다. 

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

### 실행

- 도커를 사용할 때 docker라는 명령어를 맨 앞에 붙여서 사용한다. 
  - 그리고 실제 docker는 /usr/bin/docker에 위치하고 있다. 이는 'which docker' 명령어를 통해 docker의 위치를 확인할 수 있다. 
  - 따라서 도커 명령어는 /usr/bin/docker에 위치한 파일을 통해 사용되고 있다. 
- 하지만 실제 도커 엔진의 프로세스를 확인해보면 /usr/bin/dockerd 파일로 실행되는 것을 알 수 있다. 
  - 이는 컨테이너나 이미지를 다루는 명령어는 /usr/bin**/docker**에서 실행되지만 도커 엔진의 프로세스는 /usr/bin/**dockerd** 파일로 실행되기 때문이다. 

### 동작방법

- 사용자가 docker로 시작하는 명령어를 입력하면 도커 클라이언트를 사용하는 것이며, 도커 클라이언트는 입력된 명령어를 로컬에 존재하는 도커 데몬에게 API로서 전달한다. 
- 이때 도커 클라이언트는 /var/run/docker.sock에 위치한 유닉스 소켓을 통해 도커 데몬의 API를 호출한다. 
- 예시
  1. 사용자가 docker ps와 같은 도커 명령어를 입력
  2. /usr/bin/docker는 /var/run/docker.sock 유닉스 소켓을 사용해 도커 데몬에게 명령어 전달
  3. 도커 데몬은 이 명령어를 파싱하고 명령어에 해당하는 작업 수행
  4. 수행 결과를 도커 클라이언트에 반환하고 사용자에게 결과 출력

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

---

### cmd에서 docker 명령어 사용시 에러 발생

- This error may indicate that the docker daemon is not running

  - 해당 에러는 일반적으로 **Docker 서비스가 실행 중이 아닌 경우에 발생**한다. 

- 해결방법

  1. **시도1 : docker 재실행**

     - docker desktop을 재실행한다. 

  2. **시도2 : docker Desktop에서 세팅 변경**

     - docker Desktop의 설정 클릭 -> Genera -> '**Expose daemon on tcp://localhost:2375 without TLS' 부분 체크** 하기 

       - TLS
         - TLS 는 SSL의 업데이트 버전으로, 클라이언트와 서버간에 **핸드셰이크를 통해 인증**이 이루어진다. 
         - 또한 **데이터 무결성**을 위해 데이터에 디지털 서명을 하여 데이터가 의도적으로 도착하기 전에 조작된 여부를 확인한다.
         - 따라서 보안떄문에 사용하는 개념이다. 
       - docker daemon
         - 도커 프로세스가 실행되어 서버로서 입력을 받을 준비가 된 상태를 **도커 데몬**이라고 이야기 한다. 

     - 나는 이 방법으로 해결됨. 아마도 docker 가 실행되야 하는데 실행 주체인 daemon이 TLS 로 되어있어 접근을 막은거 같음  

       <img src="./images/docker 오류1.png" width="500">

       <img src="./images/docker 오류2.png" width="500">

  3. **시도3 : cmd에 명령어 입력**

     - ```cmd
       cd C:\Program Files\Docker\Docker
       DockerCli.exe -SwitchDaemon
       ```
