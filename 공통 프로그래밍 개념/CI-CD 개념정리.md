# CI/CD 개념정리 

---

>

## CI/CD

### 정의

<img src="./images/CI_CD구조.png" width="700">

1. CI/CD는 **지속적 통합**(CI), **지속적 서비스/지속적 배포**(CD) 를 뜻한다. 
2. CI ( **Continuous Integration** )
   1. 어플리케이션의 새로운 코드 변경 사항이 정기적으로 빌드 및 테스트 되어
      공유 레포지토리에 통합히는 것을 의미
3. CD ( **Continuous Delivery & Continuous Deployment** )
   1. 지속적 서비스 와 지속적 배포가 포함된 언어이다.
4. 정리하자면, 
   1. 작업한 소스 코드를 빌드하고, 저장소에 전달 후 배포까지 하는 과정을 통상적으로 CI/CD라고 부른다.


### CI/CD 등록

1. Github 
   1. Action 에서 해당 기능을 사용할 수 있다. 
2. GitLab
   1. CI/CD 목록에서 파이프라인을 등록할 수 있다. 
3. Teamcity 



## Github의 Action 

> [참고 사이트](https://velog.io/@ggong/Github-Action%EC%97%90-%EB%8C%80%ED%95%9C-%EC%86%8C%EA%B0%9C%EC%99%80-%EC%82%AC%EC%9A%A9%EB%B2%95)

#### 정의

1. Github Action은 event, job, step을 정의하기 위해 YAML 파일을 사용한다. 
   1. 즉, `.github/workflows` 디렉토리 안에 `test-github-actions.yml` 형태로 파일을 생성하면 github 에서 CI/CD를 사용할 수 있다. 
   2. yml 파일안에 build에 관한 작업, 트리거 등의 내용이 포함되어있어  CI/CD 가 진행되는 구조이다. 

#### 진행 순서

1. `workflow`에 파일 등록
   1. workflow 파일 내용은 **자동화할 절차** 라고 생각하면 된다. 
2. 트리거 발생 
   1. 커밋 후 push 하거나 지정한 이벤트가 발생하면 `workflow 디렉토리`에 설정해 놓은 `yml 파일`의 트리거를 실행한다. 
   2. 실행되는 job은 `Action 탭`에서 확인할 수 있다.
3. 실행 목록 확인 
   1. 트리거로 인해 실행된 CI/CD는 `Actions` 탭에서 확인할 수 있다. 
   2. `Actions` 탭에서 기록 확인하고자 하는 `workflow` 를 선택한다. 
   3. 리스트에 보이는 yml을 클릭하면 좌측에 실행되는 job의 name이 뜬다.
   4. 로그를 보면 각 step들이 어떻게 실행되는지를 볼 수 있다.

## Teamcity 

#### 정의

1. CI/CD 를 하기 위한 도구(플랫폼)
2. 개발자가 코드 변경사항을 저장소에 푸시할 때마다 **자동으로 테스트하고, 빌드하고, 배포하는 파이프라인을 구성**할 수 있는 플랫폼이다. 

#### 구조

1. 크게 2가지 구조로 구성된다. 
   1. Teamcity Web 
   2. Teamcity Agent 
2. **Teamcity Web** 
   1. 자동화할 작업들을 설정
   2. VCS 설정, Build Step, Tiggers 같은 작업을 하는 공간 
3. **Teamcity Agent** 
   1. 자원을 사용할 수 있는 **서버** 라고 생각하면 된다. 
   2. Teamcity Web에서 설정한 VCS 같은 일들을 Agent 에다가 위임하고, Build 같은 여러 작업을 Agent 가 처리하게된다. 

#### Teamcity Web

1. VCS (Version Control System )
   1. **코드 저장소와의 통합을 관리한다.**
   2. 즉, **git, gitLab 같은 코드 저장소**를 TeamCity 프로젝트에 연결하는 설정이다. 
   3. 저장소 URL, 인증 정보, 브랜치 규칙 등을 지정할 수 있다. 
2. Build Step
   1. 빌드 프로세스에서 수행될 개별 작업들을 정의한다. 
   2. 즉. 여러 빌드 도구(Gradle or Maven) 와 스크립트를 실행하는 단계들을 설정하는 곳 
   3. 예를들어, 커맨드 라인 스크립트 실행, Maven, Gradle, Ant, Docker Image화 등 다양한 빌드 도구와의 통합이 가능합니다.
      1. step중 하나에다가 SSH를 이용해 원하는 IP 에서 Command를 실행시킬 수도 있다. 
3. Triggers
   1. 새로운 코드 변경 사항이 저장소에 푸시되었을 때, 자동으로 빌드를 시작하도록 설정하는 곳 
   2. 즉, 트리거처럼 이벤트 발생시 Teamcity 프로젝트를 실행하기 위해 사용된다. 

#### Teamcity Agent 

1. **TeamCity 서버에서 할당된 빌드 작업을 실행하는 곳** 
   1. 빌드 작업에는 소스 코드를 가져오고, 컴파일하고, 테스트하고, 아티팩트를 생성하는 모든 단계가 포함될 수 있다.
   2. 서버는 빌드 큐에 작업을 쌓아두고, 에이전트가 사용 가능할 때 작업을 할당한다.
2. AWS 를 사용하는것도 '서버' 용도로 자원을 사용하기 위해서이다. 
   1. Build 및 여러 작업들이 자원을 사용할 수 있는 환경이 필요하고, 물리적 서버가 없다면 클라우드 서버인 AWS 를 이용하는 것도 방법이다. 
3. 즉, 여러 에이전트를 설치하고 **분산 빌드를 통해 병렬로 작업을 처리**하여 빌드 시간을 줄일 수 있게된다. 