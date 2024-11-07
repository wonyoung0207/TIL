# WSL 비밀번호 초기화

---

>[참고 사이트1](https://velog.io/@rockwellvinca/Linux-%EC%9C%88%EB%8F%84%EC%9A%B0-1011-Ubuntuwsl-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EC%B4%88%EA%B8%B0%ED%99%94)

## 작성 이유 

1. WSL 을 사용하려고 접속하고 , `sudo` 명령어를 입력하니 `Password` 를 입력하라고 했다.  
2. 하지만 나는 처음(?) 사용하는것이다 보니 비밀번호를 알지 못했다. 그래서 WSL 의 비밀번호를 초기화에 대해 찾아보았다. 

## WSL ?

1. Windows에서 직접 Linux 배포판을 설치하고 실행할 수 있게 해주는 기능
2. Microsoft에서 개발했으며, 개발자들이 Windows 환경에서 Linux 명령어와 도구들을 쉽게 사용할 수 있도록 지원한다. 

## 방법

### 1. `Windows` ➡️ `명령 프롬프트` 실행	

### 2. root 계정으로 wsl에 접속

```
wsl -u root
```

### 3. passwd 명령어 입력

1. `passwd` 명령어를 이용하여 root 계정의 새로운 암호를 설정

   ```cmd
   passwd
   ```

### 3-1. 특정 사용자 passwd 변경 

1. root 가 아닌 특정 사용자 (나같은 경우 user 계정) 의 비밀번호를 변경해야했다. 

   ```cmd
   passwd <사용자 계정명>
   ```

   