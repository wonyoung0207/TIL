# git 사용자 ID pwd 저장

---

>

## 학습이유 

1. wsl 사용중에 작업에 대해 commit 할때마다 계속해서 ID, pwd 를 처야했다. 
2. 그래서 한번 입력으로 사용자 ID, PWD 를 저장해놓고 사용하는 방법을 찾아보게 되었다. 

## 사용방법

##### `git config --global credential.helper manager` 사용 

1.  **Git이 원격 저장소에 접근할 때 사용하는 사용자 인증 정보를**(사용자명/비밀번호 또는 토큰) **평문 파일에 저장**하도록 설정하는 옵션이다. 
2. **credential.helper manager**
   - Git 자격증명 헬퍼 중 하나
   - 최초로 `git push` 등 인증이 필요한 작업을 할 때 입력한 아이디·비밀번호(또는 토큰)를 **사용자 홈 디렉터리의 `.git-credentials`** 파일(평문)에 저장
   - OS의 안전한 자격 증명 저장소에 인증 정보를 암호화해 저장·관리하는 Git의 credential helper
3. 다음에 같은 원격에 접근할 때
   - Git은 `.git-credentials` 파일을 읽어서 자동으로 인증 정보를 재사용한다. 
   - 매번 비밀번호를 묻지 않고 **자동 인증**이 이루어지게 된다. 

```bash
# 1) credential.helper를 store로 설정 (운영체제 상관없이 작동)
git config --global credential.helper store

# 2) 첫 인증 시도: 이때 자격증명이 ~/.git-credentials 파일에 저장됨
git clone https://github.com/username/repo.git
# → 비밀번호(또는 토큰)를 물어보면 입력

# 3) 이후 동일 호스트에 대해서는 자동 인증
git pull  # 더 이상 자격증명 묻지 않음


# 설정 해제 
git config --global --unset credential.helper

# macOS 시스템의 보안 정책 적용 
git config --global credential.helper osxkeychain
```

| helper      | 암호 저장 방식         | 지원 OS | 특징                             |
| ----------- | ---------------------- | ------- | -------------------------------- |
| manager     | OS 보안 저장소(암호화) | 모든 OS | 안전, GCM Core는 최신 표준       |
| osxkeychain | Keychain(암호화)       | macOS   | macOS 표준, 안전                 |
| store       | 평문 파일              | 모든 OS | 보안 취약, 간단                  |
| cache       | 메모리(임시)           | 모든 OS | 일정 시간만 유지, 재부팅 시 삭제 |