# GitLab CICD 제공 항목 정리 

---

>[gitlab CI/CD YAML 문법 구조 Docs](https://docs.gitlab.com/ci/yaml/)
>
>[gitlab CI/CD 이미지별 Template](https://gitlab.com/gitlab-org/gitlab/-/tree/master/lib/gitlab/ci/templates)

##### GitLab은 CI/CD를 위한 통합 플랫폼으로서, 크게 다음과 같은 영역을 제공

| 항목                          | 설명                                                |
| ----------------------------- | --------------------------------------------------- |
| **Pipeline (파이프라인)**     | 빌드 → 테스트 → 배포를 자동화해주는 흐름            |
| **Jobs (작업)**               | 파이프라인의 개별 단계 (예: build-job, test-job 등) |
| **Stages (단계)**             | 작업을 논리적으로 그룹화하는 단계                   |
| **Artifacts (아티팩트)**      | 빌드 결과물(JAR, 리포트 등)을 저장하고 공유         |
| **Environments (환경)**       | 배포 대상 환경(개발, 테스트, 운영)을 관리           |
| **Runners (러너)**            | 실제 CI/CD 작업을 수행하는 도구                     |
| **Variables (변수)**          | 민감한 데이터나 환경 설정을 관리하는 환경 변수      |
| **Triggers (트리거)**         | 외부에서 파이프라인을 실행하도록 하는 기능          |
| **Schedule (스케줄)**         | 정기적으로 자동 실행하는 파이프라인                 |
| **Security Scan (보안 검사)** | 정적 코드 분석, 의존성 검사 등을 통한 보안 점검     |
| **Container Registry**        | Docker 이미지를 저장하고 관리하는 저장소            |
| **Merge Request Integration** | MR 생성시 자동 파이프라인 실행 및 코드리뷰 연동     |
| **Monitoring (모니터링)**     | 배포된 환경을 모니터링 및 관리                      |

# **1. Pipeline (파이프라인)**

GitLab CI/CD의 핵심으로, 코드 커밋 또는 MR(Merge Request) 생성 시 자동으로 실행된다. 

```bash
Pipeline 예시:
build → test → deploy
```

### Pipeline 정의 예제 (`.gitlab-ci.yml`)

```yaml
stages:
  - build
  - test
  - deploy

build-job:
  stage: build
  script:
    - ./gradlew clean build
  artifacts:
    paths:
      - build/libs/*.jar

test-job:
  stage: test
  script:
    - ./gradlew test

deploy-job:
  stage: deploy
  script:
    - echo "Deploy!"
  only:
    - main
```

------

# **2. Jobs (작업)**

파이프라인 내 개별 작업으로, 한 단계 내에서도 여러 개의 작업을 병렬로 실행 가능하다. 

```yaml
test-job1:
  stage: test
  script:
    - ./gradlew testUnit

test-job2:
  stage: test
  script:
    - ./gradlew testIntegration
```

------

# **3. Artifacts (아티팩트)**

CI 파이프라인 실행 결과 생성된 파일(JAR, 테스트 리포트 등)을 저장하여 나중에 활용 가능하도록 해준다. 

빌드된 결과물(JAR 파일 등)은 Runner의 작업 디렉토리에 생성

```yaml
build-job:
  stage: build
  script:
    - ./gradlew build -x test
  artifacts:
    paths:
      - build/libs/*.jar

deploy-job:
  stage: deploy
  script:
    - ls build/libs/  # 여기서 build-job의 artifacts 접근 가능
  dependencies:
    - build-job  # 빌드 job의 artifacts를 다운로드
```

------

# **4. Environments (환경)**

각 단계(개발, 테스트, 운영)에 배포하고 관리하는 데 사용하는 개념

```yaml
deploy-staging:
  stage: deploy
  environment:
    name: staging
    url: https://staging.example.com
  script:
    - ./deploy.sh staging

deploy-production:
  stage: deploy
  environment:
    name: production
    url: https://example.com
  script:
    - ./deploy.sh production
```

------

# **5. GitLab Runner (러너)**

GitLab에서 **CI 작업을 실제로 수행하는 프로그램**으로, 서버나 컨테이너 환경에 설치된다. 

Runner는 별도의 임시 디렉토리에서 작업을 수행하게 된다. (빌드된 결과물(JAR 파일 등)은 Runner의 작업 디렉토리에 생성)

- GitLab Shared Runner
- Specific Runner (사용자 설치 Runner)

**Runner가 없으면 파이프라인이 실행되지 않는다.** 

------

# **6. Variables (환경변수)**

파이프라인에서 환경변수나 민감한 정보를 관리할 수 있도록 지원합니다.

- GitLab 메뉴에서 설정 가능

  ```mathematica
  Settings → CI/CD → Variables
  ```

- `.gitlab-ci.yml` 에서 사용 가능

  ```yaml
  deploy-job:
    script:
      - echo "Deploy to $DEPLOY_ENV"
  ```

------

# **7. Triggers & Schedule**

- **Triggers**: 외부 시스템에서 API로 파이프라인 실행
- **Schedule**: 주기적으로 파이프라인 자동 실행

예) 매일 밤 12시 자동 빌드:

```sql
CI/CD → Schedules → New schedule
```

------

# **8. Security & Quality**

GitLab은 코드 품질 및 보안을 검사하는 기능도 지원한다. 

- **SAST (정적 코드 분석)**
- **Dependency Scanning (의존성 점검)**
- **Container Scanning (컨테이너 보안 취약점 검사)**

사용 예시:

```yaml
include:
  - template: Security/SAST.gitlab-ci.yml
```

------

# **9. Container Registry**

Docker 이미지 관리 및 배포를 지원하는 컨테이너 저장소를 제공

```yaml
docker-build:
  stage: build
  script:
    - docker build -t registry.gitlab.com/your_project/app:latest .
    - docker push registry.gitlab.com/your_project/app:latest
```

------

# **10. MR(Merge Request) Integration**

Merge Request를 생성하면 CI/CD 파이프라인이 자동으로 실행되어, 코드 리뷰와 자동화 테스트를 병행한다. 

- MR 상태에서 파이프라인 결과를 실시간 확인 가능

------

# **11. Monitoring & Metrics**

배포 후 운영 상태를 모니터링할 수 있도록 GitLab과 연계하여 모니터링 기능을 제공한다. 

- GitLab 자체 모니터링 (Metrics)
- Prometheus, Grafana 연동 지원