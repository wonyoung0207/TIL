# docker 빌드 내용 반영 안되는 문제 정리 

---

>

## 문제점

- 여러 프로젝트( front 2개, backend, db ) 를 하나의 docker compose 로 묶어서 net work 를 공유하도록 했다. 

- 이때 프로젝트의 내부 내용들을 수정 후 재시작을 진행했는데 계속해서 이전 내용들이 변경되지 않는 문제를 발견했다. 

  - 문제가 된 명령어 

    ```bash
    docker compose up -d 
    ```

## 이유 

- `docker compose up -d` 명령어는 이미 로컬에 해당 서비스의 도커 이미지(Image)가 존재하면 **소스코드가 변경되었더라도 이미지를 새로 빌드하지 않고 기존 이미지를 그대로 사용**하여 컨테이너를 재시작 하는 명령어 였다. 

## 해결방법

- `--build` 플래그를 추가하여 명시적으로 재빌드를 지시 필요 

- 즉, 재빌드 명령어를 추가 후 실행해야 적용된다. 

  ```bash
  docker compose up -d --build {compose 설정이름 (container 이름 아님)}
  ```

  

