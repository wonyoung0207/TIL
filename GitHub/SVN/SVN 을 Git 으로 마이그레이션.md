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



##### 2. SVN 저장소 전체 히스토리 clone

```bash
git svn clone SVN주소
```

- 또는 (trunk/branches/tags 구조일 때)

```bash
git svn clone SVN주소 --stdlayout
```

- 모든 revision을 Git commit으로 변환
- SVN 히스토리 그대로 유지됨



##### 3. 기본 브랜치 정리

- SVN에서 가져온 브랜치를 `main` 으로 정리

```bash
git branch -m svn-server-init main
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
git push origin main --force
```

---

## 결과

- SVN revision → Git commit 변환 완료
- 기존 SVN 히스토리 보존
- GitLab 저장소로 완전 이전

------

## 한 줄 요약

- git-svn으로 SVN 전체 히스토리를 Git commit으로 변환 후, 브랜치 정리하고 GitLab에 push하여 마이그레이션 완료.