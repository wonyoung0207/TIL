# git checkout 개념정리

---

>

## checkout

- 작업 디렉토리를 변경하거나 브랜치를 전환하고, 파일을 되돌리는 등 다양한 기능을 제공하는 git 명령어이다. 

### 1. 브랜치 전환하기:

```bash
git checkout <branch_name>
```

- 지정된 브랜치로 작업 디렉토리를 전환한다. 
- 현재 작업 중인 변경 사항은 자동으로 커밋되거나 스태시된다 =. 

### 2. 새로운 브랜치 생성하고 전환하기:

```bash
git checkout -b <new_branch_name>
git checkout -b new-branch-name origin/feature-branch


# 예시 원격 저장소의 staging1 브랜치를 로컬 브랜치 staging2 라는 이름으로 가져온 후 staging2로 브랜치 변경 
git checkout -b staging2 origin/staging1
```

- 새로운 브랜치를 생성하고 그 브랜치로 전환한다. 
- `-b` 옵션은 새로운 브랜치를 생성하고 전환하는 단축 명령어이다.

### 3. 특정 커밋으로 되돌리기:

```bash
git checkout <commit_hash>
```

- 지정된 커밋으로 작업 디렉토리를 되돌린다. 
- 특정 커밋의 상태를 확인하거나, 이전 버전의 파일을 복원하는 데 사용될 수 있다. 

### 4. 특정 파일을 특정 커밋 상태로 되돌리기:

```bash
git checkout <commit_hash> -- <file_path>
```

- 지정된 커밋에서 특정 파일의 상태를 작업 디렉토리로 복원한다. 
- 특정 파일의 변경 사항을 이전 버전으로 되돌리는 데 사용된다. 

### 5. 임시적으로 변경 사항을 보관하기:

```bash
git checkout -- <file_path>
```

- 작업 디렉토리에서 특정 파일의 변경 사항을 임시로 보관한다. 
- 변경 사항을 되돌리는 것이 아니라, 현재 작업 중인 상태에서 파일의 변경 사항을 지우고 이전 상태로 되돌린다. 



