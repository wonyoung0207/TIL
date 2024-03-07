

# Git 발생 Error 모음

---



## git branch Error

---

>[참고 사이트1](https://m.blog.naver.com/PostView.naver?blogId=sqlpro&logNo=222555029055&categoryNo=18&proxyReferer=)
>
>[참고 사이트2](https://cjh5414.github.io/get-git-remote-branch/)

### git pull 시 Error : There is no tracking information for the current branch.

#### 원인

1. 다음 내용을 해석하면 현재 브랜치에 추적 정보가 없다.
   1. 즉, **추적**할 수 있도록 **업스트림** 브랜치와 연결해야한다. 
2. 나의 경우에는 원격 저장소의 branch 에는 'update' 라는 브랜치가 있지만, 로컬에는 없어 `git remote update` 명령어를 사용해 브랜치 목록을 업데이트가 필요했다. 
3. 이때 업데이트를 먼저 해주고 로컬의 사용 브랜치를 변경(git switch) 했어야 하는데, 잘못인지해서 `git branch update` 를 생성하고 'git remote update' 를 진행했다. 
4. 이렇게 하니 `git pull` 을 실행했을때, 현재 사용중인 'update' 브랜치가 원격 저장소에 있는 'update' 브랜치와 충돌하는 상황이 발생했다. 
   1. `git branch update` 로 로컬에서 브랜치를 만들었기 때문에 충돌이 발생했고, 원격 저장소는 해당 브랜치에 대한 관리가 이루어지지 않는 상태여서 pull을 할 수 없는 것이였다. 

### 해결방법1

1. 로컬에 같은 이름으로 생성한 'update' 브랜치 제거 

   ```bash
   // 로컬에서 브랜치 삭제하기 명령어 
   git branch -d localBranchName(로컬의 브랜치 이름)
   
   // 원격에서 브랜치 삭제하기 명령어
   git push origin --delete remoteBranchName(원격 브랜치 이름)
   ```

### 해결방법2

1.  로컬 브랜치와 병합할 브랜치를 지정

    1. upstream 이용 

       ```bash
       $ git branch --set-upstream-to=origin/<원격 브랜치명> <로컬 브랜치명>
       
       $ git branch --set-upstream-to=origin/feature/branch feature/branch
       ```

    2. 다만 연결만 되었을 뿐 병합이 이루어진 것은 아니므로 직접 **병합을 진행**해주어야 한다.

    3. **git pull 커맨드를 실행하면** 로컬 브랜치가 원격 브랜치와 병합되는 것을 볼 수 있다. 이후로는 정상적으로 사용하면 된다. 



---



# 초기 branch 없어서 push 불가능

### 문제점

- `git push origin master` 를 했을 때 다음 오류가 발생했다. 
  - origin과 remote 도 정상적으로 설정되어있는데 안됨 

```git
remote: A default branch (e.g. main) does not yet exist for temp/newemp_temp/2023_first_quarter/wony/ex2
remote: Ask a project Owner or Maintainer to create a default branch:
```

### 발생이유 

- owner, 또는 maintainer를 제외하고 새로운 repo를 생성하고 최초 push를 할 수 없다. (developer는 최초 push를 할 수 없음
- 따라서 권한이 없어 push 하지 못하고, 권한없이 push 하려면 기본 branch가 존재해야한다. 

### 해결

- 권한 있는 분이 branch 생성 후 다시 Clone 받음