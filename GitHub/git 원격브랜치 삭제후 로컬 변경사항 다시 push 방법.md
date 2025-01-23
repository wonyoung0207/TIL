# git 원격브랜치 삭제후 로컬 변경사항 다시 push 방법

---

>

## 문제 발생

1. 로컬에서 브랜치로 개발 후 remote에 push 를 했다. 그때 다른분이 MR을 진행중이여서 rebase가 필요한 상황이 발생했다. 
2. rebase를 하기 위해 일단 remote에 push 된 브랜치를 삭제했다. 
3. 이제 로컬에서 rebase를 하고 다시 push를 해야하는데, 로컬은 벌써 push를 한 상황으로 인식을 한다. (왜냐하면 remote로 push 한 기록을 가지고 있기 때문에)

## 문제 해결

1. 그냥 remote 브랜치로 다시 push 하면된다. 

   1. 즉, remote에 없는 브랜치로 push 를 하게되면 새로이 브랜치가 생기면서 올라가기 때문이다. 

   ```bash
   # 1. remote 브랜치로 push 
   git push origin feature/abc
   
   # 2. remote 브랜치에서 삭제 
   git push origin --delete feature/abc
   
   # 3. remote와 최신화 
   git fetch
   
   # 4. rebase (기준 브랜치를 rebase)
   git pull --rebase origin feature/abc
   
   # 5. 다시 push
   git push origin feature/abc
   ```

   