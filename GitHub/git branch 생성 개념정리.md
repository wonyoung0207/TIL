# git branch 생성 개념정리

---

>

## git branch 란? 

1. 독립된 작업 흐름을 가능하게 해주는 기능으로, 프로젝트의 한 지점을 나타내는 포인터이다. 
2. 각 브랜치는 프로젝트의 특정 커밋을 가리키며, 이를 통해 여러 가지 기능이나 버그 수정을 독립적으로 작업하고, 나중에 병합할 수 있다. 

## checkout 된 브랜치 ? 

1. checkout 이란 git 의 명령어로도 쓰이고,  현재 선택된 브랜치를 뜻하는 말로도 사용된다. 
2. 즉, 현재 브랜치를 `체크아웃된 브랜치` 라고 하기도 한다. 

## branch 생성

1. 브랜치 생성 방법에는 크게 2가지가 있다 

   1. `git branch branch명`
   2. `git checkout branch명`

2. **`checkout` 명령어 이용**

   1. `branch` 명령어와 다른점은, 브랜치 생성 후 checkout 되어 생성한 branch로 이동한다는 것이다. 

   2. 현재 브랜치 기준으로 브랜치 생성 

      ```bash
      git checkout -b branch명
      ```

   3. 특정 브랜치를 기준으로 브랜치 생성 

      ```bash
      git checkout -b 생성branch명 기준branch명
      ```

3. **`branch` 명령어 이용**

   1. 현재 브랜치 기준으로 브랜치 생성 

      ```bash
      git branch branch명
      ```

   2. 특정 브랜치를 기준으로 브랜치 생성 

      ```bash
      git branch 생성branch명 기준branch명
      ```

## 이외 branch 명령어

1. 브랜치 이동(변경)

   ```bash
   git checkout -b branch명
   git switch branch명
   ```

2. 브랜치 병합

   ```bash
   git merge branch명
   ```

3. 브랜치 삭제 

   ```bash
   # 로컬 브랜치 삭제 
   git branch -d branch명
   
   # 원격 브랜치 삭제 
   git push origin --delete branch명
   ```

4. 원격 브랜치 명

   ```bash
   git branch -r
   ```

5. 원격 브랜치 가져오기 

   ```bash
   git fetch origin '원격의 가져올 branch명':'로컬로 가져올때의 branch명'
   ```

   