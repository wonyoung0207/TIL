# npm 명령어 모음

---

>

## Node.js 버전 리스트 확인

```bash
nvm ls
```

1. nvm(Node Version Manager)를 사용하여 설치된 Node.js 버전 리스트 확인

## 현재 node 버전 확인 

```bash
node -v
node --version
```

1. 현재 설치된 Node.js 버전 확인

## node 버전 변경

```bash
nvm use [버전정보]
nvm use 18
```

## 프로젝트 필요 라이브러리 설치

```bash
# package.json 의 라이브러리들을 다운로드 
npm i [패키지명] # 특정 패키지만 설치 
npm install 


# 버전관리가 되지 않으면 install 시 에러가 발생한다. 
# 이때 강제로 설치할 수 있는 방법이다. 
npm install --save --legacy-peer-deps
```

