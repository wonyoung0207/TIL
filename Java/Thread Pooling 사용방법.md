## Thread pool 사용방법

### 1. Thread Pool 생성 

- **ExecutorService** 구현 객체는 **Executors** 클래스의 다음 두가지 메소드 중 하나를 이용해 간편하게 생성

1. newCachedThreadPool**()**

   - 초기 스레드 개수가 0개로 **설정되며 스레드 개수보다 많은 양의 작업의 요청**되면 **새로운 스레드를 생성하여 작업을 처리**

   - 작업이 끝난 스레드가 60초 이상 새로운 작업요청이 없으면 스레드를 종료하고 스레드 풀에서 제거된다

   - 예시

     ```java
     @DisplayName("CachedThreadPool 을 생성한다.")
     @Test
     void testCounterWithConcurrencyCached() throws InterruptedException {
         int numberOfThreads = 18; // 총  실행할 스레드 
         ExecutorService service = Executors.newCachedThreadPool(); // 스레드 풀 생성 
         CountDownLatch latch = new CountDownLatch(numberOfThreads); //
         MyCounter counter = new MyCounter(); // 스레드 작업 
         iterateThread(numberOfThreads, service, latch, counter);
     
         assertThat(counter.getCount()).isEqualTo(numberOfThreads);
         assertThat(((ThreadPoolExecutor) service).getPoolSize()).isEqualTo(18);
         Thread.sleep(60000); // 60초 후 생성된 스레드가 제거되는지 확인한다.
         assertThat(((ThreadPoolExecutor) service).getPoolSize()).isEqualTo(0);
     }
     ```

2. newFixedThreadPool(int nThreads)

   - 파라미터로 제공되는 n 개 만큼 **스레드 풀을 생성**

     - 보통 일정량의 업무가 발생할 때 사용한다. 

   - 예시

     ```java
     @DisplayName("FixedThreadPool 을 생성한다.")
     @Test
     void testCounterWithConcurrencyFixed() throws InterruptedException {
         int numberOfThreads = 18; // 총 실행할 스레드 
         ExecutorService service = Executors.newFixedThreadPool(5); // 스레드 풀의 스레드 갯수를 5개로 지정 
         CountDownLatch latch = new CountDownLatch(numberOfThreads); 
         MyCounter counter = new MyCounter(); // 스레드 한개에서 실행할 작업 
         iterateThread(numberOfThreads, service, latch, counter);
     
         assertThat(((ThreadPoolExecutor) service).getPoolSize()).isEqualTo(5); // 스레드 풀 사이즈가 5와 같은지 검증함 (assertThat 은 검증할 때 쓰는 AssertJ라이브러리의 메소드임 )
     }
     
     private void iterateThread(int numberOfThreads, ExecutorService service, CountDownLatch latch, MyCounter counter) throws InterruptedException {
         for (int i = 0; i < numberOfThreads; i++) { // 총 스레드 만큼 실행  
             service.submit(() -> { // 스레드 풀에 설정한 스레드에 작업 할당 
                 counter.increment();
                 latch.countDown();
                 throw new IllegalArgumentException();
             });
         }
         latch.await();
     }
     ```

### 2. Thread pool 에 작업요청

- 스레드풀에게 작업을 처리 요청을 하기 위해선 **execute(), submit()** 2가지 메서드가 사용된다. 

  1. **execute();**
     - 작업 처리 결과를 반환하지 않는다.
     - 작업 처리 도중 예외가 발생하면 스레드가 종료되고 해당 스레드는 스레드 풀에서 제거된다. 
     - 다른 작업을 처리하기 위해 새로운 스레드를 생성한다.

  2. **submit();**

  - 작업 처리 결과를 반환한다.
    - 작업 처리 도중 예외가 발생하더라도 스레드는 종료되지 않고 다음 작업을 위해 재사용
  - 스레드의 생성 오버헤드를 방지하기 위해서라도 submit() 을 가급적 사용한다.

### 3. Thread pool 종료 

- 어플리케이션 프로세스를 종료하기 위해선 **스레드 풀을 강제로 종료**시켜 스레드를 해체시켜줘야 한다. 
  - 왜냐하면 , main 스레드가 종료되어도 스레드는 실행되고 있기 떄문이다. 
- **ExecutorService** 구현객체에서는 기본적으로 3개 종료 메서드를 제공한다.
  1. **excutorService.shutdown();**
     - 작업큐에 남아있는 작업까지 모두 마무리 후 종료 (오버헤드를 줄이기 위해 일반적으로 많이 사용.)
  2. **excutorService.shoutdownNow();**
     - 작업큐 작업 잔량 상관없이 강제 종료
  3. **excutorService.awaitTermination(long timeout, TimeUnit unit);**
     - 모든 작업 처리를 timeout 시간안에 처리하면 true 리턴 ,처리하지 못하면 작업스레드들을 interrupt()시키고 false리턴

### 4. 모든 Thread pool 개념 사용 코드 예시

```java
package ThreadPoolExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class main {

	public static void main(String[] args) {
		// ExecutorService 인터페이스 구현객체 Executors 정적메서드를 통해 최대 스레드 개수가 2인 스레드 풀 생성 
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 10; i++){
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					//스레드에게 시킬 작업 내용
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
					
					int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
					String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
					
					System.out.println("[총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
					
					//일부로 예외 발생 시킴
					int value = Integer.parseInt("예외");
				}
			};
			//스레드풀에게 작업 처리 요청
			executorService.execute(runnable);

			//콘솔 출력 시간을 주기 위해 메인스레드 0.01초 sleep을 걸어둠.
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		//스레드풀 종료
		executorService.shutdown();
	}
}
```

