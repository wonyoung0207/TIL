# git cherry-pick 개념정리

---

> [참고 사이트1](https://stackoverflow.com/questions/10345182/how-to-log-first-10-commits-in-git)
>
> [참고 사이트2](https://programmer-hoo.tistory.com/97)
>
> [참고 사이트3](https://velog.io/@dldmswjd322/Git-Commit-%EB%8B%A4%EB%A5%B8-%EB%B8%8C%EB%9E%9C%EC%B9%98%EB%A1%9C-%EC%98%AE%EA%B8%B0%EA%B8%B0)

## 문제상황

1. 기능 추가할때 feature/... 으로 브랜치를 만들고 개발을 했어야 했는데, 까먹어서 `staging` 브랜치에 개발을 해버렸다. 
2. 개발 후 commit을 완료한 상태이고, push 는 하지 않았다.(다행히 push 는 권한으로 막혀있었음...)
3. 따라서 Commit 기록을 feature/... 브랜치로 옮기고 싶어 찾아보다가 `cherry-pick` 을 알게 되었다. 

## Cherry-pick  

##### 개념

1. 특정 커밋을 다른 브랜치에 적용할 때 사용한다. 
2. 즉, 특정 커밋의 해시만 알고 있다면 다른 브랜치로 해당 커밋을 옮겨올 수 있다. 
   1. 하지만 기존 커밋을 복사해온것이기 때문에 기존 브랜치에는 commit 이 남아있게된다. 

##### 유례

1. cherry-pick 명령어는 이름 그대로 “체리 고르기”에서 유래되었다고 합니다.
2. 체리를 하나하나 골라 담는 것처럼, Git에서도 **특정 커밋을 선택적으로 다른 브랜치에 적용할 때 사용** 합니다. 
3. cherry-pick을 통해 필요한 커밋만을 “고르고” 원하는 브랜치에 반영할 수 있다. 

##### 사용방법

```bash
# commit-hash : 적용하고자 하는 커밋의 해시
git cherry-pick <commit-hash>
```

##### 사용예시

1. 커밋 해시값 확인 

   1. commit 해시를 확인하기 위해 `git log` 를 사용한다. 

   2. 이때 git log 는 현재 commit 되어있는 브랜치에서 진행해야 한다. (브랜치 별로 commit log 가 있기 때문)

      ```bash
      git log -n 5 # 위에 5개만 봄
      
      # commit 해시 
      commit 54da1dsadfkjjsdjflsdkjf
      Author: 원영 안 <sdf@dfd.co.kr>
      Date:   Thu Dec 12 08:56:53 2024 +0900
      ```

2. 브랜치 이동

   1. commit을 복사할 브랜치로 이동

   2. 즉, feature/... 브랜치를 생성 후 해당 브랜치로 이동한다. 

      ```bash
      git checkout feature/...
      git switch feature/...
      ```

3. **커밋 cherry-pick**

   1. commit 기록을 이동한 브랜치로 복사해온다. 

      ```bash
      git cherry-pick 54da1dsadfkjjsdjflsdkjf
      ```

4. 기존 커밋 삭제 

   1. `cherry-pick` 은 기존 commit 기록을 복사하는것이기 때문에 기존 commit 기록이 남아있게 된다. 

      1. 즉, 잘못 commit 한 브랜치로 이동해 해당 커밋 기록을 삭제하면 된다. 

   2. 이때 reset 명령어를 사용하는데, 뒤에 적은 `commit-hash`  이후의 내용들을 모두 다 삭제한다. 

      1. 예를들어 a -> b -> c -> d -> e -> f 커밋이 존재할 때, c의 `commit-hash` 를 입력하면 c 이후의 d,e,f 기록이 삭제되고 현재 코드의 히스토리 위치가 c로 이동한다. 

      ```bash
      git reset --hard <commit-hash>
      ```

      

