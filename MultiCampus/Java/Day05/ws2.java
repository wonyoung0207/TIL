package day05;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ws2 {

	public static void main(String[] args) {
		int arr[] = new int[10];
		int sum =0;
		int count = 0;
		double avg = 0;
		
		Random r = new Random();
		Scanner input = new Scanner(System.in);
		
		try {
			
			for(int i=0; i< arr.length; i++) {
				System.out.println("1~99������ ���ڸ� �Է����ּ���. ");
				arr[i] = input.nextInt();
				
				if(arr[i] < 1 || arr[i] > 99) {
					System.out.println("�ٽ� �Է��ϼ���.");
					i--;
					continue;
				}
				
				if(arr[i] % 2 != 0) {//Ȧ���� ���� 
					sum += arr[i];
					count++;
				}
				
			}
			
//			//���� ���� �ڵ� �Է�
//			for(int i=0; i< arr.length; i++) {
//				arr[i] = r.nextInt(99)+1;
//				if(arr[i] % 2 != 0) {//Ȧ���� ���� 
//					sum += arr[i];
//					count++;
//				}
//			}
			
			avg = (double) sum / count;
			
			System.out.println(Arrays.toString(arr));
			System.out.printf("Ȧ���� �� : %d, Ȧ���� ��� : %.2f " , sum, avg);
			
			
		}catch(Exception e) {
			System.out.println("���ڸ� �Է��ϼ��� ");
		}

		
	}

}
