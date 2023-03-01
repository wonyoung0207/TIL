# Window에서 퀵쉐어 사용하는 방법

---

>[유튜버 테크몽 개시글 참조](https://cafe.naver.com/techmong/16623)
>
>유튜버 테크몽이 공유한 지식을 이용해 삼성노트북이 아니더라도 퀵쉐어와 삼성노트를 사용할 수 있도록 만든다. 

## 퀵쉐어

### 정의

- 삼성 스마트 기기간 파일을 공유할 수 있는 기능
- 와이파이 / 블루투스 / 프로토콜을 이용한 방식으로 구동된다. 

### 문제점

- 삼성 노트북이 아닌 일반 윈도우 노트북에서 "Microsoft Store" 앱에서 퀵쉐어를 검색하면, 퀵쉐어 어플이 나오긴 하지만, 다운로드를 받을 수 없다.
  - 따라서 삼성 제공 퀵쉐어 파일을 직접 다운받아 설치해줘야한다. 

---

## Window 노트북에서 퀵쉐어 이용

### 1. 퀵쉐어 어플 다운

1. [Online link generator for Microsoft Store](https://store.rg-adguard.net/) 사이트에 들어가서, 아래의 노란 칸에, 아래 링크를 복사 붙여넣기 한다.

   - ```
     https://www.microsoft.com/en-us/p/quick-share/9pctgdfxvzlj?activetab=pivot:overviewtab
     ```

2. 그러면 많은 설치 파일이 뜨게 되는데, 검색 결과 중, `[SAMSUNGELECTRONICSCoLtd.SamsungQuickShare] `이름으로 된 앱 패키지 중 확장자가` [.msixbundle] `로 끝나는 **가장 최신 버전**(2023년 버전 있음)을 찾는다. `(※ 주의: .emsixbundle이 아니라 .msixbundle 를 다운받는다.)`

3. 해당 파일을 오른쪽 버튼을 눌러 [다른 이름으로 링크 저장] 해서 강제로 다운받는다. 

4. 그리고 파일을 실행시켜 퀵쉐어를 설치하고 열어보면 이 기기와 호환되지 않는다고 뜨게 되는데, 우선 창을 닫아 준다.

### 2. 사용중인 PC를 삼성 PC로 인식할 수 있도록 프로그램 다운로드 

1. 이 [github에 있는 파일](https://github.com/obrobrio2000/Samsung-Quick-Share-4-All/releases)로 이동해서 최신 버전의 `	SamsungQuickShare4All.bat`을 다운 받는다.
2. 해당 파일을 실행시키면 `C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp)` 의 파일 경로에 프로그램이 하나 설치된다. 
3. 해당 프로그램이 삼성PC로 인식될 수 있도록 도와준다.
   - 단점은 노트북 재부팅시 명령어 처리기가 뜰 수 있다. 왜냐하면 해당 파일이 윈도우 시작시 별도로 실행되기 때문이다. 
4. 파일이 정상적으로 설치됐다면 위에서 설치했던 퀵쉐어 어플을 다시 누른다. 
5. 재설치를 진행하면 정상적으로 동작하는 것을 확인할 수 있다. 

---

## Window 노트북에서 삼성노트 이용 

- 퀵쉐어와는 다르게 삼성노트 파일을 설치하면 바로 이용할 수 있다. 

### 삼성노트 어플 다운 받기

1. [Online link generator for Microsoft Store](https://store.rg-adguard.net/) 사이트에 들어가서, 아래의 노란 칸에, 아래 링크를 복사 붙여넣기 한다.

   - ```
     https://apps.microsoft.com/store/detail/samsung-notes/9NBLGGH43VHV
     ```

2. 그러면 많은 설치 파일이 뜨게 되는데, 검색 결과 중, `[SAMSUNGELECTRONICSCoLtd.SamsungNotes] `이름으로 된 앱 패키지 중 확장자가` [.msixbundle] `로 끝나는 가장 최신 버전(2023년 버전 있음)을 찾는다. `(※ 주의: .emsixbundle이 아니라 .msixbundle 를 다운받는다.)`

3. 해당 파일을 오른쪽 버튼을 눌러 [다른 이름으로 링크 저장] 해서 강제로 다운받는다. 

4. 퀵쉐어와 다르게 삼성노트는 바로 사용이 가능하다. 