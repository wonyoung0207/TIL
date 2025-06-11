# `gradle`과 `gradlew`의 차이

---

>

## 설치 vs. Wrapper

- **`gradle`**
  - **시스템**에 직접 설치한 Gradle 실행 파일을 호출
  - 여러 버전의 Gradle이 설치되어 있으면, 환경 변수(`PATH`)나 `GRADLE_HOME`에 따라 어떤 버전이 쓰일지 달라진다. 
- **`gradlew`**
  - **프로젝트**에 함께 체크인된 “Gradle Wrapper” 스크립트(`gradlew`, Windows는 `gradlew.bat`)를 실행한다. 
  - `gradle/wrapper/gradle-wrapper.properties`에 선언된 정확한 **Gradle 버전을 자동으로 내려받아** 사용하므로, 누구나 같은 버전으로 빌드가 보장된다. 

## 버전 일관성 보장

- `./gradlew`
  - 프로젝트마다 명시된 Gradle 버전(예: 7.6.4)을 강제하게 되어, 팀원마다 다른에러 없이 빌드 환경을 통일
- `gradle` 
  - **로컬에 설치된 버전에 의존**하므로, 버전 차이로 인한 상황이 생길 수 있다.

## 설치 필요 여부

- `gradle` 
  - 사전에 Gradle을 OS에 직접 설치하거나 SDKMAN/Homebrew 등으로 관리해야 한다. 
- `gradlew`
  - Wrapper 스크립트와 JAR만 있으면(기본적으로 프로젝트 생성 시 자동 포함) 별도 설치 없이도 `./gradlew build` 같은 명령이 바로 가능하다. 

## 사용법 예시

```bash
# 시스템에 설치된 gradle 호출
$ gradle clean build

# 프로젝트 루트에 있는 Wrapper 스크립트 호출
$ ./gradlew clean build
# (Windows PowerShell/Command Prompt에서는 gradlew.bat 사용)
```

## 결론:

- **프로젝트 단위로 항상 같은 버전을 쓰고 싶으면 → `./gradlew`**
- **시스템 전체에서 수동으로 설치·업그레이드해 관리하고 싶으면 → `gradle`**