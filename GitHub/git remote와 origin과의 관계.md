# git remote 와 origin 과의 관계 

---

>[참고 사이트1](https://pers0n4.io/github-remote-repository-and-upstream/)

## 정리 

1. `origin`은 원격 저장소의 이름(alias)으로, 이는 `remote` 명령어를 사용하여 설정된 원격 저장소의 별칭 중 하나로 사용된다. 
2. **local에서 origin으로 push한다**
3. **origin에서 local로 pull한다**

## remote

- git repository 를 관리하는 **명령어**로, 원격 저장소를 추가하거나 제거하고, 원격 저장소의 정보를 관리하는 용도로 사용된다. 
- **remote = 원격** 
  - 즉, 원격 저장소를 뜻하기도 한다. 
  - **원격 저장소를 remote repository 라고 하며, 원격 저장소는 github, gitLab, Gitbucket 과 같은 코드 호스팅 플랫폼 서버에 저장된 저장소 공간을 뜻한다.** 
  - git = 분산 버전 관리 시스템 중 하나 
  - github = git을 바탕으로 하는 플랫폼 

## origin 

- 원격 저장소인 git repository 의 **별칭(alias)**을 뜻한다. 
- 새로운 Git 저장소를 클론하면 원격 저장소의 이름으로 `origin`이 자동으로 설정된다. 
  - 왜냐하면, GitHub에 있는 원격 저장소를 클론하면 해당 원격 저장소를 가리키는 `origin`이라는 별칭이 자동으로 생성되기 때문이다. 
- 즉 origin 에는 git repository의 주소인 'https://github.com/myRepositoryName' 의 값으로 대신 사용할 수 있다. 

### 예시 1

1. github 에서 repository를 생성하고 clone으로 로컬스토리지를 생성한다. 

2. 이때 `clone`을 하면 origin이 자동으로 등록되는것을 확인할 수 있다. 

   1. 로컬 스토리지에서 repository를 생성할때 사용되는 `init`했을 때는 `git remote add origin ...`으로 origin을 직접 등록하라고 안내한다. 

3. 여기서 알수 있듯이, origin 은 repository의 기본 이름을 뜻하며 origin 을 사용해 해당 원격저장소에 대한 관리가 가능하다. 

   1. origin 은 원격 저장소의 주소값을 가지고 있다고 생각하면 쉽다. 

4. 코드 예시 

   ```js
   // origin 이라는 이름을 추가하는데, origin 이 의미하는 값은 https://github.com/wonyoung0207/test1.git 이다. 
   git remote add origin 'https://github.com/wonyoung0207/test1.git'
   ```

### 예시2

1.  `git push -u origin main` 이라는 명령어를 입력할때
2.  `-u` 옵션이 `--set-upstream` 옵션의 줄임으로 upstream을 설정한다는 뜻이다. 
3.  upstream을 한 번 설정하고 나면 다음부터는 `git push` 또는 `git pull`이라고 명령어만 입력해도 자동으로 origin의 main 브랜치로부터 push와 pull을 진행한다. 
    1. 그 이유는 upstream 옵션을 통해 해당 브랜치에서 upstream과 downstream 의 관계가 설정됐기 때문이다. 
    2. upstream : 로컬 저장소 -> 원격 저장소 
    3. downstream : 원격 저장소 -> 로컬 저장소 