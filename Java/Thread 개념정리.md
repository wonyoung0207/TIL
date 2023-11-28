## Thread 개념정리 

---

>

## 스레드

### 정의 

- CPU 의 할당을 받아 실행되는 프로세스 안에서 실제로 작업을 수행하는 주체 
- 하나의 프로세스 안에는 여러 스레드가 존재 할 수 있다. 
- 두 개 이상의 스레드를 가지는 프로세스를 "멀티 프로세스" 라고 한다. 

### 생성방법

1. Runnable 인터페이스 사용
2. Thread 클래스 상속

```java
// 방법1. 스레드 생성과 스레드 내용을 분리해 구현 
public class Main {
    public static void main(String [] args) {
        Thread thread = new TaskThread1();
        thread.start();        
    }
    
    public static class TaskThread1 extends Thread {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}

// 방법2. Runnable 인터페이스를 이용해 스레드를 구현 
public class Main {
    public static void main(String [] args) {
        Thread thread = new Thread(new Task2());
        thread.start();
    }
 
    public static class Task2 implements Runnable {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}
```

### 유용한 메소드

1. `Thread.currentThread()`
   - 현재 실행되고있는 스레드 객체를 반환 
2. `Thread.sleep(millis)`
   - 스레드를 설정 밀리초 시간동안 멈춤 
   - 운영체제에 전달하여 이 시간동안 CPU를 사용하지 않음. 
3. `Thread.start()`
   - 스레드를 시작 
4. `thread.setName() `
   - 스레드의 이름을 지정 
5. `Thread.setPriority()`
   - 스레드의 우선순위를 지정한다. 
   - setPriority() 에는 1~10 의 값이 올 수 있다. 
   - Thread.MAX_PRIORITY() 라는 값을 매개변수로 설정해 우선순위를 10 으로 할당해 줄 수 있다 .
6. thread.setUncaughtExceptionHandler() ; 
   - 처음부터 전체 스레드에 해당되는 예외 핸들러를 지정 
   - 스레드가 실행되면서 캐치되지 않은 스레드의 예외들을 해당 핸들러가 처리해준다. 

### 스레드 요소 

1. 스택
   - 지역변수 저장 공간 
2. 명령어 포인터 
   - 스레드가 실행할 다음 명령어의 주소를 가리키는 역할을 함 .
3. 따라서 스레드마다 각각의 스택과 명령어 포인터를 가진다. 
   - 스택과 명령어를 제외한 나머지 영역은 하나의 프로세스 안에 있는 스레드가 공유할 수 있다.
   - 각각의 스레드는 하나의 시점에서 **지역변수에** 있는 함수 같은 기능을 수행하며 **다른 명령을 수행**하기 때문에 **스택과 명령어 포인터**를 각각 가진다.  
