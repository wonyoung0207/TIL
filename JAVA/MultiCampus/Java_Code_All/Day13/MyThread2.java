package day13;

public class MyThread2 implements Runnable{

	
	public void run() {
		int i = 0;
		while(i <= 100) {
			i++;
			System.out.println("MyThread2 : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	


}
