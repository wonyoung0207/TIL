# EOL, LF, CRLF 정리

---

>

## 학습 이유

- git repo를 처음 clone 받았는데 Changes 에 너무 많은 파일이 잡혔다. 
- 파일을 변경하지도 않았고 파일 change  에도 별다른 수정점이 보이지 않아 학습하게 되었다. 

## 결론 정리 

- 확인결과 Git Repo 에 올릴때는 Windows 환경에서 개발해 올렸고, 다른 pc에서 clone 을 받은 환경은 WSL 우분투 환경이였다. 
- 즉, Winodws의 저장방식과 WSL 의 **저장방식 중 줄바꿈** 부분이 다르게 저장된다는 것을 알게되었다. 
  - Windows : CRLF 방식 
  - WSL : LF 방식

## EOL이란?

- **EOL (End Of Line)**: 줄의 끝을 나타내는 특수 문자
- 사람 눈에는 "Enter"로 줄바꿈처럼 보이지만, 실제로는 줄바꿈도 문자 코드로 저장됨

## LF vs CRLF

| 구분 | 이름                        | 실제 저장되는 문자     | OS 기본           |
| ---- | --------------------------- | ---------------------- | ----------------- |
| LF   | Line Feed                   | `\n` (ASCII 10)        | macOS, Linux, WSL |
| CRLF | Carriage Return + Line Feed | `\r\n` (ASCII 13 + 10) | Windows           |
| CR   | Carriage Return             | `\r` (ASCII 13)        | (옛날 Mac)        |

```bash
# LF 방식 (Linux/WSL)
Hello\nWorld\n

# CRLF 방식 (Windows)
Hello\r\nWorld\r\n
```

- 둘 다 화면에는 `Hello \n World` 로 보이지만, 내부 저장 방식이 다르므로 Git에서는 "내용 변경"으로 인식함.

## ^M 이란 ?  

- `^M` = `\r` (Carriage Return, CR)을 터미널이나 Git diff에서 표시하는 기호

```
Hello^M
World^M
```

→ 내부적으로는 `Hello\r\n`, `World\r\n`

## 문제 발생 이유 

1. Windows에서 만든 파일은 CRLF로 저장
2. WSL(Linux 환경)에서는 LF를 기본으로 사용
3. Git의 **자동 변환(core.autocrlf)** 기능이 LF ↔ CRLF 변환
4. 결과적으로 내용은 같아도 EOL 차이로 인해 Git이 변경으로 감지

##### 문제 요약

- **LF**: Linux/WSL/macOS 줄바꿈 (`\n`)
- **CRLF**: Windows 줄바꿈 (`\r\n`)
- **^M**: CR 문자를 눈에 보이게 표시한 것
- Git에서 EOL이 다르면 내용이 달라진 것으로 간주 → 변경 감지

## 1. 해결 방법 (일판 파일)

1. 혼자 WSL에서만 작업: 
   - Git + VSCode 설정만 바꿔도 해결
2. Windows랑 WSL/리눅스 혼합 팀 환경: 
   - `.gitattributes`까지 추가해서 팀 전체 규칙 고정하는 게 안전

##### 1. Git 설정 (WSL 기준, LF 고정 추천) 

- WSL 환경에서 파일이 CRLF로 바뀌는 걸 막는다. 
- 해당 설정은 설정 이후부터의 파일들에 적용되므로 설정해도 기존 파일의 줄끝이 changes 된 파일들에 적용되지 않는다. 
  - 따라서 `.gitattributes` 에 설정해줘야 현재 Changes에 반영된다. 

```bash
git config core.autocrlf false
git config core.eol lf
git config core.filemode false
```

##### 2. .gitattributes 설정 

- .gitattributes로 팀 전체 규칙 고정 
- **줄끝 설정을 `.gitattributes` 에 추가하고 커밋 후 정규화 하면 적용**된다.

```bash
# .gitattributes에 내용 추가 
* text=auto eol=lf
*.bat eol=crlf

# .gitattributes 커밋(이미 있으면 생략)
git add .gitattributes
git commit -m "chore: enforce LF line endings via .gitattributes" || true

# 줄끝 정규화
git add --renormalize .
```

##### 3. VSCode 설정

- 상태바 `CRLF` 클릭 → `LF`로 변경
- 설정 파일에 추가:

```json
"files.eol": "\n"
```

## 2. 해결 방법 (png, glb 같은 대용량 파일)

- 위에서 했던것처럼 `.gitattributes` 에 설정 후 커밋했어도 LFS가 관리하는 대용량 파일들에는 수정사항이 적용되지 않아 여전히 Chages에 남아있었다. 
- 따라서 LFS파일에도 다음 설정들을 적용해줘야했다. 

##### 1. LFS 설치 확인 

```bash
which git
which git-lfs || echo "git-lfs not found"
```

##### 2. LFS 설치 

```bash
sudo apt-get update
sudo apt-get install -y git-lfs
git lfs version           # 버전 출력되면 OK
git lfs install           # 사용자별 활성화
```

##### 3. LFS 파일 워킹트리 복원

```bash
git reset --hard               # 인덱스/워킹트리 초기화 -> 난 여기까지 하니까 해결됨 !!
git lfs pull                   # 원격에서 LFS 객체 다운로드
git lfs checkout               # 포인터 → 실제 바이너리로 대체
git status
```

