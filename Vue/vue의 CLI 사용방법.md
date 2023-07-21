# Vue CLI 사용방법

---

>

## CLI ( Commend Line Interface )

### 정의

-  vue 개발팀이 관리하는 도구로, vue개발에 있어 개발에 편의성을 주기 위한 **부가적인 도구**이다. 
   - 리눅스의 cmd 와 비슷하다고 보면 됨 


### 설치방법

1. cmd 를 킨다. 

2. 다음 명령어를 입력한다. 

   ```javascript
   sudo npm install -g @vue/cli
   // sudo 는 관리자 권한으로 실행한다는 소리. 만약 이부분 오류나면 지우고 실행해도 됨
   // -g : 전역으로 설치한다는 의미. 
   ```

3. 설치 확인

   ```javascript
   vue --version
   ```

### vue 프로젝트 생성

1. 생성하고 싶은 폴더에서 cmd 열기 

2. 다음 명령어 입력

   ```javascript
   vue create fileName
   ```

3. vue 프로젝트 생성된것을 확인

