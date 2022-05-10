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
				System.out.println("두 숫자를 입력하세요. (첫번째 숫자가 더 작아야 합니다.)");
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
			System.out.println("합 : " + sum);
			System.out.println("평균 : " + avg);
			input.close();
		}catch(Exception e) {
			System.out.println("입력된 값이 숫자가 아닙니다. ");
			
		}

		
//		try {
//			System.out.println("두 숫자를 입력하세요. (첫번째 숫자가 더 작아야 합니다.)");
//			num1 = input.nextInt();
//			num2 = input.nextInt();
//			if((num1 < 1 || num1 > 99) || ((num2 < 1 || num2 > 99)) || (num1 > num2)){
//				throw new Exception();//예외 발생 시킴
//				//return;//이것도 가능 
//			}			
//			
//			for(int i = num1; i <= num2; i++) {
//				sum += i;
//			}
//			avg = (double)sum / 2;
//			System.out.println("합 : " + sum);
//			System.out.println("평균 : " + avg);
//			
//		}catch(Exception e) {
//			System.out.println("숫자 입력 조건에 맞지 않습니다. ");
//		}
		


	}

}
