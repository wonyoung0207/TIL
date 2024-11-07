# Java 버전
Java 11 (jdk)

# Apache Maven 버전
Apache Maven 3.8.7



## Ubuntu용 설치 커맨드
```bash
sudo apt install openjdk-11-jre-headless  # version 11.0.24+8-1ubuntu3~24.04.1

java -version
openjdk version "11.0.24" 2024-07-16
OpenJDK Runtime Environment (build 11.0.24+8-post-Ubuntu-1ubuntu324.04.1)
OpenJDK 64-Bit Server VM (build 11.0.24+8-post-Ubuntu-1ubuntu324.04.1, mixed mode, sharing)


sudo apt install maven

mvn -v

mvn clean install

```

## 자바 .class 파일 컴파일

```bash
mvn compile

// 풀 패키지
mvn clean install
```


## 서버 Run

```bash
// 컴파일 포함
mvn spring-boot:run

// 서버 url, port => src/main/resources 하위에 properties 참조.(application-dev.properties, application-ncp.properties)
http://localhost:33010

```