## git  이 관리하는 파일이나 폴더 이름 변경 방법

---

>[참고 사이트1](https://jonyo.tistory.com/162)
>
>[참고 사이트2](https://velog.io/@yhe228/%ED%8F%B4%EB%8D%94%EB%AA%85-%EB%B3%80%EA%B2%BD%EC%8B%9C-git%EC%97%90-%EB%B0%98%EC%98%81%EC%9D%B4-%EC%95%88%EB%90%98%EB%8A%94-%EC%9D%B4%EC%8A%88)
>
>[참고 사이트3](https://somjang.tistory.com/entry/Git-%ED%8C%8C%EC%9D%BC%EC%9D%98-%EC%9D%B4%EB%A6%84%EC%9D%84-%EB%B0%94%EA%BE%B8%EB%8A%94-%EB%B0%A9%EB%B2%95)

### 사용 이유

- git이 관리하는 파일/폴더 명은 윈도우에서 변경시 git이 인식하지 못해 변경이 어렵다. 
  - 즉, Git은 OS단에서 `이름바꾸기` 를 변경사항으로 인식하지 않는다. 
- 따라서 파일/폴더 명을 변경할때는 `git mv` 라는 명령어를 사용해 변경해줄 수 있다. 

### git mv

- mv는 파일 이동 명령어이다. 
- mv 를 사용하면 rename 와 동일한 효과를 얻을 수 있으어 이름 변경 사항을 추적할 수 있다는 장점이 있다. 

### 사용방법

1. 변경하고 싶은 파일의 경로를 확인한다. 

   - 예를들어, 변경하고싶은 파일에서 git bash 를켠다. 

   - 만약 mv 에 파일 경로를 입력하고 싶다면 다음과 같이 하면된다. 

     ```bash
     # 폴더 구조 : /TIL/Git/TestFolder/TestFile.java 
     # TIL이 git으로 관리하고 있는 가장 상위 폴더로 생각하면 됨 ( 즉, .git 파일이 있는 폴더 )
     # 폴더는 폴더명만 명시하면 되고, 파일은 확장자명까지 해주는게 좋다. 
     ```

2. git mv [파일/폴더 이름] [변경 파일/폴더이름]

   - 변경할 파일/폴더 이름과 위치를 통해 파일/폴더를 이동시킨다. 

     ```bash
     # 시작 경로는 TIL로 통일한다. 
     # 파일 경로와 파일 이름을 확인 
     
     # 예시 
     git mv oldName newName
     
     # 1. 파일이름을 변경 
     git mv Git/TestFolder/TestFile.java Git/TestFolder/testfile.java # 만약 Git 바로 밑으로 옮기고 싶다면 Git/testfile.java 로 사용하면 된다. 
     
     # 2. 폴더이름을 변경 
     git mv Git/TestFolder Git/testfolder # 
     ```

### 발생에러

- mv 명령어는 대소문자를 구분하지 않기 때문에 `git mv Login login` 과 같은 방식으로 사용하면 Error가 발생한다 .

  ```bash
  # error 발생
  fatal: not under version control, ...
  ```

- 해결방법

  - 임시파일명으로 이름을 변경했다가 원하는 파일명으로 변경 (2번의 mv를 사용)

    ```bash
    # 1. temp라는 폴더로 이름 변경
    git mv Login temp
    
    # 2. temp 폴더를 login으로 다시 변경 
    git mv temp login
    ```

    