package day03;

import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// ���� 
		double num1 = 0;
		double num2 = 0;
		double result = 0;
		String calc;
		String str;

		try {
			System.out.println("���� 2���� �Է��� �ּ���");
			num1 = input.nextDouble();
			num2 = input.nextDouble();

		}catch(Exception e) {
			System.out.println("���ڸ� �Է��ϼ��� ");
			num1 = 1;
			num2 = 5;

			//e.printStackTrace();
		}



		//do while�� ���� �ǹ̸� ������.
		System.out.println("������ +,-,/,* �� �ϳ��� �Է��ϼ��� ");
		calc = input.next();

		while(!(calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*"))) {
			System.out.println("�����ڸ� �߸��Է��ϼ̾��. �ٽ� �Է��ϼ���. ");
			System.out.println("������ +,-,/,* �� �ϳ��� �Է��ϼ��� ");
			calc = input.next();
		}

//		//while() �� ���� �ǹ̸� ������. 
//		do {
//			System.out.println("������ +,-,/,* �� �ϳ��� �Է��ϼ��� ");
//			calc = input.next();
//
//			if((calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*"))) {
//				break;
//			}
//
//			System.out.println("�����ڸ� �߸��Է��ϼ̾��. �ٽ� �Է��ϼ���. ");
//		}while( !(calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*")));





//		//		 if else�� ���� ���
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
//			System.out.println("�˸´� �����ڰ� �����ϴ�. ");
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
			System.out.println("������ �߸� �Է� ");
		}

		//		System.out.println(Math.round(result * 1000) );
		result = (double) Math.round(result * 100) / 100;
		// * �� / �� �⺻�����̱� ������ (double)�� �ٲ㼭 1000�� ������� �Ѵ� .
		//		System.out.println(result);

		if(result >= 0) {
			System.out.println("��� ����� " + result + "�� ��� �Դϴ�. ");

			if(result > 10) {
				str = "ū��";
			}else if(result >= 5) {
				str = "�߰�";
			}else {
				str = "������";
			}

			System.out.println("�� ���ڴ� " + str + " �Դϴ�. ");

		}else {
			System.out.println("��� ����� ���� �Դϴ�. ");
		}


		//String a1 = "" + num1 + calc + num2;
		//System.out.println(a1.getClass().getSimpleName());//Ÿ�� �˾ƺ���
		//result = a1.eval() // �ڹٿ����� eval() ���� => �ڹ� ��ũ��Ʈ�� ���� 

	}

}
