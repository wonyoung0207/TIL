# WSL 저장 디스크 변경 

---

>

## 문제 발생

1. windows 에서 제공하는 wsl 을 사용하고 있었는데, 디스크의 공간이 부족해 새로운 디스크로 wsl을 옮기고자 했다. 

## 기본 저장 경로 (WSL 설치 위치)

```cmd
C:\Users\metarnd03\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\
```

- 이 `LocalState` 폴더 안에 WSL의 전체 리눅스 파일 시스템이 **가상 디스크 파일(VHDX)** 형태로 들어 있다. 
- **ext4.vhdx** : Ubuntu의 전체 루트 파일시스템 (리눅스 환경 전체가 이 안에 있음)

## 디스크 변경 방법

1. wsl 확인

   ```bash
   wsl --list --verbose
   ```

2. wsl 을 이동하고 싶은 디스크의 경로에 export 한다. 

   1. 여기서 wsl 이 뜻하는것은 위의 경로`....CanonicalGroupLimited..` 밑에있는 `ext4.vhdx` 이다. 

   ``` bash
   # WSL 배포판(Ubuntu)을 .vhd 형식의 디스크 이미지로 백업(export) => tar로 안만들고 바로 파일 자체로 복사하는 방법 
   # --export Ubuntu : Ubuntu이름의 WSL 배포판을 export 
   # F:/wsl/image/ubuntu.vhdx : 저장경로로 export함
   wsl --shutdown
   wsl --export Ubuntu F:/wsl/image/ubuntu.vhdx --format vhd
   ```

3. 기존 wsl 배포판 해제 

   1. ⚠️ 주의!! 기존 Ubuntu를 WSL에서 삭제함
   2. 이걸로 C 드라이브에 있던 Ubuntu는 제거됨

   ```bash
   wsl --unregister Ubuntu
   ```

4.  F 드라이브로 import (이동)

   1.  WSL을 `F 드라이브`에 새롭게 등록한다. 

   ```bash
   # Ubuntu ==> 새로 등록할 배포판 이름 (기존 이름과 같게 지정하려면 unregister 하고 하는게 좋음)
   # F:\WSL\Ubuntu ==> 새로 등록될 WSL 배포판의 저장 위치
   # F:\wsl\image\ubuntu.vhdx ==> export 한 ubuntu.vhdx 파일의 위치 
   # --vhd : 포멧 명시 
   
   wsl --import Ubuntu F:\wsl\ubuntu F:\wsl\image\ubuntu.vhdx --vhd
   ```

5. wsl 동작 확인 

   ```bash
   # wsl 배포판 실행 
   
   wsl -d Ubuntu
   ```

6. 기본 유저 변경

   - 나같은 경우는 기본 유저 변경이 안되서 해당 링크에서 `etc/wsl.conf` 파일 생성 후 내용을 추가해줬다. 

   - [wsl 리눅스 기본 사용자 등록](https://www.lainyzine.com/ko/article/how-to-change-the-default-user-in-wsl-linux/)

   - 기본 사용자 등록 후 shutdown 하고 다시 시작해야 인식된다. 

     ```bash
     # [wsl실행]:/etc -> etc/wsl.conf 폴더의 내용 
     vi wsl.conf
     
     [user]
     # default=<USERNAME>
     default=user
     ```

   - 다른방법은 밑에와 같다. 

     ```bash
     # WSL에서 특정 사용자(root)로 로그인
     wsl -d Ubuntu -u root
     
     # 접속 후 사용자 확인
     cat /etc/passwd
     
     # 기본 사용자 설정
     ubuntu config --default-user <사용자이름>
     ```

     

   

