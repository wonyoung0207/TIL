package day04;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ws02 {

	public static void main(String[] args) {
		int num = 0;
		int sum = 0;//¦���ո� ���� 
		int count =0;
		double avg =0;
		Scanner input = new Scanner(System.in);
		Random r = new Random();
		
		
		try {
			do {
				System.out.println("10~99 ������ ���ڸ� �Է��ϼ���");
				num = input.nextInt();
				
			}while(num < 10 || num > 99);
			
		}catch(Exception e){
			System.out.println("���ڸ� �Է��ϼ��� ");
			
		}


		int randNum = r.nextInt(num-2)+2;// + ���ϸ� 0���� ����
		
//		// random�ٸ����
//		int randNum2 = (int)(Math.random()*num) + 2;// Math.random() => 0.0 ~ 1.0 ���� ���� �߻�
//		System.out.println( "���� : "+randNum2);
		
		System.out.println("2~ " + num + "���� ������ ���� �߻� : " + randNum);
		
		for (int i = 1; i <= randNum; i++) {
			if(i % 2 == 0) {
//				System.out.println(i);
				sum += i;
				count++;
			}
			
		}
		
		avg = (double)sum / count;
		System.out.println("1~ " + randNum + "���� ¦���� �� : " + sum);
		System.out.println("1~ " + randNum + "���� ¦���� ��� : " + avg);
		input.close();
		
		
//		// �޼ҵ�� �������� �Ǻ� 
//		public boolean isNumeric(String input) {
//			try {
//				Double.parseDouble(input);
//				return true;
//			}
//			catch (NumberFormatException e) {
//				return false;
//			}
//		}


	}

}
