package day16;

import java.util.Scanner;

public class LotteGameApp {

	public static void main(String[] args) {
		LotteGame[] arr = null;
		Scanner input = new Scanner(System.in);
		int num;


		LotteGame.randArray();//�ζ� ���� ��ǥ 
		
		try {
			System.out.println("�� ���� ��ðھ��?");
			num = input.nextInt();
			if(num >= 10) {
				System.out.println("10�� �̻��� ��� �����ϴ�. �ٽ� �������ּ���.");
				return ;
			}
			arr = new LotteGame[num];
			System.out.println("�� " + num + "���� �Է��մϴ�. ");
				
		}catch(Exception e) {
			System.out.println("�߸��Է��ϼ̽��ϴ�. ���α׷� ���� ");
			return ;
		}
		
		
		
		
		for(int i = 0; i < arr.length; i++) {
			try {
				System.out.println("********** "+(i+1) + "��° ���� ��÷��ȣ�� ���纾�ϴ�. " + " **********");
				arr[i] = new LotteGame();
				arr[i].insertArray();//���� �Է� 
				
			}catch(Exception e) {
				System.out.println((i+1) + "��° ���� �ٽ� �Է¹޽��ϴ�. ");
				i--;
			}
		}
//
//		game.randArray();//�ζ� ���� ��ǥ 
		
		for(int i = 0; i< arr.length; i++) {//������ ���ߴ� ��
			System.out.println("******"+(i+1) + "��° ���� ��÷��ȣ�� ���纾�ϴ�. " + "******");
			
			arr[i].compare();
			arr[i].rank();
		}
		

		System.out.println("********** ��� ********** ");
		System.out.printf("���� �� ����� %,3d�� �Դϴ�. ",LotteGame.correct_money);
		
		
		
		

	}

}
