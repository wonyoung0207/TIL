package day13;
//스레드를 학습한다. 
// 이 클래스를 실행시키면 프로세스가 된다. 
// 해당 클래스는 단일 프로세스 프로그램이다. 

public class MainApp {

	public static void main(String[] args) {//main 하나의 스레드가 실행된다. 
		System.out.println("Start");
		int i = 0;
		
		while(i <= 20) {
			System.out.println(i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("End");

	}

}
