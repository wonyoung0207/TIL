# Thread 의 Semaphore 

---

>

## Semaphore

### 정의 

1. 동시에 특정 리소스에 접근할 수 있는 스레드의 개수를 제어하는데 사용하는 동기화 기법이다. 
2. 즉, Semaphore를 생성할 때 허용할 스레드 개수를 설정하고, 스레드가 리소스에 접근하려면 해당 스레드가 Semaphore를 획득해야 하는 구조이다. 
3. Semaphore가 이미 다른 스레드에 의해 모두 획득되었다면, 해당 스레드는 대기 상태로 전환된다. 

### 사용 메소드

1. acquire() 
   - Semaphore 를 획득해 임계 영역에 들어갈 수 있는 권한을 획득한다. 
   - **0이면 아무 스레드도 사용못하고 무조건 "스레드 리스트" 의 목록에 저장**되어 release가 될때까지 Thread.sleep으로 기다린다. 
     - 즉, 다른곳에서 해당 Semaphore를 release 해줘야 다음 줄로 넘어간다. 
2. release()
   - Semaphore를 반납 및 해제를 통해 임계영역에 들어갈 수 있는 사용자 수를 반납한다. 

### 사용방법

- 생산자와 소비자로 예시를 듬
  - 소비자는 생산자가 Item을 만들어야 소비할 수 있음 
  - 생산자는 1개가 완성되면 소비자를 깨워 소비하게 함 
  - 소비자가 소비를 끝내면 다른 쓰레드가 그제서야 다시 생산할 수 있는 권한을 얻을 수 있음
  - 즉, 소비자가 소비를 하기 위해선 생산자가 생산하기를 기다리고, 생산자는 item을 생산한 후 소비자가 소비할때까지 다음Thread의 작업을 막는다. 

```java
Semaphore full = new Semaphore(0); // 하나의 스레드조차 사용하지 못함 
Semaphore empty = new Semaphore(1); // 큐 사이즈 (한개의 스레드만이 사용가능함)
Queue queue = new ArrayDeque();
Lock lock = new ReentrantLock();

// 1.판매자
while(true){
    Item item = produce(); // 2 
    empty.acquire(); // 3 ( empty 에 대한 사용권 획득) -> empty의 Semaphore가 1이기 때문에 다른 쓰레드들은 lock 됨
    lock.lock(); // 4 ( 같은 ReentrantLock를 사용하는 스레드에 대해 lock )
    queue.offer(item); // 5 ( 큐에 item추가 )
    lock.unlock(); // 6 ( lock 해제 )
    full.release(); // 7 ( 소비자 스레드를 깨운다. )
}

// 2.소비자
while(true){
    full.acquire(); // 1 (Semaphore가 0 이므로 어떠한 소비자 스레드조차 실행되지 않고 바로 "스레드 리스트" 에 저장해 대기하도록 만든다.) 
    lock.lock(); // 8
    Item item = queue.poll(); // 9 ( 큐에 저장된 값을 꺼내옴 )
    lock.unlock(); // 10
    consume(item);
    empty.release(); 
}
```

