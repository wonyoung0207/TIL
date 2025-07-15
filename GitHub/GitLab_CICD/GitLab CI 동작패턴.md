# GitLab CI 동작패턴

---

>[참고 사이트1](https://soonmin.tistory.com/87)
>
>[gitlab CI Docs](https://docs.gitlab.com/ci/)

**Job**

- GitLab CI/CD에서 정의한 개별 작업 단위이다. 
- `.gitlab-ci.yml` 파일에 작성된 각각의 script 명령을 뜻한다.

**Runner**

- Job을 실제로 수행하는 실행 환경이다.
- GitLab과 연동되어, CI/CD 작업이 발생할 때마다 실행 명령을 받아 실제 작업을 수행하는 프로그램이다. 

## CI 동작과정

```bash
1. 코드 Push 또는 MR 생성 → GitLab 서버가 이를 감지
                  │
                  ▼
2. GitLab이 정의된 `.gitlab-ci.yml`에 따라 파이프라인을 생성하고 Job을 실행
                  │
                  ▼
3. GitLab Runner가 작업을 받아 실제 실행 (Runner가 계속해서 GitLab 서버를 Pull 하는 방식)
                  │
                  ▼
4. Runner에서 실행 결과(성공/실패 및 로그)를 GitLab에 보고
                  │
                  ▼
5. GitLab은 파이프라인 결과를 화면에 표시
```

## Runner 작업 순서 

1. GitLab에서 Job 요청을 받음.
2. Runner 환경에서 코드 Clone.
3. `./gradlew clean build` 명령 실행.
4. 빌드가 끝나면 결과물(Artifact)을 GitLab로 전송.
5. 작업 로그와 상태(성공/실패)를 GitLab로 전송.