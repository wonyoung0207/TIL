package day13;
//�����带 �н��Ѵ�. 
// �� Ŭ������ �����Ű�� ���μ����� �ȴ�. 
// �ش� Ŭ������ ���� ���μ��� ���α׷��̴�. 

public class MainApp {

	public static void main(String[] args) {//main �ϳ��� �����尡 ����ȴ�. 
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
