# Chocolatey 개념정리

---

>

## Chocolatey 란? 

1. **Chocolatey**는 `Windows`에서 인기 있는 패키지 관리자이다.
2. `Homebrew`처럼 명령어를 통해 소프트웨어를 설치하고 관리할 수 있다. 
   1. `Homebrew` 는 `macOS or Linux` 에서 사용하는 패키지 관리자 이다. 

## Chocolatey 설치 방법

1. **PowerShell을 관리자 권한으로 실행** (시작 메뉴에서 "PowerShell" 검색 후 오른쪽 클릭하여 "관리자 권한으로 실행" 선택)

2. 아래 명령어를 입력하여 Chocolatey를 설치한다. 

   ```powershell
   // powershell
   Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.SecurityProtocolType]::Tls12; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
   ```

3. 설치가 완료되면 `choco` 명령어로 소프트웨어를 설치할 수 있다.

## Chocolatey 사용 예시

- Gradle 설치

  - `choco install gradle` 명령어를 사용하면 **전역적으로 Gradle을 설치**하게 된다. 
  - 이는 **시스템의 모든 프로젝트에서 사용할 수 있도록 Gradle을 설치**하는 방식이다. 

  ```powershell
  # powerShell 의 관리자 권한 실행에서 해야함 (관리자 아닌경우 lock 걸려서 설치 중 멈춤)
  choco install gradle
  ```

- Gradle 버전 확인

  ```powershell
  gradle -v
  ```

