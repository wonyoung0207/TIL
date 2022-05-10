package day03;

import java.util.Scanner;

public class For문_ex {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = 0;
		int sum = 0;
		double avg = 0;
		
		
		System.out.println("10~99 사이의 숫자를 입력해 주세요.");
		num = input.nextInt();
		
		while(num < 10 || num > 99) {
			System.out.println("숫자의 범위를 다시 설정해 주세요");
			num = input.nextInt();			
		}
		
		for(int i=1; i <= num; i++) {
			sum += i;
		}
		
		avg = (double)sum / num;

		System.out.println("합 : " + sum + ", 평균 : " + avg);
	}

}
