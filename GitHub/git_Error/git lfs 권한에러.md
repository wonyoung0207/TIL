# Git LFS Push 에러 정리

---

>

## 문제 상황

- GitHub 저장소에 모델/이미지 등 대용량 파일을 LFS로 관리 중.
- push 시 다음과 같은 에러 발생:

```bash
remote: error: GH008: Your push referenced at least 3 unknown Git LFS objects
hint: The '.git/hooks/pre-push' hook was ignored because it's not set as executable.
```

- `git lfs push --all origin master` 실행 시에는 정상 업로드되지만,
- 새로운 LFS 파일을 추가/커밋하면 같은 문제가 반복됨.

## 원인

1. **Git LFS pre-push hook** 이 실행되지 않음
   - 로그에 `pre-push hook was ignored because it's not set as executable` 경고가 표시됨
   - 즉, LFS 파일을 자동 업로드해야 할 hook이 권한 문제로 무시되고 있음
2. 결과적으로:
   - 커밋에는 LFS 포인터만 올라감
   - 실제 LFS 오브젝트는 원격에 업로드되지 않음
   - GitHub에서 "알 수 없는 LFS object" 에러 발생

## 해결 방법

### 1. pre-push hook 실행 권한 부여

```
chmod +x .git/hooks/pre-push
```

### 2. Git LFS hook 강제 재설치

```
git lfs install --force
```

### 3. 누락된 오브젝트 수동 업로드

```
git lfs push origin master
```

## 예방

- hook 권한을 올바르게 설정하면, `git push` 실행 시 LFS 오브젝트도 자동으로 업로드됨.
- hook이 비활성화된 상태라면 push할 때마다 수동으로 `git lfs push` 해야 하므로 반드시 권한 문제를 해결해야 함.

## 정리

- **문제**: Git LFS pre-push hook 실행 안 됨 → LFS 오브젝트가 원격에 안 올라가 push 거부됨
- **원인**: `.git/hooks/pre-push` 에 실행 권한 없음
- **해결책**:
  - `chmod +x .git/hooks/pre-push`
  - 또는 `git lfs install --force`
  - 누락된 오브젝트는 `git lfs push origin master` 로 수동 업로드