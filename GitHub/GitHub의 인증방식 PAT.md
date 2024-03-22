# GitHub 의 인증방식 PAT 

---

>[참고 사이트1](https://velog.io/@2214yj/github%EC%97%90%EC%84%9C-PAT%EB%A5%BC-%EC%84%A0%ED%83%9D%ED%95%9C-%EC%9D%B4%EC%9C%A0)
>
>[github 엑세스 자격 증명](https://docs.github.com/ko/enterprise-server@3.8/authentication/keeping-your-account-and-data-secure/updating-your-github-access-credentials)
>
>[참고 사이트2](https://iamjjanga.tistory.com/16)

## 공부하게 된 이유 

### 이유 1

1. 회사 노트북에서 내 Github의 레포지토리 중 private 에 pull & push 하려고 했는데 `Authentication failed` 가 발생해 pull & push 가 불가능했다. 
2. 찾아보니, 내 GitHub 계정에 등록된 PAT 이 없어 private에 접근할 수없던 것. 
3. 회사에서는 내 계정으로 push를 하지 않으려고 했었는데 이번 기회에 push를 하려고 더 알아보니 PAT에 대해 공부하게 되었다. 
4. 또한 `2021년 8월 13일` 부로 GitHub.com에서 Git 작업을 인증 할 때 더 이상 `계정 암호를 허용하지 않`으며, `token-based authentication이 필요`하다고 한다

### 이유 2

1. 회사 컴퓨터에서 회사 Github 계정만 사용하다가 개인 Github을 사용할 일이 생겼다. 
   1. 즉, push를 하는데 github 계정이 달라 충돌이 발생했다. 
   2. 해당 이유는 `git config` 에서 `로컬 사용자` 를 내 계정으로 맞춰주어 해결할 수 있었다. 
2. `git config` 방법 말고도 npm 의 git-user-switch를 이용한 방법도 있다. 
   1. [git user switch 이용 방법](https://cloudest.oopy.io/posting/098)

# PAT (Personal Access Token)

## 정의

1. Personal Access Token(PAT)
2. OAuth token과 유사한 문자열로된 token으로,  **scope**이라는 것을 통해 permission 제한하고, password와 유사하나 일회생성의 특징이 있다. 
   1. 조심할 점은 한번 생성하고 따로 저장하지 않으면 같은  문자열을 만들 수 없으므로 다시 생성해야한다.
3. github는 ID-PASSWORD 방식도 있지만, Persional Access Token(PAT)를 권고하고 있다.
   1. 즉, push를 위해선 PAT이 필요하다. 
   2. **또한 PAT에 따라 쓰기 등의 여러 권한을 설정해 사용자별 레포지토리의 사용 권한을 컨트롤 한다.** 

## PAT 이점 

1. **권한 관리**
   1.  PAT를 생성할 때 사용자는 특정 권한과 범위를 지정할 수 있습니다. 
   2. 따라서 PAT를 사용하여 수행할 수 있는 작업을 제어할 수 있습니다. 
   3. 예를 들어, 특정 repository에 대한 읽기/쓰기 권한만 부여할 수 있습니다.
2. Github의 토큰은 기본 해시에 불과하며, 이는 암호 기반 인증에 필요한 무거운 암호화/암호화 해제보다 더 효율적입니다. 이를 통해 Github에서 인증 프로세스를 빠르게 완료할 수 있습니다.
3. 토큰은 사용 케이스 또는 장치 별로 여러 개를 생성할 수 있습니다.
4. 토큰은 언제든지 각 토큰에 대한 접근 권한을 철회할 수 있습니다.
5. 토큰은 임의로 생성되는 일련의 문자열이며 암호 기반 인증 시스템에 대해 실행할 수 있는 무차별 대입 공격에 영향을 받지 않습니다. 따라서 토큰 기반 시스템은 암호에 의존하는 시스템보다 더 안전합니다.
6. 토큰을 한 번 생성하고 나면 토큰을 다시 확인할 수 없기 때문에 username과 password가 해킹되더라도 토큰이 유출되는 것을 막을 수 있습니다.

## Password 와 PAT 차이점

1. **안전성**: PAT는 GitHub 계정에 대한 암호가 아닌 토큰이므로, 암호를 노출시키지 않고도 인증할 수 있습니다. 이는 보안상의 장점을 제공합니다.
2. **권한 관리**: PAT를 생성할 때 사용자는 특정 권한과 범위를 지정할 수 있으므로, 사용자는 필요한 권한만 부여하여 보다 정확하게 액세스를 제어할 수 있습니다.
3. **추적성**: PAT는 GitHub에서 수행한 작업을 추적할 수 있도록 합니다. 각각의 PAT는 특정 작업에 사용될 수 있으므로, 이를 통해 특정 작업을 수행하는 데 사용된 액세스를 추적할 수 있습니다.

## 사용방법

1. GitHub의 우측 상단 profile photo를 클릭하고, `Settings`를 클릭
2. 좌측 `sidebar - Developer settings`
3. 좌측 `sidebar - Personal access tokens`
4. `Generate new token` 클릭 
5.  Token description name을 입력하고, Scopes를 설정
   1. 일반적으로 repositories에 대한 접근은 `repo`만 체크하나, 특정 환경에서는 이상의 scope을 요구할 수 있음. 
   2. 즉, repo만 체크하면 됨 
   3. 기간은 마음대로지만, 무제한으로 하는건 권장되지 않는다. 
6. Generate token을 클릭하고나면, 생성된 token 우측에 📋를 클릭하여 token을 copy한다.
   1. 재사용이 불가능 하기때문에 Local에 저장해서 사용하거나 아니면 새롭게 regenerate하여 사용하면 된다