# Thread 조건변수 wait()

---

>[참고 사이트1](https://cypsw.tistory.com/entry/C-Task-Wait-vs-await-%EC%B0%A8%EC%9D%B4%EC%A0%90)

## wait 

- 스레드를 완전히 sleep 시켜 다른 스레드가 신호를 보내 깨울 때까지 CPU 점유를 포기한다. 
- `wait()`를 호출한 스레드는 해당 객체의 모니터 락을 반납하고 대기하게 되며, 다른 스레드가 `notify()` 혹은 `notifyAll()` 메서드를 호출하여 대기 중인 스레드를 깨워줘야 한다.
- 즉, 깨어나기까지 조건 변수와 관련된 **lock 을 unlock 한다.** 

### signal 

- 현재 조건 변수에서 기다리는 스레드를 깨우는데 조건 변수가 기다리는 스레드가 하나 이상일 떄 오직 하나만 깨어나고 나머지는 슬립상태로 남는다. 
- 모든 조건변수를 깨우려면 signalAll 을 사용하면 된다. 

### notify

- wait 하고 있는 스레드를 깨울 떄 사용한다. 
- 즉, 다른 스레드에서 `sleepThread1.notify() ` 를 사용해 깨울 수 있다. 

### await vs wait

1. await 
   1. 비동기 처리시 사용하는 키워드로, await된 구간의 처리를 하나의 쓰레드에 맡기고 main 스레드는 await 의 다음 부분을 실행한다. 
   2. 비동기 처리가 끝날때까지 현재 스레드를 차단한다. 
   3. 즉, 결과값을 기다리지 않고 main 스레드는 계속 진행된다. 
2. wait()
   1. 해당 객체의 모니터 락을 획득한 스레드가 다른 스레드가 notify() 또는 notifyAll()을 호출할 때까지 대기 상태로 기다린다. 
   2. 하나의 스레드를 멈추고 다른 스레드에서 notify() 해줄때까지 기다린다. 

```java
class SomeClass2 {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean isCompleted = false;
 
    public void declareSuccess() throws InterruptedException {
        lock.lock();
        try {
            // 1. await  
            while (!isCompleted) {
                condition.await(); // await() 의 처리가 끝날때까지 비동기로 기다린다. 
            }
            
            // 2. wait() : 인스턴스 자체를 락한다. 
            while (!isCompleted) {
          	  wait(); // 다른 스레드에서 notify() 해줄때까지 sleep 한다. 
            }
        }
        finally {
            lock.unlock();
        }
       
        System.out.println("Success!!");
    }
 
    public void finishWork() {
        lock.lock();
        try {
            isCompleted = true;
            condition.signal();
        }
        finally {
            lock.unlock();
        }
    }
}
```
