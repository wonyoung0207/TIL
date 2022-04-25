package day13;

public class MainThread2 {

	public static void main(String[] args) {
		System.out.println("Start.........");
		
		Thread t1 = new Thread(new Runnable() {//익명클래스를 이용해서 스레드 생성 
			public void run() {
				int i = 0;
				while(i <= 100) {
					i++;
					System.out.println("annoThread1 : " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {//익명클래스를 이용해서 스레드 생성 
			public void run() {
				int i = 0;
				while(i <= 100) {
					i++;
					System.out.println("annoThread2 : " + i);
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		t2.start();
		
		
		Runnable r1 = new Runnable() {//인터페이스 형태로 만들어졌다. 
			public void run() {
				int i = 0;
				while(i <= 100) {
					i++;
					System.out.println("annoThread3 : " + i);
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		};
		Thread t3 = new Thread(r1);
		t3.start();
		
		System.out.println("End.........");
	}

}
