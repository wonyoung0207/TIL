package day13;

public class MyThread1 extends Thread{
//스레드 클래스 -> Thread 를 상속받아 run() 메소드를 구현한다.  
// 
	@Override
	public void run() {
		int i = 0;
		while(i <= 100) {
			i++;
			System.out.println("MyThread1 : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		super.run();
	}

}
