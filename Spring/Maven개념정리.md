# Maven 개념정리

---

## Maven

### 정의

- pom.xml파일을 통해 정형화된 **빌드 시스템**으로 **프로젝트 관리**를 해준다
- Spring을 사용한 웹 개발 시, 필요한 라이브러리(.jar 파일)들이 있다.
  - 해당 라이브러리들은 **의존관계**가 있어, 필요한 A가 있어도 A에게 필요한 B가 없으면 실행되지 않는다.
  - 이렇게 **라이브러리들을 관리해주는 도구**가 **Maven**이다.
- 따라서 Maven은 **라이브러리들을 관리해주는 도구** 이다. 

#### 빌드 도구 ( Build Tool)

- 프로젝트 생성, 테스트 빌드, 배포 등의 작업을 위한 전용 프로그램
- 계속 늘어나는 **라이브러리의 버전 동기화의 어려움을 해소**하고자 등장했다. 
- 초기에는 Ant 를 사용했으나 현재는 **Maven과 Gradle**이 많이 쓰인다. 

### 사용이유

- spring은 의존성 주입이 필요하게 된다. 이때 pom.xml에 필요한 **여러가지 라이브러리들**을 정의해주게 되는데 Maven이 네트워크를 통해서 **자동으로 라이브러리들을 다운**받아 관리해준다.

### POM ( Project Object Model )

- pom.xml파일을 말한다.
- Maven의 기능을 이용하기 위해서 pom이 사용된다. 
- pom.xml을 보면 프로젝트의 **설정, 의존성**을 알 수 있다. 

### pom.xml 사용예시 

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>kr.or.connect</groupId>
    <artifactId>examples</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>mysample</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

- project : pom.xml 파일의 최상위 루트 엘리먼트(Root Element)
- modelVersion : POM model의 버전
- groupId : 프로젝트를 생성하는 조직의 고유 아이디를 결정한다. 일반적으로 도메인 이름을 거꾸로 적는다.
- artifactId : 해당 프로젝트에 의하여 생성되는 artifact의 고유 아이디를 결정한다. Maven을 이용하여 pom.xml을 빌드할 경우 다음과 같은 규칙으로 artifact가 생성된다. artifactid-version.packaging. 위 예의 경우 빌드할 경우 examples-1.0-SNAPSHOT.jar 파일이 생성된다.
- packaging : 해당 프로젝트를 어떤 형태로 packaging 할 것인지 결정한다. jar, war, ear 등으로 할 수 있다.
- version : 프로젝트의 현재 버전. 추후 살펴보겠지만 프로젝트가 개발 중일 때는 SNAPSHOT을 접미사로 사용한다.
  - Maven의 버전 관리 기능은 라이브러리 관리를 편하게 합니다.
- name : 프로젝트의 이름이다.
- url : 프로젝트 사이트가 있다면 사이트 URL을 등록하는 것이 가능하다.