1. 11일차

   ---

   > 인터페이스에대해 복습한다. 
   >
   > Frame을 사용해본다. 
   >
   > 예외처리에 대해 학습한다. 

   

   ## 1. Frame

      + 이클립스에서 frame을 생성한다. 
        + java.awt 패키지에 들어가 있다. 
      + frame을 객체로 만든다. 
        + Frame f = new Frame("MyFrame");
      + 사용 메소드
        + setSize( 300 ,200) 
          + 사이즈 설정
        + setVisible(true)
          + 화면에 표시할건지 
        + addWindowListener(new ...)
          + frame에 event를 붙이겠다. 
        + Button b = new Button("click")
          + f.add(b,"North") : 버튼을 frame 북쪽에 생성한다. 

   ## 2. 익명 클래스

      + 객체 생성시 변수를 선언하지 않고 바로 사용하는 클래스

      + ```java
        f.addWindowListener(new WindowAdapter() {
            // 익명클래스 형식으로 사용한다. 
            public void windowClosing(WindowEvent e) {
                //창을 닫는 기능
                System.exit(0);
            }
        });//frame에 event를 붙이겠다. 
        
        ```

   ## 3. **예외처리**(Exception)

      + 예상하지 못한 오류(**컴파일러가 잡아내지 못한 에러**)들을 처리하여 정상작동할 수 있도록 한다. 
        + 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인한 오류 이다.
        + **try catch finally**로 잡을 수 있다. 
      + 모든 예외는 최상위 클래스인 Exception 으로부터 상속받아 만들어 진다.  
      + try 에서 예외가 발생한 지점 밑에 **코드는 실행되지 않는다** 
      + 예외처리는 다른 객체에서 진행하는것이 아닌 main 화면 즉, 화면출력에 관한 클래스에서 예외처리를 해야한다. 
      + finally
        + 정상적으로 흘러가거나 문제 발생시 실행할 곳
        + 실행을 종료하기 전 실행할 것들을 넣어놓는다.  
      + 사용가능 메소드
        + e.printStackTrace() 
          + 문제 발생지를 구체적으로 알려줌 
        + e.toString ()
          + 어떤 오류인지 알려줌 
   
   ## 4. 예외 떠넘기기
   
      + 키워드는 **throw**와 **throws **를 사용한다. 
        + throw 는 예외를 발생시키는 키워드
        + throws는 예외를 던지는 키워드
      + **사용자 정의 Exception**
        + throw new MinusException;
        + thorw 사용한 메소드 옆에 throws MinusException써줘야함
        + 또한 throw를 **호출한 곳에 try catch** 를 사용해줘야한다. 
      + interface , 추상클래스에서 미리 발생할 수 있는 예외를 throws로 설정해 주는것이 좋다. 
   
   ## 5. 계산기 만들기 
   
      + 실수를 0으로 나누면 **무한대**, 정수는 **0으로 못나눔** 
        + try catch로 오류 잡아낸다. 
