# git config 설정방법

---

>

## git 레포지토리 초기화 

1. 만약 현재 clone 하고자 하는 디렉터리가 Git 레포지토리가 아니라면 초기화 후 clone 을 해야 한다. 

   ```bash
   git init
   ```

## 사용자 정보 설정

```bash
git config --global user.name "<GitLab 사용자 이름>"
git config --global user.email "<GitLab 등록 이메일>"

# 예시
git config --global user.name "my-gitlab-username"
git config --global user.email "myemail@example.com"
```

## 계정정보 인증 

##### 조건 

1. Github 이나 GitLab 에서 Repository 를 clone 해오려면 권한을 가져야 한다. 
2. 즉, 해당 Repo 에 대한 접근 권한과 로컬에서 Clone 할때 Git 에서 해당 Repo에 접근할 수 있는 계정정보(UserName, Password) 를 입력해야한다. 

##### 종류 

1. HTTPS 프로토콜 사용 
   1. **인증 정보를 저장하지 않으면 매번 GitLab에 접근할 때 사용자 이름과 비밀번호를 요구**
   2. 즉, Clone 할때마다 Username과 Password를 입력해야하는 번거로움이 발생한다. 
2. SSH 사용 설정 
   1. Access Token을 만들어 계정에 등록해놓는 방법 
   2. 클라이언트와 서버 간 인증을 위한 `암호화 키`이다. 

## 계정정보 단순화 

##### HTTPS 프로토콜 사용 

1. 방법은 비밀번호를 **일반 텍스트 형태로 저장**하기 때문에 다른 방법(예: SSH)을 사용하는것이 좋다. 

```bash
git config --global credential.helper store
```

##### Personal Access Token(PAT) 저장

1. 비밀번호 대신 **Personal Access Token**(PAT)을 사용하도록 권장한다. 
2. HTTPS 대신 **SSH**를 사용하면, 한 번 설정으로 비밀번호 없이 GitLab에 연결할 수 있다. 

## SSH 설정 방법 

##### 1. SSH 키 생성

1. `-t rsa`: RSA 알고리즘 사용.

2. `-b 4096`: 키 길이를 4096비트로 설정(보안 강화를 위해 권장).

3. `-C "your_email@example.com"`: 키를 식별하기 위한 주석 추가(옵션).

   ```bash
   ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
   ```

4. 키 위치 

   ```bash
   ~/.ssh/id_rsa        # 개인 키
   ~/.ssh/id_rsa.pub    # 공개 키
   ```

5. 키 확인

   ```bash
   ls -l ~/.ssh
   ```

##### 2. SSH 에이전트 실생

1. SSH 에이전트를 실행하는 이유는 **SSH 키를 관리하고, 암호 없이 Git 작업이나 원격 서버 접속을 편리하게 수행하기 위해서**이다. 

2. SSH 에이전트를 시작 및 추가 

   1. SSH 키를 추가하면, 세션 동안 비밀번호를 다시 입력할 필요가 없다. 

   ```bash
   # 시작
   eval "$(ssh-agent -s)"
   
   # 추가 
   ssh-add ~/.ssh/id_rsa
   ```

##### 3. GitLab에 SSH 공개 키 추가

1. 공개 키 확인 및 출력

   ```bash
   cat ~/.ssh/id_rsa.pub
   ```

2. 공개 키 등록

   1. GitLab 웹사이트로 이동
   2. **우측 상단 프로필 아이콘 > Settings > SSH Keys**로 이동
   3. **Key** 입력란에 복사한 공개 키를 붙여넣는다. 
   4. **Title**에 키를 구분하기 위한 이름을 입력합니다(예: "WSL SSH Key").
   5. **Add key**를 클릭

##### 4. SSH 연결 확인

1. GitLab과 SSH 연결 테스트

   ```bash
   ssh -T git@gitlab.com
   # 결과 : Welcome to GitLab, @your_username!
   ```

##### 5. GitLab에서 SSH URL로 Clone

1. Clone 할때 HTTPS URL 방식이 아닌 SSH URL 이용해야한다. 

   ```bash
   git clone git@gitlab.com:username/repository.git
   ```

## SSH 설정 후에도 HTTPS Clone 가능 여부

1. SSH를 설정했다고 해서 HTTPS 방식이 비활성화되지는 않는다. 

2. 대신, Clone 시 HTTPS 주소를 사용하면 여전히 Git은 사용자 이름과 비밀번호 또는 Personal Access Token(PAT)을 요청한다. 

   ```bash
   # HTTPS 사용방식
   git clone https://gitlab.com/username/repository.git
   
   # SSH 사용방식 
   git clone git@gitlab.com:username/repository.git
   ```

3. 조심할점

   - 이전에 HTTPS 방식으로 Clone 한 Repo는 변경하지 않는한 계속해서 HTTPS로 작업한다. 

   - Clone한 저장소가 HTTPS를 사용하고 있는데 SSH로 전환하는 방법

     ```bash
     # HTTPS -> SSH
     git remote set-url origin git@gitlab.com:username/repository.git
     
     # SSH -> HTTPS
     git remote set-url origin https://gitlab.com/username/repository.git
     ```

     
