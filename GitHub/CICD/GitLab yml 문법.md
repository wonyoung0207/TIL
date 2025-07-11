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

# 빌드 작업
build-job:
  stage: build
  script:
    - chmod +x ./gradlew
    - ./gradlew clean build -x test
  artifacts:
    paths:
      - build/libs/*.jar   # 빌드 결과물(JAR) 저장

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

### ① `stages`

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

### ② `job-name`

- 각 Job의 이름으로 임의로 지정할 수 있다. 

```yaml
my-build-job:   # Job 이름 자유롭게 설정
```

### ③ `stage`

- Job이 실행될 단계를 지정한다.

```yaml
stage: build  # build 단계에서 수행
```

### ④ `script`

- 해당 Job에서 실행될 실제 스크립트를 작성한다. 

```yaml
script:
  - chmod +x ./gradlew           # gradlew 실행 권한 부여
  - ./gradlew clean build -x test  # 테스트 제외하고 빌드
```

### ⑤ `artifacts`

- 빌드 결과물을 저장하고 GitLab CI에서 다운로드 가능하게 한다. 
- 실제 프로젝트 소스 경로에 저장되는 게 **아니고**, GitLab 서버(파이프라인 실행 환경)에 별도로 저장

```yaml
artifacts:
  paths:
    - build/libs/*.jar
```

- 저장된 아티팩트는 GitLab UI에서 쉽게 다운로드 가능.

### ⑥ `variables`

- gitlab-ci.yml 파일 내에서 환경변수를 선언 해서 사용할 수 있다. 
- 하지만 민감정보는 노출될 위험이 있으므로 project - settings - CI/CD - Variables에서 환경 변수를 설정할 수 있다.

```yaml
variables:
  SPRING_PROFILES_ACTIVE: test
```

### ⑦ `environment`

- 환경(개발/스테이징/운영)을 지정하고 관리할 수 있다. 

```yaml
environment:
  name: production
```

### ⑧ `only`

- 특정 브랜치에서만 실행한다. 

```yaml
only:
  - main   # main 브랜치에서만 실행
```