# Vue 프로젝트 생성 방법

---

1. node js 다운
   - 먼저 node js를 다운받는다. 
   - npm을 이용해서 CLI 를 설치하기 위해서이다. 
2. cmd 에서 npm을 이용해 vue의 CLI를 설치한다. 
   1. `npm install -g @vue/cli` 명령어 입력하면 됨 
3.  extension툴
   1. vsCode에서 vue 사용시 사용하면 좋은툴들을 받아준다. 
      1. vetur : 코드 하이라이팅
      2. ESLint : javascript 사용시 실시간 에러검출
      3. Easy icon theme : icon들이 잘 구분될 수 있도록 함 
      4. vue vscode snippets : 자동완성 기능 제공 
4. vetur 사용시 package.json 에러남 
   1. jsconfig.json 폴더 생성으로 해결 
5. vue 프로젝트 생성 
   1. CLI를 설치 했다면 `vue create 프로젝트명` 으로 생성가능 
6. vue 서버구동
   1. 프로젝트 root에서 cmd 나 터미널 이용
   2. `npm run server` 입력 