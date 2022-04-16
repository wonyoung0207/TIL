# 4일차

> 조건문과 반복문에 대해 학습한다. 
>
> 깃허브와 eclipse 를 연동하는 방법을 알아본다.



1. 조건문 

   1. Switch (조건) { case 값 : break; }

   2. **if else 문**

2. 반복문

   1. **for 문**
   2. do while 문
   3. while문

3. 예외처리

   1. **try catch**
      + Throw new Exception() 으로 강제로 예외 발생 
      + **Return**; 실행되도 예외처리 됨

4. Random 난수생성

   1. Math 패키지
      + Math.random()
      + 0.0 ~ 1.0 사이의 난수를 발생시킨다. 
      + arr1[i] = (int)(Math.random() * 9) + 1 ; => 1~9까지에서 난수 발생 
      + 
   2. random 패키지 
      + Random r = new Random()   => r.nextInt(최대값)
      + 0 ~ a까지의 난수를 발생시킨다. 
        + **1을 더하지 않으면 10 미만, 1을 더하면 이하로 바뀐다. **
        + 즉, 0.0 ~ 9.9까지 출력하는것 같다. 
      + arr[i] = r.nextInt(9) + 1;  => (0~ 8) + 1까지 난수 생성 
      + 난수 생성시 **소수**로 출력
        + arr[i] = r.nextDouble()*10; 
        + 0.0 ~ 10.0 사이의 소수를 난수로 생성한다. 

5. Github와 eclipse 연동

   + 이클립스 -> 연동할 프로젝트 마우스 오른쪽 -> team -> shared -> 로컬 저장소 생성 및 연결 -> commit -> login
   + 이때 login 부분에서 username 과 password 를 입력해야 한다. 
     1. userName은 내 github username 이고, passwd는 github -> setting -> developer setting ->new token 으로 제공받은 token번호이다. 해당 토큰은 별도로 관리해야한다.
     1. 처음올릴때는 commit and push 하면 되지만, 기존 프로젝트를 받아서 올리는 거라면 commit 후 merge 과정을 거치고 push  해야한다. 

6. Github와 eclipse 연동방법

   1. 먼저 github에 레포지토리를 만든다. 
   2. 이클립스 window -> git repository -> clone a git repository 선택
   3. 복사한 url 맞는지 확인
   4. user 와 passwd 입력
   5. finish 될때까지 next  => **github와 local 저장소를 연동한것임**
   6. 그 다음 올릴 프로젝트에서 마우스 오른쪽 -> Team -> share Project
   7. create 눌러서 Local git에서 사용할 repository 설정
   8. 연결이 완료되었음. 이제 commit 후 push만 해주면 됨 
   9. Team -> commit -> ++ 버튼으로 Staged changes 로 올린 후 commit and push
   10. 복사한 url 넣고 github userName과 token으로 로그인
   11. Team -> push to branch in remote -> preview -> push

   + 따라서 깃 주소와 연동되어있는 local 저장소를 만들고, 해당 local 에서 commit and push 한다. 

7. Github와 eclipse 연동오류

   1. **rejected - non-fast-forward** 오류

      + 해당 오류는 Configure Fetch 때문에 나타난다. 
      + [참고사이트](https://tychejin.tistory.com/168)

      1. 이클립스 -> window -> other -> git repository -> 해당 프로젝트 repository -> remotes -> Origin -> github 주소 우클릭 후 **Configure Fetch**
      2. Ref mapping에 어떤게 존재한다면 **Advanced ** 클릭 ( 없다면 add 눌러서 추가 )
      3. 기존 존재하는 Source Ref 를 Remove(휴지통 모양) 눌러서 제거
      4. **Add create/update specification **박스안에서 
         1. Source ref: 지정(master[branch] 선택하면 알아서 자동입력된다.) 
         2. 해준 후 Add Spec 클릭
      5. Finish -> **Save and Fetch**
      6. Repository -> Branches -> Local 에 있는 파일 우클릭 -> Merge
      7. Team -> Remote -> push -> Add crete/update 부분에서 source ref 를 master 선택 -> add Spec 클릭 -> Finish -> push and save

8. 

