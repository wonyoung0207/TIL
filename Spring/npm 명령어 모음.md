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

## 라이브러리의 의존성 확인 

```bash
npm info timingsrc dependencies
```

## 의존성 모듈 확인 

```bash
# 2 뎁스까지의 의존성 모듈 확인 
npm ls --depth=2
```





| 명령어                              | 설명                                                         |
| ----------------------------------- | ------------------------------------------------------------ |
| npm init                            | 새로운 프로젝트(패키지)를 시작할 때 사용하는 명령어로 package.json 파일을 생성합니다. |
| npm init -y                         | -y 옵션을 사용하여 기본값을 자동으로 설정할 수 있습니다.     |
| npm install <패키지명> (축약 i)     | 패키지(= 라이브러리, 모듈)를 설치하는 명령어 입니다. (로컬 설치) |
| npm install <패키지명@버전>         | 버전과 함께 사용하면 특정 버전을 설치할 수 있습니다.         |
| npm install --save 축약 -S          | --save 옵션을 사용하면 dependencies에 추가됩니다. (npm@5 버전 이후부터는 디폴트로 --save 옵션이 적용됨.) |
| npm install --save-dev 축약 -D      | 사용하면 devDependencies에 추가됩니다.                       |
| npm install <패키지명1> <패키지명2> | 여러개를 설치할 수 있습니다.                                 |
| npm install -g <패키지명>           | 전역 설치를 할 수 있습니다. (또는 --global)                  |
| npm install                         | package.json에 설정된 모든 패키지를 설치                     |
| npm install --production            | package.json에 설정된 모든 패키지를 설치 (devDependencies 제외) |
| npm uninstall <패키지명>            | 로컬 패키지 삭제                                             |
| npm uninstall -g <패키지명>         | 전역 패키지 삭제                                             |
| npm update <패키지명>               | 설치한 패키지를 업데이트 합니다.                             |
| npm root                            | 로컬 패키지 설치 디렉토리 확인                               |
| npm root -g                         | 전역 패키지 설치 디렉토리 확인                               |
| npm ls                              | 로컬 설치된 패키지 확인                                      |
| npm ls -g                           | 전역 설치된 패키지 확인                                      |
| npm start                           | package.json 파일의 script 속성의 start 실행                 |
| npm run <script-name>               | package.json 파일의 script 속성의 start외 스트립트 실행      |
