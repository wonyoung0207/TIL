

## docker container 변경 사항 반영 방법

- 현재 디렉토리에 있는 `docker-compose-register.yml` 파일을 사용하려면 `-f` 옵션을 줘야 한다. 

```bash
# container 삭제 
docker-compose -f docker-compose-register.yml down

# container 생성
docker-compose -f docker-compose-register.yml up -d --build
```

## runner config 파일 위치

```bash
/etc/gitlab-runner/config.toml
```

