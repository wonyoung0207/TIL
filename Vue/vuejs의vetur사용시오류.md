# vsCode의 확장프로그램 Vetur 사용시 나타나는 오류 

---

### 사용이유 

- vetur은 vuejs사용시 코드 하이라이팅 떄문에 사용한다. 
- 코드 하이라이팅 
  - 코드 눌렀을 떄 어디까지 한 묶음인지 보여줌 

### 문제 

- package.json에서 오류 발생
  - 이유는 vetur 때문이였다. 

### 해결방법

- [vetur 의 공식문서에 있는 해결방법](https://vuejs.github.io/vetur/guide/setup.html#project-setup)

- vue 프로젝트의 root 에 jsconfig.json 파일을 생성한다. 

- 해당 파일안에 밑의 내용을 넣는다. 
  ```json
  {
      "include": [
      "./src/**/*"
      ]
  }
  ```
  
  ```html
  < 파일구조  >
  └── src
      ├── components
      │   ├── a.vue
      │   └── b.vue
      ├── containers
      │   └── index.vue
      ├── index.js
      └── jsconfig.json -> root 에 파일 추가 
  </파일구조>
  ```
  
  

