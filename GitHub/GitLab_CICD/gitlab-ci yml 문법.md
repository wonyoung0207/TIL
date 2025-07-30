# GitLab yml 문법

---

>

## 주요 문법 

1. stages 
   - 작업 순서를 정한다. 
   - 즉, `stage : [...] ` 으로 설정되어있는 것들을 순차 실행한다. 
2. job
   - Runner 가 작업할 일을 지정한다. 
   - stage 에 설정되는 이름으로 Build, test, deploy... 가 있다. **(이름은 자유롭게 변경가능하다.)**
3. script 
   - job에서 실행할 명령어를 지정 

## 예시

```yaml
# 파이프라인 단계 정의
stages:
  - build
  - test
  - deploy

variables:
  SPRING_PROFILES_ACTIVE: test  # 스프링 프로파일 설정 (선택적)
  
frontend-deploy:
  stage: deploy
  image: node:14.17-alpine
  script:
    - echo "📦 Install dependencies & build frontend"
    - cd frontend
    - npm install
    - npm run build
    - mkdir -p /output/frontend
    - cp -r dist/* /output/frontend/   # ⬅ 빌드 결과물 복사
    - ls -lh /output/frontend
  cache:
    key: "node-14-${CI_COMMIT_REF_SLUG}"  # 브랜치별, Node 버전별 분리
    paths:
      - frontend/node_modules/
  rules:
    - if: $CI_COMMIT_BRANCH == "dev" && $CI_PIPELINE_SOURCE == "push"
  tags:
    - klever-twin-runner

# 빌드 작업
build-job:
  stage: build
  script:
    - chmod +x ./gradlew
    - ./gradlew clean build -x test
  artifacts:
    paths:
      - build/libs/*.jar   # 빌드 결과물(JAR) 저장 -> GitLab-Runner 의 vm 내부 경로 

# 테스트 작업
test-job:
  stage: test
  script:
    - chmod +x ./gradlew
    - ./gradlew test
  artifacts:
    paths:
      - build/reports/tests/test/  # 테스트 보고서 저장

# 배포 작업 (예시)
deploy-job:
  stage: deploy
  script:
    - echo "배포 단계 (원하는 배포 스크립트 추가)"
  environment:
    name: production
  only:
    - main   # main 브랜치에서만 수행
```

## **요소별 상세 설명**

### 1. `stages`

- CI 파이프라인이 순차적으로 실행될 단계를 정의한다. 
  - Stage 이름**은 언제든지 자유롭게 변경할 수 있다**
- **정의된 순서대로 실행**된다. 
  - **한 stage 내 여러 job 은 병렬 실행(동시 실행) ** 된다. 

```yaml
stages:
  - build
  - test
  - deploy
```

```bash
# 병렬 실행 예시 
┌─────────────┐
│   build     │
└─────────────┘
       │
       ▼
┌─────────────┐   ┌─────────────┐   ┌─────────────┐
│ unit-test   │   │ integration │   │ security    │
│   job       │   │ test job    │   │ test job    │
└─────────────┘   └─────────────┘   └─────────────┘
       │                 │                 │
       └─────────────────┴─────────────────┘
                         ▼
                 ┌─────────────┐
                 │   deploy    │
                 └─────────────┘
```

### 2.  `job-name`

- 각 Job의 이름으로 임의로 지정할 수 있다. 

```yaml
my-build-job:   # Job 이름 자유롭게 설정
```

### 3. `stage`

- Job이 실행될 단계를 지정한다.

```yaml
stage: build  # build 단계에서 수행
```

### 4.  `script`

- 해당 Job에서 실행될 실제 스크립트를 작성한다. 

```yaml
script:
  - chmod +x ./gradlew           # gradlew 실행 권한 부여
  - ./gradlew clean build -x test  # 테스트 제외하고 빌드
```

### 5.  `artifacts`

- 빌드 결과물을 저장하고 GitLab CI에서 다운로드 가능하게 한다. 
- 실제 프로젝트 소스 경로에 저장되는 게 **아니고**, GitLab 서버(파이프라인 실행 환경)에 별도로 저장

```yaml
artifacts:
  paths:
    - build/libs/*.jar
```

- 저장된 아티팩트는 GitLab UI에서 쉽게 다운로드 가능.

### 6.`variables`

- gitlab-ci.yml 파일 내에서 환경변수를 선언 해서 사용할 수 있다. 
- 하지만 민감정보는 노출될 위험이 있으므로 project - settings - CI/CD - Variables에서 환경 변수를 설정할 수 있다.

```yaml
variables:
  SPRING_PROFILES_ACTIVE: test
```

### 7. `environment`

- 환경(개발/스테이징/운영)을 지정하고 관리할 수 있다. 

```yaml
environment:
  name: production
```

### 8. `only`

- 특정 브랜치에서만 실행한다. 

```yaml
only:
  - main   # main 브랜치에서만 실행
```

### 9. cache

- **`npm install` 후 캐시 생성**
- **Runner가 설치된 로컬 PC/서버의 디스크에 저장**됨
  - 정확한 위치는 Runner의 설정 (`config.toml`)에 따라 다름

```yml
cache:
  key: ${CI_COMMIT_REF_SLUG}
  paths:
    - frontend/node_modules/
```

- `frontend/node_modules/` 폴더를 `.tar.gz` 형식으로 저장함
- `key`는 캐시 식별자 → 브랜치별로 다르게 캐시 생성 가능 (`${CI_COMMIT_REF_SLUG}`는 브랜치 이름을 슬러그화한 값)

1. **다음 파이프라인에서 캐시 복원**
   - `key`가 동일한 경우, 해당 캐시가 존재하면 다운로드 및 압축 해제해서 재사용
2. **복원된 캐시는 CI 컨테이너 내에 자동 복사됨**
   - 예: 다시 `frontend/node_modules/` 경로로 복원됨
3. 캐시 vs 아티팩트 

| 항목      | Cache                              | Artifacts                            |
| --------- | ---------------------------------- | ------------------------------------ |
| 목적      | **속도 개선** (설치된 파일 재사용) | **결과 전달** (예: 빌드 결과물 전달) |
| 저장 기간 | 브랜치별 유지 / 만료일 설정 가능   | Job 종료 후 일정 기간 유지           |
| 예시      | `node_modules`, Gradle `.gradle/`  | 빌드된 `.jar`, `.zip`, `.html`       |



