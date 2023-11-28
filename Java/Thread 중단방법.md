# 스레드 중단 방법

---

>[참고 사이트1](https://kadosholy.tistory.com/122)
>
>[참고 사이트2](https://velog.io/@wijoonwu/Java-%EB%8D%B0%EB%AA%AC-%EC%93%B0%EB%A0%88%EB%93%9C)

## 스레드 중단

### 스레드 중단 이유 

- 스레드는 아무것도 안할때도 메모리와 일부 커널 리소스를 사용한다.
  - 스레드가 실행중일 떄는 CPU 시간뿐만아니라 CPU 캐시 공간도 사용한다. 
  - 즉, 사용하지 않을때는 리소스를 줄이기위해 스레드를 제거해야한다. 

- 또한 **프로세스 안에서 스레드가 하나라도 실행중이라면 main 스레드가 멈춰도 애플리케이션은 중단되지 않는다.** 

### 중단 메소드 

1. `thread.interrupt();`

   - interrupt() 한 스레드에 InterruptException() 을 발생시킨다. 
   - 예외처리를 별도로 하지 않으면 아무리 interrupt() 를 보내도 반응하지 않기 때문에 개발자가 예외처리를 해줘야한다. 
   - 예외처리는 가장 많은 부하가 있는 곳에 걸어야 좋다. 
   - 또한 예외처리시 catch 안에 아무것도 없어서는 안된다.

   ```java
   public static void main(String[] args){
       Thread t1 = new Thread(new Task);
       t1.start();
       t1.interrupt();
   }
   
   private static class Task implements Runnable {
       
       @Override
       public void run(){
           // 방법1. 예외처리로 중단 명령어 실행 
           try{
               Thread.sleep(500000); // 오랜시간 프로세스 제움 
           }catch(InterruptedException e ){ // interrupt() 로 인해 호출됨 
               System.out.println("stop program!! ") // 예외 발생해 스레드 종료 
           }
           
           // 방법2. 현재 스레드 상태값으로 중단 
           if(Thread.currentThread().isInterrupted()){
               System.out.println("stop program!! ")
           }
       }
   }
   ```

2. `Deamon thread`

   - 운영체제에서 **백그라운드로 동작**하는 프로그램을 데몬(Daemon)이라고 한다.

     - 자바에서 이런 데몬과 유사하게 동작하는 쓰레드를 데몬 쓰레드(Daemon Thread)라고 한다

   - 백그라운드에서 특별한 작업을 처리하게 하는 용도로 만든다.

   - 특징 

     - **일반 쓰레드(main 등)가 모두 종료되면 강제적으로 종료**된다. 
       - 즉, JVM이 데몬스레드가 작업이 끝날때까지 기다리지 않는다. 
     - 백그라운드 실행으로, 낮은 우선순위를 가진다. .

   - 형태

     ```java
     Thread t1 = new MyThread();
     t1.setDaemon(true);     // true로 설정시 데몬스레드로 동작함
     t1.start();
     ```

   ```java
   public class MyThread extends Thread {
   	public void run() {
   		try {
   			int count=0;
   			while(true) {
   				System.out.print(count++);
   				Thread.sleep(200); // 0.2 초마다 값 증가 (백그라운드에서 실행 )
   			}
   		} catch (InterruptedException e) {
   			System.out.print("Interrupted!!!!!");
   		}
   	}
   }
   
   public class HelloWorld {
   	public static void main(String[] args) {
   		MyThread mt1 = new MyThread();
   		mt1.setDaemon(true);
   		mt1.start(); // 데몬 스레드 실행 	
   		
   		try {
   			Thread.sleep(2000); // 2초 기다리고 main 스레드 종료 -> main스레드 종료로 인해 대몬 스레드도 함께 종료 
   		} catch (InterruptedException e) {
   			e.printStackTrace(); 
   		}
   	}
   }
   ```

    

