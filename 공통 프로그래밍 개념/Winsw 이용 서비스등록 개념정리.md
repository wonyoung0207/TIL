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

## 서비스 제거(uninstall)

1. **WinSW에서 `uninstall` 명령을 실행할 때는 `.xml` 내부의 `<id>` 값만 사용**

   - 즉, **로그 설정, arguments, executable 등 나머지 설정은 전혀 사용하지 않는다.**

2. 동작방식

   1. `<id>` 값을 읽는다. 예: `<id>TrafficDigitalTwinNetty</id>`

      ```cmd
      winsw.exe uninstall service.xml
      ```

   2. 해당 **Windows 서비스 이름(ID)** 을 기준으로 서비스 제거 명령을 실행

      ```
      sc delete TrafficDigitalTwinNetty
      ```

   3. 종료. 다른 설정(`arguments`, `logpath`, `name`, `description`, ...)은 **무시**



## 서비스 등록 자동화 bat

```bat
@echo off
:: 관리자 권한 체크
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo This script needs to be run as Administrator.
    echo Please approve the UAC prompt.
    powershell -Command "Start-Process '%~f0' -Verb RunAs -WorkingDirectory '%CD%'"
    exit /b
)
cd /d "%~dp0"
setlocal enabledelayedexpansion

REM ===== XML file list (You can modify here) =====
set XML1=dt.xml
set XML2=nginx.xml
set XML3=socket.xml

set ID1=
set ID2=
set ID3=

call :get_id "%XML1%" ID1
call :get_id "%XML2%" ID2
call :get_id "%XML3%" ID3

:main_menu
cls
echo ========================================
echo        WinSW Service Menu
echo ========================================
echo.
echo  1. Install service
echo  2. Uninstall service
echo  3. Start service
echo  4. Stop service
echo  9. Exit
echo.
set MENU=
set /p MENU="Select menu number: "
set TMPMENU=%MENU: =%
if "%TMPMENU%"=="" (
    echo Input is required. Please enter a menu number.
    pause
    goto main_menu
) else if "%MENU%"=="1" (
    set ACTION=install
) else if "%MENU%"=="2" (
    set ACTION=uninstall
) else if "%MENU%"=="3" (
    set ACTION=start
) else if "%MENU%"=="4" (
    set ACTION=stop
) else if "%MENU%"=="9" (
    exit /b
) else (
    echo Invalid input. Please select again.
    pause
    goto main_menu
)

:service_menu
cls
echo ========================================
echo        Select service to manage (!ACTION!)
echo ========================================
echo.
echo  0. All services (%XML1%, %XML2%, %XML3%)
echo  1. %XML1%  (!ID1!)
echo  2. %XML2%  (!ID2!)
echo  3. %XML3%  (!ID3!)
echo  9. Back to main menu
echo.
set SERVICE_NO=
set /p SERVICE_NO="Select service number: "
set TMPSERVICE=%SERVICE_NO: =%
if "%TMPSERVICE%"=="" (
    echo Input is required. Please enter a service number.
    pause
    goto service_menu
) else if "%SERVICE_NO%"=="0" (
    set ALLSERVICES=1
) else if "%SERVICE_NO%"=="1" (
    set ALLSERVICES=0
    set XML=%XML1%
    set SID=!ID1!
) else if "%SERVICE_NO%"=="2" (
    set ALLSERVICES=0
    set XML=%XML2%
    set SID=!ID2!
) else if "%SERVICE_NO%"=="3" (
    set ALLSERVICES=0
    set XML=%XML3%
    set SID=!ID3!
) else if "%SERVICE_NO%"=="9" (
    goto main_menu
) else (
    echo Invalid input. Please select again.
    pause
    goto service_menu
)

echo.

REM ===================== 전체 서비스 실행 =====================
if "%ALLSERVICES%"=="1" (
    for %%I in (1 2 3) do (
        set "XML=!XML%%I!"
        set "SID=!ID%%I!"
        if not defined XML (
            REM 비어있으면 스킵
            continue
        )
        call :do_action "!XML!" "!SID!" "!ACTION!"
        echo.
        echo ----------------------------------------
        echo.
    )
    pause
    goto main_menu
) else (
    call :do_action "!XML!" "!SID!" "!ACTION!"
    echo.
    pause
    goto main_menu
)

REM ===================== 액션 수행 함수 ======================
:do_action
REM Args: %1=xml, %2=sid, %3=action
set "XML=%~1"
set "SID=%~2"
set "ACTION=%~3"

if "%ACTION%"=="install" (
    echo [INFO]  Installing %XML% ...
    winsw.exe install "%XML%"
    if errorlevel 1 (
        echo [ERROR]  Install failed for %XML%
    ) else (
        echo [OK]     Installed %XML%
    )
) else if "%ACTION%"=="uninstall" (
    echo [INFO]  Uninstalling %SID% ...
    winsw.exe uninstall "%XML%"
    if errorlevel 1 (
        echo [ERROR]  Uninstall failed for %SID%
    ) else (
        echo [OK]     Uninstalled %SID%
    )
) else if "%ACTION%"=="start" (
    echo [INFO]  Starting %SID% ...
    sc start "%SID%"
    if errorlevel 1 (
        echo [ERROR]  Start failed for %SID%
    ) else (
        echo [OK]     Started %SID%
    )
) else if "%ACTION%"=="stop" (
    echo [INFO]  Stopping %SID% ...
    sc stop "%SID%"
    if errorlevel 1 (
        echo [ERROR]  Stop failed for %SID%
    ) else (
        echo [OK]     Stopped %SID%
    )
)
goto :eof

REM =================== get_id 함수 (동일) ===================
:get_id
REM Args: %1=xml filename, %2=variable to store service ID
setlocal
set "result="
for /f "usebackq delims=" %%I in (`findstr /r /c:"<id>.*</id>" %~1`) do (
    set "line=%%I"
    set "line=!line:<id>=!"
    set "line=!line:</id>=!"
    for /f "tokens=* delims=	 " %%A in ("!line!") do set "line=%%A"
    set "result=!line!"
)
endlocal & set "%2=%result%"
goto :eof

```

