## git stash 명령어

---

>[참고 사이트1](https://www.lainyzine.com/ko/article/git-stash-usage-saving-changes-without-commit/)

## git stash 

### 정의

- 다른 브렌치로 이동시 커밋하지 않은 내용(변경 내용) 을 임시 저장했다가, 나중에 다시 꺼내올 수 있다. 
  - 즉, 변경사항을 stash 라는 곳에 임시 저장하는 것이다. 
  - stash의 가장 중요한 기능은 커밋되지 않은 변경사항을 스택에 쌓아두고, 이걸 다시 꺼내오는 기능이다. 

### 동작방법

1. 동작 방식
   - stash는 기본적으로 스택처럼 동작한다. 
   - 즉, stash 영역에 변경사항을 쌓고 pop으로 꺼내오는 형식이다.
2. 동작 순서 
   1. git stash
      1. `git stash save`의 축약형
      2. 변경 사항을 임시로 저장하는 명령어 
   2. git stash pop 
      1. 임시 변경사항을 꺼내오는 명령어

### 관련 명령어

1. `git stash & git stash save`

   - 현재 디렉토리에서 변경된 내용으로 stash 생성

   - 사용하면 현재 working directory는 깨끗해진다. 

     ```git
     $ git stash 
     $ git stash save 
     ```

2. `git stash drop `

   - 가장 최근의 stash 삭제 

     ```java
     $ git stash drop
     Dropped refs/stash@{0} (716b4e839729443a935b7e15b6398c5cee152e49
     ```

3. `git stash list`

   - 현재 stash에 저장된 목록을 보여준다. 

     ```java
     $ git stash list
     stash@{0}: stash 저장2 // stash@{0} 식으로 스택에 저장됨 
     stash@{1}: stash 저장1
     ```

4. `git stash show stash@{0}`

   - 임시 변경사항(`stash@{0}`)의 내용을 확인할 수 있다. 

5. `git stash apply`

   - `git stash pop` 명령어와 비슷한 기능으로, stash를 꺼내오지만 목록에서 삭제하지 않는다. 
   - 즉, pop으로 하면 해당 stash를 꺼내온 후 drop으로 삭제하는것과 같지만, apply는 꺼내온 후 목록에서 stash를 삭제하지 않아 그대로 남아있는것을 볼 수 있다. 

6. `git stash -k`

   - 스테이지에 올라간 파일 (git add) 을 제외하고 stash에 저장한다. 
   - 즉, `git stash`는 `git add` 명령어를 실행해 스테이지로 옮긴 파일이건 아니건 모두 임시 저장하는것이 디폴트이다. 
     - 이때 `-k`(`--keep-index`) 옵션을 사용하면 스테이징 단계에 있는 파일은 제외하고 임시 저장할 수 있다. 

7. `git stash clear`

   - stash 스택의 모든 변경사항을 한번에 삭제한다. 

8. `git stash branch new-branch-name`

   - 새로운 브랜치를 만들고 stash된 내용을 `pop`한다.

9. 특정 파일의 변경 사항만 stash에 저장 

   ```
   git stash push <파일 이름>
   ```

---

## 발생 문제

### 현재 파일과 충돌

1. stash pop 했을 때, stash에 기록한 파일과 현재 디렉토리의 변경 파일이 충돌 
   - 즉, stash 저장했을 때의 시점과 현재 디렉토리 변경 시점이 달라 파일 충돌이 일어난다. 
   - 충돌나면 stash 의 기록이 pop 되지 않고, merge할건지 먼저 뭍는다. ( stash 기록 안사라짐 )
2. 해결방법
  1. `git reset --merge`
     - merge 가 일어난 파일에서 merge할 파일의 변경 내용을 지운다. 
     - 즉, 현재 디렉토리의 내용만 남기고 merge 충돌 나는 부분을 지운다. 
  2. `git checkout -f`
     - 작업 디렉토리를 강제로 이전 커밋의 상태로 되돌린다. 
     - 이 명령어는 변경된 내용을 무시하고 작업 디렉토리를 특정 커밋의 상태로 덮어쓴다. 따라서 이 명령어를 사용하면 모든 **변경 사항이 삭제되므로 사용하기 전에 주의**해야한다. 

### stash 명칭 에러 

1. `error: unknown switch e`

   - 아마도 stash 의 명칭을 `''` 로 묶어줘야 하는것 같다. 

2. 해결 방법

   ```CMD
   # stash 꺼낸 후 삭제 
   git stash pop 'stash@{1}'
   
   # stash 꺼내고 삭제 안함 -> 꺼낸 후 drop 으로 삭제해줘야함. 
   git stash apply 'stash@{1}'
   ```