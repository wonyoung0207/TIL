# Gradle 버전변경

---

>
>

## 학습이유

- swagger 를 도입하기 위해 gradle 의 workspace를 초기화 후 다시 기동하려고 했다. 
- 변경 후 기동했지만 gradlew의 workspace가 초기화가 재대로 안되는지 이전 Swagger 라이브러리 사용 흔적이 있었다. 
- 이때 JDK ,gradle 라이브러리들간에 버전 호환성을 고려해 gradle 버전을 변경해야겠다고 생각했다. 
- 또한 같은 프로젝트 하는 사람들의 gradle 버전을 통합할 필요가 있다고 생각이 들어 학습하게 되었다.

## 학습내용

1. gradle 과 gradlew 차이점 
1. gradlew 환경 초기화 및 build

## `gradle` VS `gradlew `

1. gradle
   1. 시스템 환경에서의 gradle ( global 이라고 생각하면됨 )
2. gradlew
   - 프로젝트 단위에서 사용되는 gradle 

## gradlew 환경 초기화 및 build 방법

- `gradle` 명령어가 아닌 `gradlew` 명령어 사용할것임 (프로젝트 단위 gradle 설정 하기 위해)

##### 1. 초기화

```bash
# 1) 프로젝트 루트 디렉토리로 이동
cd /path/to/your/project

# gradle 관련 파일 삭제 
rm -rf gradlew gradlew.bat gradle/wrapper
```

##### 2. gradle 설치

```bash
# 1) (시스템에 설치된) gradle 명령어로 Wrapper 생성 및 버전 지정
# 프로젝트 gradlew 버전 변경 ( 자동 )
./gradlew wrapper --gradle-version 7.6.4
# 프로젝트 gradlew 버전 변경 ( 수동 )
distributionUrl=https\://services.gradle.org/distributions/gradle-7.6.4-bin.zip

# 2) 유닉스/리눅스 계열에서 gradlew 실행권한 부여
chmod +x gradlew

# 3) Wrapper 통해 지정 버전 Gradle 배포본 다운로드 및 버전 확인
./gradlew --version

# 4) 실제 빌드나 태스크 실행으로 Wrapper 정상 동작 확인
./gradlew clean build --refresh-dependencies
```

##### 3. 버전확인

```bash
# 글로벌 gradle 버전 
gradle -v

# 프로젝트 gradle 버전 
./gradlew -v
```

##### 4. 에디터 Java 서버 초기화

- 변경된 라이브러리가 적용되지 않을 수 있기 때문에 `Java Server` 초기화 해야함 
- vsCode 인 경우 `Ctrl + Shift + P → Java: Clean Java Language Server Workspace` 
  - 나의 경우엔 아무일도 일어나지 않아 확인해보니 알람이 숨겨져 있었다. (오른쪽 하단 종모양 누르면 `Red hat` 관련  VS Code **Reload Window** 있음)
  -  VS Code 내 Java 언어 서버가 사용하는 **워크스페이스 캐시와 인덱스 초기화**
- **서버가 완전히 새로 캐시를 쌓으므로** 어노테이션 인식 오류 해결 가능 

##### 4. 실행

```bash
./gradlew bootrun
```

