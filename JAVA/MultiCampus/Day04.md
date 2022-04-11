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
   2. random 패키지 
      + Random r = new Random()   => r.random(a)
      + 0 ~ a까지의 난수를 발생시킨다. 

5. Github와 eclipse 연동

   + 이클립스 -> 연동할 프로젝트 마우스 오른쪽 -> team -> shared -> 로컬 저장소 생성 및 연결 -> commit -> login

   + 이때 login 부분에서 username 과 password 를 입력해야 한다. 
     1. userName은 내 github username 이고, passwd는 github -> setting -> developer setting ->new token 으로 제공받은 token번호이다. 해당 토큰은 별도로 관리해야한다.