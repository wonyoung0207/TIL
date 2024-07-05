# dockerfile 명령어 정리

---

>[참고 사이트1](https://acet.pe.kr/935)
>
>[참고 사이트2](https://devlog-wjdrbs96.tistory.com/296)
>
>[참고 사이트3](https://www.daleseo.com/dockerfile/)

## dockerFile 

### 정의 

- Docker 이미지(image)가 어떤 단계를 거쳐 빌드(build)되야 하는지를 담고있는 텍스트 파일이다. 
- Docker 이미지는 base 이미지부터 시작해서 기존 이미지위에 새로운 이미지를 중첩해서 여러 단계의 이미지 층(layer)을 쌓아가며 만들어진다
- 즉, **이미지를 `docker run ` 명령어로 실행했을 때, 이 dockerfile 안에 있던 명령어들이 실행되는 것이다.** 
  - dockerFile은 이미지의 설정 및 동작 과정이라고 생각하면 된다. 

### 포멧 및 작성 관례 

- 여러개의 명령문으로 구성되며 명령어는 인자와 구분되기 위해 모두 대문자로 써주는것이 관례이다. 

- 각 명령문은 명령어로 시작하고 여러 개의 인자가 따라올 수 있다. 

  ```dockerfile
  # 주석입니다. 
  명령어(INSTRUCTION) 인자(arguments)
  ```

### 명령어

1. `FROM`

   - docker 이미지는 여러 층인 Layer로 이루어질 수 있다. 

   - 이때 Layer가 뜻하는 것은 하나의 이미지로, 여러 계층으로 만들 수 도 있다. 

   - 여기서 가장 **Base인 이미지를 설정**할 때 사용하는 명령어가 `FROM` 명령어이다. 

     ```dockerfile
     # 형태 
     FROM <이미지명>
     FROM <이미지명>:<태그>
     
     # 예시 
     FROM ubuntu:latest
     FROM postgres
     ```

2. `RUN`

   - shell에서 명령어를 치는 것과 같은 기능으로, `npm install` , `apt-get` 과 같은 작업을 진행할 수 있다. 

   - 보통 이미지 안에 특정 소트트웨어를 설치하기 위해서 많이 사용된다. 

   - 또한 shell의 커맨드(cmd창의 명령어)와 같은 기능을 하므로 **커맨드에서 사용하는 명령어**를 거의 다 사용할 수 있다. 

     ```dockerfile
     # 형태 
     RUN ["<커맨드>", "<파라미터1>", "<파라미터2>"]
     RUN <전체 커맨드>
     
     # 예시 
     RUN npm install # 컨테이너의 설정된 경로에 npm을 install 한다. 
     RUN apk add curl # curl 도구 설치
     ```

3. `CMD`

   - 해당 **이미지를 컨테이너로 띄울때 디폴트로 실행할 커맨드**나 ENTRYPOINT 명령어로 지정된 커맨드에 디폴트로 **넘길 파라미터**를 지정할 때 사용 

   - CMD 명령어는 보통 ENTRYPOINT 명령어와 함께 사용하게 되는데,

     -  ENTRYPOINT 명령어로는 커맨드를 지정하고, 
     -  CMD 명령어로는 디폴트 **파라미터를** 지정한다. 

   - **RUN 명령어와 비슷하게 사용된다.** 

     - 차이점은 CMD 는 **컨테이너 띄울 떄 딱한번 실행**할 수 있고, RUN은 Dockerfile에 여러 개의 RUN 명령어를 선언 할 수 있다.

     ```dockerfile
     # 형태 
     CMD ["echo", "Hi"]
     
     # shell에서 docker run시 CMD 설정한 내용이 한번 발생
     $ docker run -it <image-name>
     # 결과 =>  echo from CMD 
     # CMD는 RUN과 다르게 컨테이너로 띄울 때 한번만 실행되기 때문에 
     ```

4. `WORKDIR`

   - shell 의 **cd 명령어와 같은 방식**으로 사용되어, 작업 디렉토리의 전환(이동) 을 가능하게 하는 명령어이다. 

   - 이 다음에 오는 명령어들은 해당 경로 기준으로 실행된다. 

     ```dockerfile
     # 형태 
     WORKDIR <이동할 경로>
     
     # /usr/app 경로로 이동
     WORKDIR /usr/app
     
     # /usr/app 경로 기준으로 npm 을 설치한다. 
     RUN npm install --silent
     ```

5. `ENTRYPOINT`

   - **이미지를 컨테이너로 띄울 때 항상 실행되어야 하는 커맨드**를 지정할 수 있다. 

   - 즉,  컨테이너가 뜰 때 `ENTRYPOINT` 명령문으로 지정된 커맨드가 실행되고, 이 커맨드로 실행된 프로세스가 죽을 때, 컨테이너도 따라서 종료된다. 

     ```dockerfile
     # 형태 
     ENTRYPOINT ["<커맨드>", "<파라미터1>", "<파라미터2>"]
     ENTRYPOINT <전체 커맨드>
     
     # 예시
     ENTRYPOINT ["npm", "start"] # npm start 스크립트 실행
     ENTRYPOINT ["java","-jar","/app.jar"] # /app.jar 실행 
     ```

6. `ENV`

   - **환경 변수**를 설정하기 위해서 사용

   - `ENV` 명령문으로 설정된 환경 변수는 이미지 빌드 시에도 사용됨은 물론이고, 해당 컨테이너에서 돌아가는 애플리케이션도 접근할 수 있다. 

     ```dockerfile
     ENV NODE_ENV production
     ```

7. `COPY / ADD`

   - `COPY` 명령문은 **호스트 컴퓨터에 있는** 디렉터리나 파일을 **Docker 이미지의 파일 시스템으로 복사**하기 위해서 사용

     - 경로에 주의해야한다. 절대경로와 상대경로 둘다 사용가능
     - 따라서 상대경로 사용시 WORKDIR 명령어로 작업 디렉토리를 어디로 해놨는지 체크해야한다. 

     ```dockerfile
     # 형태 
     COPY source(도커 호스트 OS 파일) dest(이미지 내 위치)
     
     
     # 예시 
     # 호스트 시스템 내 build/libs/app.jar 파일을 WORKDIR 위치에 app.jar라는 이름으로 복사한다
     COPY build/libs/app.jar app.jar
     ```

   - `ADD` 명령문은 일반 파일 뿐만 아니라 압축 파일이나 네트워크 상의 파일도 사용

     - 대부분 `COPY`를 사용하는것이 권장됨 
     - COPY와 다른 특징
       1. 자동 압축해제
          복사 대상 파일이 압축 파일(tar, tar.gz)일 경우 압축을 해제하여 복사한다
       2. Remote file URL
          호스트 시스템의 파일/디렉토리가 아닌 URL을 사용해 복사할 수 있다

     ```dockerfile
     # 형태 
     ADD source(로컬 파일) dest(이미지 내 위치)
     
     # 예시 
     ADD build/libs/app.jar app.jar
     ```

8. `EXPOSE`

   - 컨테이너가 리스닝할 포트 및 프로토콜 설정

   - 이미지를 `docker run` 했을 떄 사용하는 옵션인 `-p` 와 같은 기능을 한다. 

     - 즉, EXPOSE에대가 기본 포트 포워딩 값을 설정해놓으면 `docker run` 시 `-p` 로 지정해주지 않아도 설정 포트로 포트포워딩이 설정된다. 

     ```dockerfile
     # 해당 이미지를 컨테이너화 시키면 80번 포트로 노출(포트포워딩) 된다. 
     EXPOSE 80
     
     # 즉, 로컬에서 80번 포트를 이용하면 해당 컨테이너로 연결된다. 
     # 하지만 docker run 시 -p 옵션을 주면 해당 내용은 사용되지 않는것과 같다. 
     ```

9. `ARG`

   - `docker build` 커맨드로 이미지를 빌드 시, `--build-arg` 옵션을 통해 넘길 수 있는 인자를 정의하기 위해 사용

   - `ARG`로 설정한 값은 이미지가 빌드되는 동안에만 유효

     ```dockerfile
     # shell에서의 build 옵션 설정  
     $ docker build --build-arg port=8080 .
     
     # dockerfile에서의 build 옵션 설정 
     ARG port=8080
     ```

   