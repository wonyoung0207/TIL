package day04;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ws02 {

	public static void main(String[] args) {
		int num = 0;
		int sum = 0;//짝수합만 구함 
		int count =0;
		double avg =0;
		Scanner input = new Scanner(System.in);
		Random r = new Random();
		
		
		try {
			do {
				System.out.println("10~99 사이의 숫자를 입력하세요");
				num = input.nextInt();
				
			}while(num < 10 || num > 99);
			
		}catch(Exception e){
			System.out.println("숫자만 입력하세요 ");
			
		}


		int randNum = r.nextInt(num-2)+2;// + 안하면 0부터 시작
		
//		// random다른방법
//		int randNum2 = (int)(Math.random()*num) + 2;// Math.random() => 0.0 ~ 1.0 사이 난수 발생
//		System.out.println( "난수 : "+randNum2);
		
		System.out.println("2~ " + num + "까지 수에서 난수 발생 : " + randNum);
		
		for (int i = 1; i <= randNum; i++) {
			if(i % 2 == 0) {
//				System.out.println(i);
				sum += i;
				count++;
			}
			
		}
		
		avg = (double)sum / count;
		System.out.println("1~ " + randNum + "까지 짝수의 합 : " + sum);
		System.out.println("1~ " + randNum + "까지 짝수의 평균 : " + avg);
		input.close();
		
		
//		// 메소드로 숫자인지 판별 
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
