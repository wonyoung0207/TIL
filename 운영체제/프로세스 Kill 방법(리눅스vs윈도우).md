



# 프로세스 Kill 방법(리눅스vs윈도우)

---

>

## 운영체제별 사용 명령어 

1. `kill`
   1. Unix 계열에서 사용 
2. `taskkill`
   1. Windows에서 사용

## 프로세스 종료 방식

1. `kill`
   1. 기본적으로 시그널을 보내 종료한다. 
2. `taskkill`
   1. PID 또는 프로세스 이름을 통해 종료한다. 

## 사용법의 차이

1. `kill`은 Unix/Linux 시스템에서 PID 기반으로 종료한다. 

   1. `/PID` : 사용하여 프로세스 ID를 통해 종료
   2. `/IM` : 사용하여 프로세스 이름을 통해 종료

   ```bash
   kill <PID>
   kill -9 [PID] # 강제 종료
   ```

2. `taskkill`은 Windows 시스템에서 PID 또는 프로세스 이름 기반으로 종료한다.

   ```cmd
   taskkill /PID <PID>
   taskkill /IM <프로세스이름>
   taskkill /F /PID <PID>
   ```

   