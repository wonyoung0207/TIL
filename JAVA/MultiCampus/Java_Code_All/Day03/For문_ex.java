package day03;

import java.util.Scanner;

public class For��_ex {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = 0;
		int sum = 0;
		double avg = 0;
		
		
		System.out.println("10~99 ������ ���ڸ� �Է��� �ּ���.");
		num = input.nextInt();
		
		while(num < 10 || num > 99) {
			System.out.println("������ ������ �ٽ� ������ �ּ���");
			num = input.nextInt();			
		}
		
		for(int i=1; i <= num; i++) {
			sum += i;
		}
		
		avg = (double)sum / num;

		System.out.println("�� : " + sum + ", ��� : " + avg);
	}

}
