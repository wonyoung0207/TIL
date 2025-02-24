# Logback 설정 기능

---

>

## 사용 태그 정리

##### 정리

1. 파일/콘솔 출력 설정 → `<appender>`
2. 로그 파일 자동 롤링 → `<rollingPolicy>`
3. 로그 포맷 지정 → `<encoder>`
4. 특정 레벨 로그만 저장 → `<filter>`
5. 패키지별 로그 레벨 설정 → `<logger>`
6. 기본 로그 정책 설정 → `<root>`

### **1. `<configuration>`**

```xml
<configuration scan="true" scanPeriod="10 seconds">
```

📌 **설명**

- Logback 설정의 루트 요소
- `scan="true"`: 설정 파일이 변경될 경우 자동으로 감지하여 재적용
- `scanPeriod="10 seconds"`: 10초마다 설정 파일 변경 여부를 확인

------

### **2. `<appender>`**

```xml
<appender name="fileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
```

📌 **설명**

- 로그를 출력할 대상(파일, 콘솔 등)을 정의하는 요소
- `name="fileAppender"`: appender의 이름 지정 (다른 곳에서 `ref="fileAppender"`로 참조 가능)
- `class="ch.qos.logback.core.rolling.RollingFileAppender"`: 로그 파일을 일정 조건에 따라 회전(롤링)하는 파일 로거 사용

✔ **Appender 종류**

| Appender              | 설명                                          |
| --------------------- | --------------------------------------------- |
| `ConsoleAppender`     | 콘솔에 로그 출력                              |
| `FileAppender`        | 파일에 로그 기록 (고정 파일)                  |
| `RollingFileAppender` | 일정 크기/기간이 지나면 새로운 로그 파일 생성 |

------

### **3. `<rollingPolicy>` (파일 롤링 정책)**

```xml
<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
```

📌 **설명**

- **로그 파일을 주기적으로 교체(롤링)하는 정책 설정**
- `TimeBasedRollingPolicy`: 날짜 기반 롤링을 수행
- `<fileNamePattern>`을 사용하여 파일명 패턴 지정

✔ **예제**

```xml
<fileNamePattern>./logs/tdt/%d{yyyy-MM,aux}/tdt_%d{yyyy-MM-dd}_%i.log</fileNamePattern>
```

- `%d{yyyy-MM,aux}` → 연도-월 별 디렉토리 생성 (`logs/tdt/2025-02/`)
- `%d{yyyy-MM-dd}` → 일별 파일 생성 (`tdt_2025-02-10_1.log`)
- `%i` → 같은 날 파일이 많아질 경우 인덱싱 (`tdt_2025-02-10_2.log`)

------

### **4. `<timeBasedFileNamingAndTriggeringPolicy>`**

```xml
<timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
    <maxFileSize>20MB</maxFileSize>
</timeBasedFileNamingAndTriggeringPolicy>
```

📌 **설명**

- `SizeAndTimeBasedFNATP`: 로그 파일이 일정 크기 이상이면 새로운 파일을 생성
- `<maxFileSize>20MB</maxFileSize>`: 20MB가 넘으면 새로운 로그 파일 생성

------

### **5. `<maxHistory>`**

```xml
<maxHistory>90</maxHistory>
```

📌 **설명**

- 최대 보관할 로그 파일 개수를 설정 (예: 90일 동안의 로그 파일 유지)

------

### **6. `<encoder>` (로그 출력 형식)**

```xml
<encoder>
    <pattern>
    	[%d{yyyy:MM:dd HH:mm:ss.SSS}] [%-5level] --- [%thread] %logger{35} : %msg %n
    </pattern>
</encoder>
```

📌 **설명**

- 로그의 출력 패턴을 지정
- `%d{yyyy:MM:dd HH:mm:ss.SSS}` → 날짜 및 시간 (예: `2025:02:10 12:30:45.123`)
- `%level` → 로그 레벨 (INFO, DEBUG, ERROR 등)
- `%thread` → 로그를 기록한 스레드 이름
- `%logger{35}` → 로그를 발생시킨 클래스명 (최대 35글자)
- `%msg` → 로그 메시지
- `%n` → 줄바꿈 문자

------

### **7. `<filter>` (로그 필터링)**

```xml
<filter class="ch.qos.logback.classic.filter.LevelFilter">
    <level>DEBUG</level>
    <onMatch>ACCEPT</onMatch>
    <onMismatch>DENY</onMismatch>
</filter>
```

📌 **설명**

- 특정 로그 레벨에 대해서만 로그를 기록하도록 필터링
- `<level>DEBUG</level>` → `DEBUG` 레벨 로그만 기록
- `<onMatch>ACCEPT</onMatch>` → 매칭되면 로그 저장
- `<onMismatch>DENY</onMismatch>` → 매칭되지 않으면 무시

✔ **예제**

- DEBUG 로그만 `hex` 파일에 저장
- ERROR 로그만 `errorAppender`에 저장

------

### **8. `<logger>` (특정 패키지별 로그 레벨 설정)**

```xml
<logger name="com.meta.tt" level="INFO" />
```

📌 **설명**

- 특정 패키지(`com.meta.tt`)의 로그 레벨을 `INFO`로 설정

✔ **다른 예제**

```xml
<logger name="hexLogHandler" level="DEBUG" additivity="false">
    <appender-ref ref="hex"/>
</logger>
```

- `hexLogHandler`의 로그 레벨을 `DEBUG`로 설정
- `additivity="false"` → `root` 로거에 로그를 전달하지 않음 (중복 저장 방지)

------

### **9. `<root>` (기본 로그 설정)**

```xml
<root level="INFO">
    <appender-ref ref="fileAppender"/>
    <appender-ref ref="consoleAppender"/>
    <appender-ref ref="errorAppender"/>
</root>
```

📌 **설명**

- **기본 로그 레벨을 `INFO`로 설정**
- `INFO` 이상의 로그는 `fileAppender`, `consoleAppender`, `errorAppender`에 저장

✔ **root 로그 레벨 예제**

| 로그 레벨 | 기록됨?                                         |
| --------- | ----------------------------------------------- |
| `ERROR`   | ✅                                               |
| `WARN`    | ✅                                               |
| `INFO`    | ✅                                               |
| `DEBUG`   | ❌ (DEBUG는 INFO보다 낮은 레벨이므로 기록 안 됨) |

## 예제 코드

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration>
<configuration scan="true" scanPeriod="10 seconds">
	<!--
scan="true": 설정 파일의 변경 사항을 자동으로 감지하여 적용
scanPeriod="10 seconds": 10초마다 설정 파일 변경 여부를 확인
-->
	<!-- 로그 파일 저장을 위한 RollingFileAppender 설정 -->
	<appender name="fileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<!-- 로그 파일 패턴: 연도-월 별 디렉터리 생성, 일별로 파일 구분, 파일 인덱싱 사용 -->
			<fileNamePattern>
./logs/tdt/%d{yyyy-MM,aux}/tdt_%d{yyyy-MM-dd}_%i.log
</fileNamePattern>
			<!-- 로그 파일의 최대 크기를 20MB로 설정 -->
			<timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
				<maxFileSize>20MB</maxFileSize>
			</timeBasedFileNamingAndTriggeringPolicy>
			<!-- 로그 파일의 최대 보관 기간을 90일로 설정 -->
			<maxHistory>90</maxHistory>
		</rollingPolicy>
		<!-- 로그 출력 패턴 설정 -->
		<encoder>
			<pattern>
[%d{yyyy:MM:dd HH:mm:ss.SSS}] [%-5level] --- [%thread] %logger{35} : %msg %n
</pattern>
		</encoder>
	</appender>
	<!-- 콘솔에 로그 출력하는 Appender 설정 -->
	<appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>
[%d{yyyy:MM:dd HH:mm:ss.SSS}] [%-5level] --- [%thread] %logger{35} : %msg %n
</pattern>
		</encoder>
	</appender>
	<!-- DEBUG 레벨 로그를 별도로 저장하는 RollingFileAppender 설정 -->
	<appender name="hex" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<!-- DEBUG 로그만 필터링하여 기록 -->
		<filter class="ch.qos.logback.classic.filter.LevelFilter">
			<level>DEBUG</level>
			<onMatch>ACCEPT</onMatch>
			<onMismatch>DENY</onMismatch>
		</filter>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<!-- 로그 파일 패턴: 연도-월 별 디렉터리 생성, 일별로 파일 구분, zip 압축 적용 -->
			<fileNamePattern>
./logs/hex/%d{yyyy-MM,aux}/hexCode_%d{yyyy-MM-dd}_%i.log.zip
</fileNamePattern>
			<!-- 로그 파일의 최대 크기를 50MB로 설정 -->
			<timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
				<maxFileSize>50MB</maxFileSize>
			</timeBasedFileNamingAndTriggeringPolicy>
			<!-- 로그 파일의 최대 보관 기간을 90일로 설정 -->
			<maxHistory>90</maxHistory>undefined
		</rollingPolicy>undefined
		<!-- 로그 출력 패턴 설정 -->undefined
		<encoder>
			<pattern>
%d{yyyyMMddHHmmssSSS} %m%n
</pattern>undefined
		</encoder>undefined
	</appender>undefined
	<!-- ERROR 레벨 로그를 별도로 저장하는 RollingFileAppender 설정 -->undefined
	<appender name="errorAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">undefined
		<!-- ERROR 로그만 필터링하여 기록 -->undefined
		<filter class="ch.qos.logback.classic.filter.LevelFilter">
			<level>ERROR</level>
			<onMatch>ACCEPT</onMatch>
			<onMismatch>DENY</onMismatch>undefined
		</filter>undefined
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<!-- 로그 파일 패턴: 연도-월 별 디렉터리 생성, 일별로 파일 구분 -->
			<fileNamePattern>
./logs/error/%d{yyyy-MM,aux}/tdt_%d{yyyy-MM-dd}_%i.err
</fileNamePattern>
			<!-- 로그 파일의 최대 크기를 20MB로 설정 -->
			<timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
				<maxFileSize>20MB</maxFileSize>
			</timeBasedFileNamingAndTriggeringPolicy>
			<!-- 로그 파일의 최대 보관 기간을 90일로 설정 -->
			<maxHistory>90</maxHistory>undefined
		</rollingPolicy>undefined
		<!-- 로그 출력 패턴 설정 -->undefined
		<encoder>
			<pattern>
[%d{yyyy:MM:dd HH:mm:ss.SSS}] [%-5level] --- [%thread] %logger{35} : %msg %n
</pattern>undefined
		</encoder>undefined
	</appender>undefined
	<!-- 특정 패키지 로깅 레벨 설정 -->undefined
	<logger name="com.meta.tt" level="INFO" />undefined
	<!-- DEBUG 로그를 hex Appender에만 기록 (중복 저장 방지) -->undefined
	<logger name="hexLogHandler" level="DEBUG" additivity="false">undefined
		<appender-ref ref="hex"/>undefined
	</logger>undefined
	<!-- HikariCP의 로그 레벨을 INFO로 설정하여 너무 많은 로그 발생 방지 -->undefined
	<logger name="com.zaxxer.hikari" level="INFO" />undefined
	<!-- 기본(root) 로거 설정: INFO 이상의 로그를 file, console, errorAppender에 기록 -->undefined
	<root level="INFO">undefined
		<appender-ref ref="fileAppender"/>undefined
		<appender-ref ref="consoleAppender"/>undefined
		<appender-ref ref="errorAppender" />undefined
	</root>undefined
</configuration>
```

