## Thread의 ReentrantLock

---

>

## ReentrantLock 

### 정의

- synchronized와 비슷하게 동작하는 locking 기법이지만 , 더 많은 제어 기능을 제공한다. 
- 여러 스레드가 동시에 공유된 자원에 접근하는 것을 제어하기 위해 사용

### 사용방법

```java
Lock lockObj = new ReentrantLock();
Resource r = new Resource();

public void method(){
    lockObj.lock(); // locking ( 락 생성 )
    try{
	    use(resource);        
    } finally{
        // 만약 try에서 exception이나 까먹고 lcokObj.unlock() 을 하지 않았을 경우 "데드락 스레드" 가 발생하므로 try~finally 구문을 사용해야한다. 
	    lockObj.unlock(); // unLocking ( 락 해제 )
    }    
}
```

### synchronized   vs   ReentrantLock 

1. 유연성:
   - `ReentrantLock`은 `synchronized` 보다 더 많은 기능을 제공
     - 예를 들어, `tryLock()`를 통해 잠금을 시도하고 실패할 경우 다른 작업을 수행할 수 있다.
2. 가용성:
   - `ReentrantLock`은 `tryLock()`를 통해 잠금을 시도하고, 잠금을 얻지 못할 경우 다른 작업을 수행할 수 있는 반면 
   - `synchronized`는 잠금을 얻을 때까지 기다리며, 다른 스레드가 잠금을 해제할 때까지 블록됩니다.

### tryLock()

- `tryLock()`은 `ReentrantLock`에서 제공하는 메서드 중 하나로, 잠금을 시도하고 잠금을 획득할 수 있는지 여부를 확인하는 데 사용된다. 
- 잠금을 시도하고 실패할 경우 다른 작업을 수행하게 한다. 
- 장점
  - **다른 스레드가 잠금을 기다리는 대기 상태에 빠지지 않고 다른 작업을 수행**할 수 있다.
- 주의할점
  - tryLock()의 반환값을 확인해서 공유된 리소스를 사용할 수 있는지 여부를 판단해야 한다. (if문 사용 )
  - 또한, 다른 스레드가 획득한 lock에서 lock.unlock()을 호출하면, 예외가 발생하여 스레드가 다운된다. 

```java
 if (lock.tryLock()) { // 공유 리소스가 잠기지 않았을 경우에만 안쪽 매소드 실행 (즉, 공유 리소스에 대한 접근을 아예하지 못함 )
    try {
        // 잠금 획득 후 실행할 작업
        System.out.println(Thread.currentThread().getName() + " acquired the lock"); // 작업 진행 
        // 잠금 해제
    } finally {
        lock.unlock(); 
    }
} else {
    // 잠금을 얻지 못한 경우 다른 작업 수행
    System.out.println(Thread.currentThread().getName() + " couldn't acquire the lock");
}
```



---

## ReentrantReadWriteLock

### 정의

- `ReentrantLock`의 변형으로, 읽기와 쓰기 동작을 분리하여 동시성을 개선하는 데 사용한다. 
- 읽기 작업은 여러 스레드가 동시에 수행할 수 있지만, 쓰기 작업은 오직 하나의 스레드만 수행하는 특징을 이용한 방법이다. 
  - 즉, 읽기 연산은 동시에 수행되어도 데이터의 무결성을 유지할 수 있기 때문에 동시 수행이 가능하도록 하고, 쓰기 연산만 동기화를 이용한다. 

### 사용이유

1. read와 write기능을 나눠 read에 관한 기능은 스레드 대기 시간을 없애 성능을 높일 수 있다. 

### read lock 과  write lock 

1. ReadLock 
   1. `Lock readLock = new lock.readLock();` 으로 객체 생성해 사용할 수 있다. 
   2. read 기능은 모든 스레드가 동시에 실행해도 문제가 없으므로 readLock으로 Lock을 하면 동시에 여러 쓰레드가 해당 공유 리소스에 접근할 수있다. 
2. WriteLock
   1. `Lock writeLock = new lock.writeLock();` 으로 객체 생성해 사용할 수 있다. 
   2. write 기능은 순서가 중요하기 때문에 한번에 하나의 스레드만이 이용가능해야한다. 따라서 writeLock 을 사용하면 하나의 스레드만 사용 가능하며 unlock 이 될때까지 다른 스레드는 기다리게 된다. 

### 사용 방법

```java
public class Example {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static int sharedData = 0;

    public static void main(String[] args) {
        Runnable readTask = () -> {
            readLock.lock();
            try {
                // 읽기 작업 수행
                System.out.println(Thread.currentThread().getName() + " is reading: " + sharedData);
            } finally {
                readLock.unlock();
            }
        };

        Runnable writeTask = () -> {
            writeLock.lock();
            try {
                // 쓰기 작업 수행
                sharedData++;
                System.out.println(Thread.currentThread().getName() + " is writing: " + sharedData);
            } finally {
                writeLock.unlock();
            }
        };

        // 읽기 작업을 수행하는 스레드 생성 및 실행
        for (int i = 0; i < 5; i++) {
            Thread readerThread = new Thread(readTask);
            readerThread.start();
        }

        // 쓰기 작업을 수행하는 스레드 생성 및 실행
        Thread writerThread = new Thread(writeTask);
        writerThread.start();
    }
}
```

