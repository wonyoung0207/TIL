# Git 글로벌 사용자 vs 로컬 사용자

---

>[참고 사이트1](https://easyfly.tistory.com/1088)

### 글로벌 사용자 등록 (Global User Registration)

- **정의**: 글로벌 사용자 설정은 사용자의 컴퓨터 전체에 적용되는 Git 사용자 설정

- **설정 방법**:

  ```js
  // 어디서든 명령어 사용
  git config --global user.name "이름"
  git config --global user.email "이메일"
  ```

- **사례**: 예를 들어, 김철수 씨가 자신의 노트북에서 모든 Git 프로젝트에 대해 동일한 사용자 이름과 이메일 주소를 사용하고자 할 때 글로벌 설정을 사용합니다. 이렇게 설정하면 김철수 씨가 노트북에서 생성하거나 수정하는 모든 Git 프로젝트에 동일한 사용자 정보가 자동으로 적용됩니다.

### 로컬 사용자 등록 (Local User Registration)

- **정의**: 로컬 사용자 설정은 특정 Git 프로젝트에만 적용되는 사용자 설정입니다.

- **설정 방법**: 

  ```js
  // 프로젝트 디렉터리에서 명령어를 사용
  git config user.name "이름" 
  git config user.email "이메일" 
  ```

### 설정된 계정 삭제 

```bash
# global
git config --unset --global user.name
git config --unset --global user.email

# local
git config --unset user.name
git config --unset user.email
```
