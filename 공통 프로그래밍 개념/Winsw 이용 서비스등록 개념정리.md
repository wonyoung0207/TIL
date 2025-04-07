# Winsw 서비스 등록 개념정리

---

>

## WinSW 실행과정
1. winsw.exe가 실행되면서 XML 설정 파일을 로드
2. XML에 지정된 프로그램(executable 속성)을 실행
3. Windows 서비스로 등록되면, 부팅 시 자동 실행 가능

## 실행 명령어

1. winsw.xml 변경사항 적용 (uninstall 안해도됨)
   1. winsw stop -> winsw refresh -> winsw start

```bash
winsw install [xml파일명]  → Windows 서비스로 등록
winsw start    → 서비스 시작 (.jar 실행)
winsw stop     → 서비스 중지
winsw uninstall → 서비스 제거 
winsw refresh → 서비스 업데이트(xml변경사항 적용)
```

## 구조

| 설정               | 설명                                                         |
| ------------------ | ------------------------------------------------------------ |
| `<name>`           | Windows 서비스 이름을 설정 (`Traffic Digital Twin Nginx`)    |
| `<description>`    | 서비스 설명 (`Traffic Digital Twin Metabuild.co.`)           |
| `<executable>`     | 실행할 프로그램 (`nginx.exe`)                                |
| `<startarguments>` | Nginx 실행 시 사용할 추가 옵션 (`-p`로 Nginx 기본 디렉터리로 설정) |
| `<stopexecutable>` | 종료할 때 실행할 프로그램                                    |
| `<logpath>`        | 로그 파일 저장 경로                                          |
| `<roll-by-size>`   | 로그 크기가 일정 크기를 넘으면 새로운 로그 파일 생성         |

## Application 설정

1. jar 파일 안의 application을 사용할것인지, 외부 application 을 사용할 것인지 결정해야함. 
   1. 현재는 내부를 먼저 불러오고, 외부 설정을 덮어씌우는 방식으로 사용
   2. 즉, 내부/외부 다 사용하지만 동일항목에 대해선 외부 설정으로 적용 
2. `--spring.config.location=classpath:/,file:/path/to/application.properties`
   - JAR 내부 설정 유지
   - 외부 설정이 덮어쓰기 적용됨
   - 외부 설정이 없으면 기본 설정을 사용
3. `--spring.config.additional-location=file:/path/to/application.properties`**
   - JAR 내부 설정을 유지한 채, 외부 설정을 추가
   - 외부 설정이 동일한 속성을 덮어쓰지 않음 (병합)

## WinSW 실행 방식과 `working directory`

1. 디폴트 경로 
   1. winsw 로 실행한 모든 파일의 `classpath: & file:` 는 winsw.exe 를 기점으로 잡힌다. 
   2. 즉, WinSW가 `java.exe`를 실행하면 기본적으로 `winsw.exe`가 위치한 디렉토리를 `working directory`로 설정함
   3. `TrafficDigitalTwin.jar`가 있는 곳이 아니라 `winsw.exe`가 있는 위치가 경로의 기준이 됨
2. `executable` 경로
   1. `winsw.exe`가 있는 디렉토리를 기준으로 해석됨.
   2. **`executable, logpath`의 경로는 `winsw.exe`가 실행될 때 결정됨.**
   3. 즉, `winsw.exe`가 실행되는 위치를 기준으로 Java 실행 파일(`java.exe`)의 경로를 해석함
3. 주의할점
   1. 최신버전에서는 `workingdirectory` 태그 사용못함 

