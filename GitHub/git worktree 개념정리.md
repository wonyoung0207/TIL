# git worktree 개념정리

---

>

## worktree 사용이유 

1. 하나의 프로젝트 레포지토리에서 여러 브랜치를 동시에 작업하고 싶을 때
2. 브랜치 전환(`checkout`)하면 소스코드 전체가 바뀌어서 불편할 때
3. 기존 레포를 복제(clone)하면 디스크 공간이 두 배로 낭비될 때

##### 1. 기존 방법의 한계

- 다른 브랜치를 보려면 `git checkout <branch>` 해야 함 (→ 매번 이동해야 해서 번거로움)
- 레포를 하나 더 clone하면 → 용량, 설정, 속도가 다 불편해짐.

##### 2. 정리

- 하나의 Git 레포지토리로 여러 브랜치를 동시에 다른 폴더에 체크아웃할 수 있다.
- 레포를 여러 개 clone하지 않아도 됨. (디스크 공간 절약!)
- 각 폴더마다 독립된 작업 가능 (VSCode에서 폴더별로 따로 열 수 있음!)

## `git worktree` 기본 구조

- 하나의 Git 저장소(메인 레포)가 있고 추가 worktree를 연결해서 **"별도 작업 디렉터리"**처럼 쓸 수 있다.
- 모든 worktree들은 **하나의 `.git` 데이터베이스**를 공유함 (속도 빠름 + 공간 절약)

```bash
# my-project/ → main 브랜치로된 폴더 
my-project/
├── .git/ # 해당 git 을 공유함 
├── frontend/
├── backend/
├── README.md

# my-project-feature/ → feature 브랜치로된 폴더 
my-project-feature/
├── frontend/
├── backend/
├── README.md
```

##### 주의할점!!

1. **같은 브랜치는 여러 워크트리에 동시에 체크아웃할 수 없다.**
   1. **브랜치 하나 = worktree 하나** 규칙을 지켜야함 
   2. 즉, 로컬에 해당 브랜치가 checkout 되어있으면 이미 다른 워크트리(또는 기본 레포)에서 사용중인 것으로 판단
   3. **새 워크트리에 다시 checkout 할 수 없다.**
2. 여러개 사용하려면 `git checkout -b` 를 사용해서 로컬 브랜치로 이름 만든 후 worktree에 등록 

## 사용방법

##### 1. 현재 프로젝트 폴더 기준

```bash
cd my-project  # 현재 레포지토리 디렉터리로 이동
```

##### 2. worktree 추가

```bash
git worktree add ../my-project-feature feature-branch
```

- `../my-project-feature`: 새 폴더 (현재 폴더 바깥에 생성됨)
- `feature-branch`: 체크아웃할 브랜치 이름
  - 기존에 있는 브랜치 이름이면 그 브랜치로
  - 없으면 새로 만들어짐!

👉 결과: `../my-project-feature/` 폴더에 `feature-branch` 브랜치가 체크아웃된 버전이 생김.

##### 3. 각각 폴더 열어서 작업

- `my-project/` → `main` 브랜치 작업
- `my-project-feature/` → `feature-branch` 작업

**완전 독립적으로 소스 수정 가능!**

##### 4. worktree 목록 확인

```bash
git worktree list
```

```bash
# 출력 예시:
/path/to/my-project         (HEAD -> main)
/path/to/my-project-feature  (feature-branch)
```

##### 5. worktree 삭제 (정리할 때)

```bash
git worktree remove ../my-project-feature
```

- 폴더는 삭제되지만, Git 브랜치(`feature-branch`) 자체는 삭제되지 않음.
- 필요하면 나중에 다시 worktree 만들 수 있음.

