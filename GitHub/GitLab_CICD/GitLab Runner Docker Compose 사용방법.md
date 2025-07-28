

## docker container 변경 사항 반영 방법

- 현재 디렉토리에 있는 `docker-compose-register.yml` 파일을 사용하려면 `-f` 옵션을 줘야 한다. 

```bash
# container 삭제 
docker-compose -f docker-compose-register.yml down
docker rm gitlab-runner-register

# 이미지 삭제 
docker rmi gitlab/gitlab-runner

# container 생성
docker-compose -f docker-compose-register.yml up -d --build

# container 실행 
docker-compose -f docker-compose-run.yml up -d --build
```

## runner config 파일 위치

- gitlab-runner 의 container 안에 경로를 보면 config.toml 을 볼 수 있다. 
  - 만약 compose 실행시 볼륨설정을 config 에도 했다면 wsl 이나 compose 실행 경로에 config 가 생성되어있는것을 확인할 수 있다. 
- 주의할점
  - docker-compose.yml 계속 실행하면 config.toml 에 컨테이너 실행시마다 config 쌓일 수 있음 

```bash
/etc/gitlab-runner/config.toml
```

## gitlab-runner 컨테이너의 빌드 파일 마운트 

- runner 에서 실행한 작업에 대해 빌드된 파일들을 windows 의 특정 경로로 복사하고 싶었다. 
- 나는 다음 방법을 사용했다. 

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

```yml
#  GitLab 서버에 Runner 등록
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

##### 4. 로컬애서 파일 확인

- 마운트된 파일을 로컬 경로에서 확인한다. 

