package day03;

import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 계산기 
		double num1 = 0;
		double num2 = 0;
		double result = 0;
		String calc;
		String str;

		try {
			System.out.println("숫자 2개를 입력해 주세요");
			num1 = input.nextDouble();
			num2 = input.nextDouble();

		}catch(Exception e) {
			System.out.println("숫자를 입력하세요 ");
			num1 = 1;
			num2 = 5;

			//e.printStackTrace();
		}



		//do while과 같은 의미를 가진다.
		System.out.println("연산자 +,-,/,* 중 하나를 입력하세요 ");
		calc = input.next();

		while(!(calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*"))) {
			System.out.println("연산자를 잘못입력하셨어요. 다시 입력하세요. ");
			System.out.println("연산자 +,-,/,* 중 하나를 입력하세요 ");
			calc = input.next();
		}

//		//while() 과 같은 의미를 가진다. 
//		do {
//			System.out.println("연산자 +,-,/,* 중 하나를 입력하세요 ");
//			calc = input.next();
//
//			if((calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*"))) {
//				break;
//			}
//
//			System.out.println("연산자를 잘못입력하셨어요. 다시 입력하세요. ");
//		}while( !(calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*")));





//		//		 if else와 같은 방법
//		switch(calc) {
//		case "+" : 
//			result = num1 + num2;
//			break;
//		case "-" :
//			result = num1 + num2;
//			break;
//		case "*" :
//			result = num1 * num2;
//			break;
//		case "/" :
//			result = num1 / num2;
//			break;
//		default:
//			System.out.println("알맞는 연산자가 없습니다. ");
//			break;
//		}



		if(calc.equals("+")) {
			result = num1 + num2;
		}else if(calc.equals("-")){
			result = num1 - num2;
		}else if(calc.equals("*")){
			result = num1 * num2;
		}else if(calc.equals("/")){
			result = num1 / num2;
		}else {
			System.out.println("연산자 잘못 입력 ");
		}

		//		System.out.println(Math.round(result * 1000) );
		result = (double) Math.round(result * 100) / 100;
		// * 와 / 는 기본연산이기 때문에 (double)로 바꿔서 1000을 나눠줘야 한다 .
		//		System.out.println(result);

		if(result >= 0) {
			System.out.println("계산 결과는 " + result + "로 양수 입니다. ");

			if(result > 10) {
				str = "큰수";
			}else if(result >= 5) {
				str = "중간";
			}else {
				str = "작은수";
			}

			System.out.println("이 숫자는 " + str + " 입니다. ");

		}else {
			System.out.println("계산 결과는 음수 입니다. ");
		}


		//String a1 = "" + num1 + calc + num2;
		//System.out.println(a1.getClass().getSimpleName());//타입 알아보기
		//result = a1.eval() // 자바에서는 eval() 없음 => 자바 스크립트에 있음 

	}

}
