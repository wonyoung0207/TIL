package day13;

public class MainThread {

	public static void main(String[] args) {
		System.out.println("Start.........");
		MyThread1 t1 = new MyThread1();
		t1.start();
		
		MyThread2 t2 = new MyThread2();
		Thread tt2 = new Thread(t2);
		tt2.start();
		
		System.out.println("End.........");
	}

}
