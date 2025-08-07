# Docker Compose 볼륨 마운트 정리

---

>

## 호스트와의 볼륨 마운트 필요한 이유

1. **컨테이너 간 파일 공유**
   - Docker는 컨테이너끼리 직접 파일 시스템을 들여다보게 허용하지 않는다. 
   - 대신, 호스트 레벨에 존재하는 **볼륨(volume)** 을 “공유 폴더”처럼 사용해서 파일을 주고받는다. 
2. **캐시 지속성**
   - Runner 컨테이너 자체 파일시스템에만 저장하면, 컨테이너 재시작 시 사라진다. 
   - 볼륨으로 저장하면, Runner 컨테이너가 재시작되어도 캐시가 그대로 남아 있게된다. 

## 마운트 구조 

- runner 에서 실행한 작업에 대해 빌드된 파일들을 windows 의 특정 경로로 복사하고 싶었다. 

```
┌──────────┐            ┌───────────────────┐           ┌───────────────────┐
│   Host   │ ──(1)───▶ │ Runner Container  │ ──(2)──▶ │ Job Container     │
│ (Docker) │           │  (/cache 마운트)  │           │  (node_modules/)  │
└──────────┘           └───────────────────┘           └───────────────────┘
```

**(1) Host ↔ Runner Container**

- `docker-compose.yml` 에 정의한 `gitlab_runner` 볼륨이 실제 호스트에 만들어진다. 
- 이 볼륨을 Runner 컨테이너 경로로 마운트한다. 

**(2) Runner Container ↔ Job Container**

- Runner 설정(`config.toml`)의 `volumes = ["/cache"]` 에 따라, Job 컨테이너도 동일한 Docker 볼륨(`gitlab_runner_cache`)을 `/cache`로 마운트한다. 
- 즉, 모든 Job 컨테이너는 같은 `/cache` 디렉토리를 보고, 데이터를 읽고 쓸 수 있다.

## 파일별 볼륨 마운트 역할 

1. **docker-compose.yml** 

   - Host 와 Runner 컨테이너의 볼륨 설정 역할 
   - `runner-cache` 라는 **named volume** 선언
   - Runner 컨테이너에 `/cache` 마운트

2. **config.toml**

   - 
   - Job 컨테이너에도 `/cache` 볼륨 마운트
   - Runner에게 “캐시는 `/cache` 에서 읽고 쓰라” 고 지시

3. **.gitlab-ci.yml**

   ```yml
   cache:
     key: "$CI_PROJECT_NAME-$CI_COMMIT_REF_SLUG"
     paths:
       - node_modules/
     policy: pull-push
   ```

   - Job 시작 전 `/cache/$KEY` 에서 캐시 복원 → `node_modules/` 채워지고
   - Job 종료 후 `/cache/$KEY` 에 캐시 저장

## 예제 코드 

##### 1. gitlab-ci.yml 파일 설정

- runner 에서 job 실행시 필요 
- job 의 스크립트에서 빌드 파일을 gitlab-runner 컨테이너의 특정 경로로 복사 
- 왜냐하면 job은 runner 와는 별개의 container로 실행되고 사라지기 때문에 runner의 경로로 복사하는 과정이 필요

```yml
backend-deploy: 
  stage: deploy
  image: gradle:7.6.1-jdk17
  script:
      - echo "📦 Clean & build backend"
      - cd backend/dt
      - ./gradlew --no-daemon clean build
      - mkdir -p /output/backend
      - cp build/libs/*.jar /output/backend/  # ⬅ 마운트된 경로로 복사 (별도 컨테이너이기 때문에 마운트 걸어 로컬로 저장될 수 있도록 한다.)
  rules:
    - if: $CI_COMMIT_BRANCH == "dev" && $CI_PIPELINE_SOURCE == "push"
  only:
    - dev
  tags:
    - docker-runner
```

##### 2. compose의 config 파일 생성

- gitlab-runner 에서 실행되는 job 들은 별도 container 로 실행되는데, 이때 **개별 job 실행결과 파일을 저장**하려면 마운트 해야한다. 

- **이때 필요한것이 compose 의 `config.toml` 이다.** 

  ```toml
  # config.toml
  
  check_interval = 0
  
  [[runners]]
    name = "docker-compose-runner"
    url = "[사용자 gitlab url]"
    id = 30
    token = "[gitlab runner token]"
    token_obtained_at = 2025-07-28T00:02:23Z
    token_expires_at = 0001-01-01T00:00:00Z
    executor = "docker"
    [runners.cache]
      MaxUploadedArchiveSize = 0
      [runners.cache.s3]
      [runners.cache.gcs]
      [runners.cache.azure]
    [runners.docker]
      tls_verify = false
      image = "docker:20-dind"
      privileged = false
      disable_entrypoint_overwrite = false
      oom_kill_disable = false
      disable_cache = false
      volumes = ["/var/run/docker.sock:/var/run/docker.sock", "/mnt/c/metabuild/backend:/output/backend", "/cache"]
      shm_size = 0
      network_mtu = 0
  ```

##### 3. docker compose 실행 파일

- job의 실행결과를 runner 의 컨테이너에 복사했다면 마운트를 이용해 원하는 경로로 파일 생성이 된다. 
  - 즉, **compose 실행시 runner 컨테이너의 특정 경로를 로컬의 특정 경로와 마운트** 해놓는다. 
  - GitLab Runner를 **실행(run)** 하기 위한 용도


```yml
#  GitLab 서버에 Runner 등록 용도
version: "3"

services:
  gitlab-runner-register:
    container_name: gitlab-runner-register
    image: 'gitlab/gitlab-runner'
    restart: always
    volumes:
      - './config:/etc/gitlab-runner'
      - '/var/run/docker.sock:/var/run/docker.sock'
      - '/mnt/c/docker-mount:/output/build' # windows 폴더 마운트 (/output/build : runner 컨테이너 폴더 위치)
    command:
      - register
      - --non-interactive
      - --locked=false
      - --name=p1
      - --executor=docker
      - --docker-image=docker:20-dind
      - --docker-volumes=/var/run/docker.sock:/var/run/docker.sock
    environment:
      - CI_SERVER_URL=http://[ip]:[port]
      - REGISTRATION_TOKEN=[gitlab-runner registor token]
    networks:
      - docker-network

networks:
  docker-network:
```

```yml
# docker container run 용도 
version: "3"

services:
  gitlab-runner-run:
    container_name: gitlab-runner-run
    image: 'gitlab/gitlab-runner'
    restart: always
    volumes:
      - './config:/etc/gitlab-runner'
      - '/var/run/docker.sock:/var/run/docker.sock'
      - '/mnt/c/metabuild/build/backend/:/output/backend/'
      - '/mnt/c/metabuild/build/frontend/:/output/frontend/'
    command:
      - run
    environment:
      - CI_SERVER_URL=http://10.10.30.238:8980/
      - REGISTRATION_TOKEN=glrt-ypLtGX5nK5v6paMzyPnm
    networks:
      - docker-network

networks:
  docker-network:

```

##### 4. 로컬애서 파일 확인

- 마운트된 파일을 로컬 경로에서 확인한다. 

