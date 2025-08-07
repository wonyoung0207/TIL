

## docker container 변경 사항 반영 방법

- 현재 디렉토리에 있는 `docker-compose-register.yml` 파일을 사용하려면 `-f` 옵션을 줘야 한다. 

```bash
# docker compose 삭제 
docker compose -f docker-compose-run.yml down -v
docker compose -f docker-compose-register.yml down -v

# 컨테이너 삭제 (볼륨 삭제 안됨)
docker rm gitlab-runner-register

# 이미지 삭제 
docker rmi gitlab/gitlab-runner:latest

# docker compose 이용 container 생성
# register : Gitlab runner 와 연결하기 위해 필요 
# run: runner 컨테이너 실행 
docker compose -f docker-compose-register.yml up -d --build 
docker compose -f docker-compose-run.yml up -d --build 
```

## runner config 파일 위치

- gitlab-runner 의 container 안에 경로를 보면 config.toml 을 볼 수 있다. 
  - 만약 compose 실행시 볼륨설정을 config 에도 했다면 wsl 이나 compose 실행 경로에 config 가 생성되어있는것을 확인할 수 있다. 
- 주의할점
  - docker-compose.yml 계속 실행하면 config.toml 에 컨테이너 실행시마다 config 쌓일 수 있음 

```bash
/etc/gitlab-runner/config.toml
```

## 주의할점

1. **컨테이너 삭제 시 볼륨이 사라지지 않음** 

   - **즉, docker compose 로 down 시 볼륨 옵션으로 같이 삭제해야 함** 
   
   ```bash
   docker rm my-container  # 볼륨은 남아있음
   docker rm -v my-container  # Anonymous Volume만 같이 삭제
   ```

   **Named Volume은 명시적으로 지우지 않으면 계속 남아있음**
   
   ```bash
   docker volume rm my_volume
   ```

## 설정파일별 역할

1. register 
   1. `gitlab-runner-register` 컨테이너는 **"등록만" 수행**
   2. 이 컨테이너는 `register` 명령으로 `/etc/gitlab-runner/config.toml`을 생성할 뿐이고,
   3. 실질적인 **Job 실행 시점**에는 `config.toml`에 있는 `[[runners.docker.volumes]]`가 적용된다. 

2. 흐름
   1. `gitlab-runner-register` 컨테이너 → `register` 명령 → `config.toml` 생성
   2. `config.toml` 안의 `volumes = [...]` 설정은 이후 모든 CI Job에 **자동 반영**
   3. 따라서 등록 시 `--docker-volumes`는 최소한만 적어도 되고, 핵심은 `config.toml`

| 구성 요소                                   | 역할                                               | 설명                                     |
| ------------------------------------------- | -------------------------------------------------- | ---------------------------------------- |
| `docker-compose.yml`에서 `--docker-volumes` | 등록 시 컨테이너 안에 어떤 볼륨 설정을 넣을지 지정 | 즉시 사용됨 (예: `/var/run/docker.sock`) |
| `config.toml`에서 `runners.docker.volumes`  | **CI Job 실행 시 마운트**할 경로 지정              | **이 설정이 실제 빌드 환경에서 사용됨**  |

