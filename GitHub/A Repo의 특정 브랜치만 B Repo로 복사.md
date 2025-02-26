# A Repo의 특정 브랜치만 B Repo로 복사.md

---

>

## 사용이유

1. 회사에서 깃랩을 사용중이였는데, 프로젝트별로 깃 레포지토리를 나눠 관리하는게 아닌 브랜치로 나눠 관리하고 있었다.
2. 즉, 브랜치가 하나의 프로젝트 였던거....
3. 그래서 브랜치에 있는 프로젝트들을 레포지토리로 나눠보려고 한다. 

## 방법

1. 3가지 방법정도가 있는거같다. 
   1. A 레포 전체(브랜치 모두)를 B 레포로 그대로 이전 
      1. `git push --mirror origin` 이용 
      2. A 레포의 모든 브랜치, 태그, 히스토리를 **그대로 유지한 채 푸시**
   2. A 레포의 특정 폴더만 B 레포로 이전
   3. A 레포의 특정 브랜치만 B 레포로 이전 
2. 이중 나는 3번을 택했다. 

## 결론

1. 기존 레포지토리에서 특정 branch 만 Clone 받는다. 
2. Clone 받은 브랜치에서 remote origin 을 삭제한다. 
3. 새로운 레포지토리의 URL 로 remote 를 등록 후 Push 한다. 



## A 레포의 특정 브랜치만 B 레포로 옮기는 방법

##### 1. B 레포를 빈 저장소로 생성

- GitLab, GitHub 등에서 B 레포를 만든 후, 원격 저장소 URL을 확인
- (예: `https://gitlab.com/yourname/B-repo.git`)

##### 2. A 레포에서 특정 브랜치만 새 클론 생성

- `--single-branch`: 다른 브랜치는 제외하고 해당 브랜치만 클론

```bash
git clone --branch <브랜치명> --single-branch https://gitlab.com/yourname/A-repo.git
```

예를 들어, `feature-branch`만 옮기고 싶다면:

```bash
git clone --branch feature-branch --single-branch https://gitlab.com/yourname/A-repo.git
```

이렇게 하면 **해당 브랜치만** 포함된 디렉터리가 생성

##### 3. B 레포 새로운 원격 저장소 설정

```bash
git remote remove origin  # 기존 A 레포의 원격 저장소 제거
git remote add origin https://gitlab.com/yourname/B-repo.git  # B 레포 원격 저장소 추가
```

##### 4. B 레포의 기본 브랜치 이름 변경 (옵션)

- push 해야되기 때문에 B 레포의 브랜치명 통일해야함 

```bash
# 현재 브랜치 이름을 변경
# 기존 브랜치 feature-branch가 main으로 이름이 변경
git branch -m feature-branch main
```

##### 5. B 레포로 푸시

```bash
# B 레포에 브랜치 명이 존재하지 않아야한다. 만약 있다면 rebase 하라고함. 
git push -u origin main
```