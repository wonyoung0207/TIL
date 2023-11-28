# 스레드 실행 순서 제어 

---

>[참고 사이트1](https://dpdpwl.tistory.com/13)
>
>[참고 사이트2](https://defacto-standard.tistory.com/1191)

### 스레드 실행 순서 제어가 필요한 이유 

- 하나의 스레드는 메인 스레드가 끝나도 여전히 실행될 수 있다. 
  - 즉, main은 끝났는데 안에서 만들어진 스레드는 여전히 동작하고 있어 어플리케이션이 종료되지 않는 상황이 발생한다. 
- 또한 스레드간 참조된 값이 필요하다면 순서를 제어할 필요가 있다. 
  - t1 의 결과값이 t2 스레드에 필요한 경우 t1이 끝나기전에 t2가 실행되 재대로된 결과가 나오지 않을 수 있음. 
- 이때 스레드의 순서를 제어하기 위해 join을 사용한다. 
  - join() 은 InterruptedException을 발생시킨다. 
  - InterruptedException은 Exception 클래스를 상속받으므로 Checked Exception 이여서 예외처리를 해줘야한다. 

### `thread.join(ms)` 

- 스레드가 종료되어야만
  - 매개변수로 밀리초를 주면 해당 시간이 지나고나서도 결과가 반환되지 않으면 join() 메소드가 실행된다. 
  - 즉, **오래걸리는 특정 스레드를 계속 기다릴 수 없으니 join() 을 통해 설정한 시간만큼만 수행 후 스레드를 종료**한다. 

### 예시

```java
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new CustomThread());
        Thread thread2 = new Thread(new CustomThread());
        Thread thread3 = new Thread(new CustomThread());
        Thread thread4 = new Thread(new CustomThread());
        Thread thread5 = new Thread(new CustomThread());

        try {
            thread1.start();
            thread1.join();

            thread2.start(); // thread1의 결과가 반환되고 실행된다.
            thread2.join();

            thread3.start(); // thread2의 결과가 반환되고 실행된다.
            thread3.join(2000); 

            thread4.start(); // thread3 가 2초동안 끝나지 않는다면 thread3 종료 후 실행 
            thread4.join(); 

            thread5.start();
         // thread5.join();  // join을 하지 않았기 때문에 main스레드 종료 후 thread5가 종료될 수 있음 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of Main");
    }
}
// 결과 
// thread1 -> thread2 -> thread3 -> thread4 -> End of Main -> thread5 
```
