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
				System.out.println("1~99사이의 숫자를 입력해주세요. ");
				arr[i] = input.nextInt();
				
				if(arr[i] < 1 || arr[i] > 99) {
					System.out.println("다시 입력하세요.");
					i--;
					continue;
				}
				
				if(arr[i] % 2 != 0) {//홀수만 실행 
					sum += arr[i];
					count++;
				}
				
			}
			
//			//랜덤 숫자 자동 입력
//			for(int i=0; i< arr.length; i++) {
//				arr[i] = r.nextInt(99)+1;
//				if(arr[i] % 2 != 0) {//홀수만 실행 
//					sum += arr[i];
//					count++;
//				}
//			}
			
			avg = (double) sum / count;
			
			System.out.println(Arrays.toString(arr));
			System.out.printf("홀수의 합 : %d, 홀수의 평균 : %.2f " , sum, avg);
			
			
		}catch(Exception e) {
			System.out.println("숫자만 입력하세요 ");
		}

		
	}

}
