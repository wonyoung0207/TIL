package day13;

public class MyThread1 extends Thread{
//������ Ŭ���� -> Thread �� ��ӹ޾� run() �޼ҵ带 �����Ѵ�.  
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
