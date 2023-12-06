# Thread 임계영역

---

>[참고 사이트1_주의할점 내용](https://jgrammer.tistory.com/entry/Java-%ED%98%BC%EB%8F%99%EB%90%98%EB%8A%94-synchronized-%EB%8F%99%EA%B8%B0%ED%99%94-%EC%A0%95%EB%A6%AC)

## 임계영역 

### 정의

- 다른 스레드가 작업하고 있다면 해당 영역은 다른 스레드가 사용할 수 없다. 
- java에서는 synchronized 를 사용헤 임계영역을 설정한다. 
  - 임계영역은 인스턴스 단위로 lock이 공유된다. 
  - 예를들어 같은 인스턴스의 synchronized 가 붙은 요소들은 lock 이 함께 걸려 접근이 차단된다. 

### 주의할 점 

- 메소드 단위로 임계영역을 설정하면 다른 스레드에서는 **동일한 객체**의 **synchronized 로 설정된 다른 메서드**로 액세스할 수 없게 된다. 

  - 즉, increment() 메소드를 thread 1 이 실행중이라면, thread 2 는 decrement() 를 실행할 수 없다. 
  - 따라서 임계영역은 인스턴스 단위로 적용되는데, 이때 synchronized 가 걸린 부분에 대해서만 lock 이 되고 그 외의 부분에 대해선 호출해 사용할 수 있다. 
    - **임계영역은 인스턴스별로 lock 이 공유되어 synchronized가 있는 요소들이 lock 이 함께 걸린다.**
    - 따라서 만약 thread3이 SharedClass의 counter 변수에 접근하면 lock 이 걸리지 않아 사용할 수 있다. 

  ```java
  static class SharedClass {
      private int counter = 0;
  
      public synchronized void increment() {
          this.counter++;
      }
  
      public synchronized void decrement() {
          this.counter--;
      }
  }
  ```

### 임계 영역 설정 방법

1. method block

   - 인스턴스 안에 있는 메소드에 block 설정 

     ```java
     public class Counter {
         public int count;
         public Object obj;
     
         public synchronized void increase() {
             count++;
         }
     
         public synchronized void decrease() {
             count--;
         }
     }
     ```

2. synchronized block  생성

   - 임계 영역으로 사용될 부분에만 따로 block 설정 

   - **임계영역은 인스턴스별로 lock 이 공유되어 synchronized가 있는 요소들이 lock 이 함께 걸린다.** 

     ```java
     public class Counter {
         public int count;
         public Object obj; // 해당 인스턴스를 공유해서 LOCK 이걸리도록 만듬 
     
         public void increase() {
             synchronized(obj) {
                 count++;
             }
         }
     
         public void decrease() {
             synchronized(obj) {
                 count--;
             }
         }
     }
     ```

     

