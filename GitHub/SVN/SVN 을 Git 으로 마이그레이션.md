#  SVN → Git 마이그레이션 핵심 정리

---

>

## 학습 개기

- SVN 프로젝트를 이용하는 프로젝트에서 **Production** 과 **Develop** 버전을 나눠 개발해야하는 상황이 발생했다. 
  - **Production** : 다른 팀에서 조금식 기능 개발 및 요구사항 수정 
  - **Develop** : 전체적인 CSS 프론트 구조변경 
- SVN 으로 개발하는게 불편해 Git 으로 마이그레이션해서 개발하고자 해당 개념을 학습하게 됐다. 

## 방법

##### 1. git-svn 설치

```bash
sudo apt install git-svn
```



##### 2. 위치이동 

- SVN 을 가져오기 위한 경로로 이동 
- 이때 햇갈리지 않아야 하는게, git init 된 파일( remote repository 의 경로에서 실행하면 안된다!!)
  - 왜냐하면 중복 `git init` 으로 인해 충돌될 가능성 있음 



##### 2. SVN 저장소 전체 히스토리 clone

```bash
git svn clone SVN주소

# 특정 브랜치만 가져오기 
git svn clone SVN주소/branch-name

# 모든 revision을 Git commit으로 변환
git svn clone SVN주소 --stdlayout
```

- 순서 
  1. 새로운 디렉토리 생성
  2. 내부에서 `git init` 실행 
  3. SVN History 가져오기 
  4. git-svn 메타데이터 생성

- 정리 

  - svn 기록을 원하는 경로의 git init 폴더로 가져옴 

  - SVN 히스토리 그대로 유지됨



##### 3. 기본 브랜치 정리

- SVN에서 가져온 브랜치 이름을 원하는 브랜치 명으로 변경

```bash
git branch -m svn-server-init
```

- 또는

```bash
git switch main
git reset --hard svn-server-init
```



##### 4. GitLab 원격 저장소 연결

```bash
git remote add origin GitLab주소
```



##### 5. GitLab으로 push

- 나같은 경우 main 브랜치에 바로 push 하지 못하도록 gitlab 설정이 되어있어 해제하고 push 가능했다. 
  - **main이 Protected면 보호 해제 필요**

```bash
## 원하는 remote 브랜치로 push 
git push origin [브랜치명]

## main 으로 push 원하는 경우 
git push origin [브랜치명] --force
```



##### 6. main 위에 svn-server-init 올리기

1. main 브랜치로 이동

```bash
git switch main
```

2. svn 브랜치 merge (unrelated history 허용)

```bash
git merge svn-server-init --allow-unrelated-histories
```

→ 서로 다른 Git 히스토리를 강제로 merge

3. 충돌 해결 후 commit

```bash
git add .
git commit
```



---



## 결과

- SVN revision → Git commit 변환 완료
- 기존 SVN 히스토리 보존
- GitLab 저장소로 완전 이전

------

## 한 줄 요약

- git-svn으로 SVN 전체 히스토리를 Git commit으로 변환 후, 브랜치 정리하고 GitLab에 push하여 마이그레이션 완료.