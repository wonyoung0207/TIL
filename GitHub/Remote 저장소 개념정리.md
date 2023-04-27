# Remote 저장소 개념정리 

---

>[참고 사이트1](https://git-scm.com/book/ko/v2/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-%EB%A6%AC%EB%AA%A8%ED%8A%B8-%EC%A0%80%EC%9E%A5%EC%86%8C)

## Remote 저장소

### 정의

- 리모트 저장소는 인터넷이나 네트워크 어딘가에 있는 저장소를 말한다.
  - 저장소는 여러 개가 있을 수 있는데 어떤 저장소는 읽고 쓰기 모두 할 수 있고 어떤 저장소는 읽기만 가능할 수 있다.
- 따라서 여러 사람과 함께 일할 때 이 Remote 저장소를 바꿔가며 push and pull 로 데이터를 관리할 수 있다. 

### clone 사용시 remote 자동설정

- `git clone` 명령을 실행했을 때 묵시적으로 `origin` 해당 URL(clone 한 곳의 URL) 리모트 저장소를 추가한다. 
  - 따라서 `git push origin master` 의 문구에서 origin이 하는 역할은 **clone한 주소를 대체하는 변수** 정도로 생각하면 된다. 

### 명령어

1. 설정한 모든 remote 보기

   ```
   $ git remote -v
   ```

2. remote 추가 

   ```
   // git remote add origin [추가할 원격 git 저장소 주소]
   // remote저장소 주소대신 origin 이라는 변수로 대체하겠다는 의미. 
   $ git remote add origin https://github.com/wonyoung0207/TIL.git => .git으로 끝남 
   ```

3. remote 삭제 

   ```
   $ git remote remove
   $ git remote rm 
   ```

4. remote 이름 변경

   ``` 
   $ git remote rename pb paul
   ```

5. remote 저장소에 push 

   ```
   // git push <리모트 저장소 이름> <브랜치 이름> 
   $ git push origin master
   ```

