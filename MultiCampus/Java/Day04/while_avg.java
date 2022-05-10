package day04;

import java.util.Scanner;

public class while_avg {

	public static void main(String[] args) {
		int sum = 0;
		double avg = 0;
		int num=0;
		int count = 1;
		
		Scanner input =  new Scanner(System.in);
		
		do {
			System.out.println("2~99까지의 정수를 입력하세요.");
			num = input.nextInt();
			
		}while(num < 2 || num > 99);
		
		while(count <= num) {
			sum += count;
			count++;
		}
		
		avg = (double)sum / (count-1);
		
		System.out.println("1에서 " + num + "까지의 합 : " + sum);
		System.out.println("1에서 " + num + "까지의 평균 : " + avg);
		
		
		
	}

}
