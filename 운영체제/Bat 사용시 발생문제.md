## **BAT 파일 사용 시 주요 문제 요약**

------

### 1. **공백 입력 처리 문제**

- `set /p`로 값을 입력받을 때 엔터 또는 공백만 입력하면
   → **이전 값이 그대로 남아**
   → 입력값 검증이 제대로 되지 않을 수 있음

- **해결:**

  - 입력 받기 전 반드시 변수 초기화(`set MENU=` 등)
  - 입력 후에는 공백 제거(`set TMPMENU=%MENU: =%`) 후 체크

  ```bat
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
  # 공백문자 처리 (MENU와 공백처리변수 다르게 처리하는게 일반적임)
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
  ```

------

### 2. **goto로 반복 진입 시 변수 값 유지 문제**

- `goto`로 같은 메뉴/루프를 반복하면
   **이전 입력 값(변수)이 남아있어**
   공백 등 예외 상황에서 오류/예상과 다른 동작이 발생

- **해결:**

  - 매번 입력 전 변수(`MENU`, `SERVICE_NO` 등)를 빈 값으로 초기화(`set MENU=`)
  - 각 입력 루프마다 변수 초기화 습관화

  ```bat
  # goto 반복문 시작시 변수 초기화 
  set MENU=
  ```

------

### 3. **관리자 권한 실행 시 경로 문제**

- UAC로 **관리자 권한 승격**(`Start-Process ... -Verb RunAs`)하면
   기본 작업 디렉토리가 **bat 파일이 위치한 폴더가 아닐 수 있음**
   → bat가 xml 등 파일을 못 찾는 문제 발생

- **해결:**

  - bat 파일 시작 부분에
     `cd /d "%~dp0"`
     추가하여
     항상 bat 파일이 위치한 폴더로 이동

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
  :: ----------- 이하 기존 로직 -----------
  ```

  