package day04;

import java.util.Scanner;

public class ws01 {

	public static void main(String[] args) {
		int num1=0, num2=0;
		int in;
		int sum = 0;
		double avg;
		
		
		Scanner input = new Scanner(System.in);
		try {
			do {
				System.out.println("�� ���ڸ� �Է��ϼ���. (ù��° ���ڰ� �� �۾ƾ� �մϴ�.)");
				num1 = input.nextInt();
				num2 = input.nextInt();
				
				if((num1 >= 1 && num1 <= 99) && ((num2 >= 1 && num2 <= 99)) && (num1 < num2)){
					break;
				}			

			}while(true);
			
			for(int i = num1; i <= num2; i++) {
				sum += i;
			}
			
			avg = (double)sum / 2;
			System.out.println("�� : " + sum);
			System.out.println("��� : " + avg);
			input.close();
		}catch(Exception e) {
			System.out.println("�Էµ� ���� ���ڰ� �ƴմϴ�. ");
			
		}

		
//		try {
//			System.out.println("�� ���ڸ� �Է��ϼ���. (ù��° ���ڰ� �� �۾ƾ� �մϴ�.)");
//			num1 = input.nextInt();
//			num2 = input.nextInt();
//			if((num1 < 1 || num1 > 99) || ((num2 < 1 || num2 > 99)) || (num1 > num2)){
//				throw new Exception();//���� �߻� ��Ŵ
//				//return;//�̰͵� ���� 
//			}			
//			
//			for(int i = num1; i <= num2; i++) {
//				sum += i;
//			}
//			avg = (double)sum / 2;
//			System.out.println("�� : " + sum);
//			System.out.println("��� : " + avg);
//			
//		}catch(Exception e) {
//			System.out.println("���� �Է� ���ǿ� ���� �ʽ��ϴ�. ");
//		}
		


	}

}
