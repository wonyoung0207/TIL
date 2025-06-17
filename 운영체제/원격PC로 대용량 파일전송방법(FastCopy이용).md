# 원격PC로 대용량 파일 전송 방법

---

>[참고 사이트1](https://combaggwa.tistory.com/entry/%ED%8C%8C%EC%9D%BC-%EB%B3%B5%EC%82%AC-%EB%B9%A0%EB%A5%B4%EA%B2%8C-%ED%95%98%EA%B8%B0Fast-Copy)
>
>[fastcopy 설치](https://fastcopy.jp/)

## 1. 원격 PC에 일반 공유 폴더 만들기

1. **원격(수신) PC에서**
   1. 탐색기에서 `D:\testDir` 폴더를 우클릭 → **속성**
   2. **공유** 탭 → **고급 공유…** 클릭
   3. “이 폴더 공유” 체크 → 공유 이름에 `testDir` 입력
   4. **권한** 버튼 클릭 → `Everyone` 또는 필요한 사용자에게 **읽기/쓰기(Full Control)** 권한 부여 → 확인
      1. “특정 계정”은 **공유를 제공하는 PC(즉, `testDir` 폴더가 있는 원격 PC)** 에 정의된 사용자 계정이다. 
2. **방화벽 확인**
   - 제어판 → **Windows Defender 방화벽** → **앱 또는 기능 허용**
   - “파일 및 프린터 공유”에 체크되어 있는지 확인

------

## 2. 로컬 PC에서 네트워크 매핑

- FastCopy GUI 자체에는 자격증명 입력란이 없어서, **FastCopy를 실행하기 전에** 반드시 Windows 차원에서 해당 공유에 대한 인증을 먼저 성공시켜줘야 한다. 

```powershell
# 공유 폴더 이름 확인 
net share

# net use : 네트워크 드라이브 연결(매핑)·해제·상태 조회
# \\172.1.1.1\testDir : 접근하려는 공유 폴더의 UNC 경로 -> 공유폴더 지정하면 부여받는 명칭이 testDir 된다. 
# /user : “이 공유에 접속할 때 사용할 사용자 계정”을 지정
# REMOTE_PASS : 계정 암호
net use \\172.1.1.1\testDir REMOTE_PASS /user:REMOTE_USER 
net use \\172.1.1.1\전임자_인수인계 passwd /user:user

# 실패 세션 정리 
net use \\172.1.1.1\전임자_인수인계 /delete
```

- `REMOTE_USER` / `REMOTE_PASS`에 원격 PC 로그인 계정 입력

------

## 3. FastCopy GUI로 복사하기

1. FastCopy를 실행

2. **Source**: 전송할 로컬 파일/폴더 선택

3. **DestDir**:

   - 매핑했으면 `Z:\`

   - 매핑 안 했으면 `\\172.1.1.1\testDir\`

     ```bash
     "\\remote_IP\전임자_인수인계\"
     # 역슬래시(\): 경로 맨 끝에 반드시 한 개 이상의 \를 붙여서 “폴더”임을 명시
     ```

     

4. **Mode**: `Copy` (또는 `Move`)

5. **옵션**

   - **Buffer-size**: 네트워크가 좋으면 `1 MiB`~`4 MiB` 권장
   - **Verify**: 전송 후 검증
   - **Force Close**: 완료 후 창 닫기

6. **Execute** 클릭 → 속도 그래프 확인하면서 복사

------

## 4. 복사 완료 후 확인

- 원격 PC에서 `D:\testDir`에 파일이 정상 복사됐는지 크기·수정시간 확인
- (선택) FastCopy GUI의 속도 그래프나, CLI에 `/log="C:\fastcopy.log"` 옵션 추가 후 로그 확인