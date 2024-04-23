# git 원격 저장소의 브랜치 가져오기

---

>[참고 사이트1](https://cjh5414.github.io/get-git-remote-branch/)

## 학습 계기

1. 원격 저장소와 로컬 저장소의 브랜치가 다른경우가 발생 
   1. 따라서 원격 저장소에는 있는 브랜치를 로컬로 가져와야하는 상황이 발생함 
2. git clone을 했을 때 원격 저장소의 모든 브랜치를 가져오진 않는다. 

### 모든 브랜치 가져오지 않는 이유

- Git의 설계상 이유이다. 
- Git은 원격 저장소의 모든 브랜치를 가져오는 것이 아닌, 사용자가 필요로 하는 브랜치를 명시적으로 가져오도록 설계되어 있다. 

1. **브랜치의 수가 많을 수 있음:** 
   1. 대규모 프로젝트의 경우 원격 저장소에 수십, 수백 또는 수천 개의 브랜치가 있을 수 있습니다. 
   2. 모든 브랜치를 클론할 경우, 많은 데이터가 전송되어 네트워크와 디스크 공간을 많이 사용할 수 있습니다.
2. **보안 및 개인 정보 보호:** 
   1. 일부 브랜치에는 보안 정보나 개인 데이터가 포함될 수 있으며, 모든 사용자가 액세스할 필요가 없을 수 있습니다.
3. **로컬 환경의 단순화:** 
   1. 모든 브랜치를 가져올 경우 로컬 저장소의 관리가 복잡해질 수 있습니다. 
   2. 사용자는 일반적으로 필요한 브랜치만 가져와서 작업하기를 선호할 수 있습니다.

### 원격 브랜치 가져오는 순서

1. `git fetch origin`
   1. 원격 저장소의 최신 변경 사항 가져오기 
2. `git checkout <브랜치 이름> `
   1. 특정 브랜치를 로컬로 가져오기 

## git checkout 이용방법 

1. 먼저 원격의 브랜치에 접근하기 위해 git remote를 갱신

   ```bash
   $ git remote update
   ```

2. branch 목록 확인 

   ```js
   // 모든 브랜치 목록 출력 
   $ git branch -a 
   
   // 원격 레포지토리의 브랜치 목록 출력 
   $ git branch -r
   
   // git branch -a 한 결과 
   * devekio
     list
     main
     multi
     remotes/origin/HEAD -> origin/main
     remotes/origin/devekio
     remotes/origin/main
     remotes/origin/multi
     remotes/origin/update
   ```

3. 원격 저장소의 branch 가져오기 

   1. `-t` : 가져올 branch 이름과 동일한 branch를 생성하면서 해당 branch로 checkout(변경) 한다. 
   2. 만약 branch이름을 변경하고 싶은경우 
      1. `$ git checkout -b [생성할 branch 이름] [원격 저장소의 branch 이름]`

   ```js
   $ git checkout -t origin/update
   ```

   



