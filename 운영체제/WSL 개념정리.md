# WSL 개념정리

---

>

## WSL 

#### 개념

1. Windows에서 직접 Linux 배포판을 설치하고 실행할 수 있게 해주는 기능
2. Microsoft에서 개발했으며, 개발자들이 Windows 환경에서 Linux 명령어와 도구들을 쉽게 사용할 수 있도록 지원한다. 

#### 설치 방법

1. Windows 검색 창에 "Windows 기능 켜기/끄기"를 검색하여 열고, "Windows Subsystem for Linux"와 "Virtual Machine Platform"을 활성화한다. 

#### 특징

1. **리눅스 명령어 사용**
   1. `ls`, `cd`, `grep`, `top` 같은 기본적인 Linux 명령어를 그대로 사용할 수 있다. 
2. **리눅스 커널의 지원**
   1. WSL 2에서는 실제 Linux 커널을 가상화 기술로 Windows 위에 실행하므로 높은 호환성과 성능을 제공한다. 
3. **파일 시스템 통합**
   1. Windows 파일 시스템과 Linux 파일 시스템이 통합되어 있다. 
   2. 예를 들어, Linux에서 `/mnt/c/` 디렉터리를 통해 Windows 파일에 접근할 수 있고, Windows에서도 WSL에서 생성된 파일을 접근할 수 있다. 

#### 주의사항

1. cmd 에서 WSL 에서 돌아가고 있는 PID 서비스 검색시 나오지 않음 

   1. wsl 에서 실행중인 서비스의 PID를 찾기 위해선 wsl 로 들어가 검색해야한다. 
   2. 왜냐하면 `netstat -ano`는 Windows 네이티브 네트워크 연결만을 검사하므로 WSL(특히 WSL 2)에서 실행 중인 프로세스는 기본적으로 Windows의 `netstat`에서 감지되지 않는다. 

   ```
   ss -tunlp | grep "3521"
   ```

   