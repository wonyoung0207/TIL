package day11;

import java.util.Scanner;

public class MyException {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = 0;
		int result = 0;
		
		System.out.println("Input Number");
		String num = input.next();
		
		try {
			n = Integer.parseInt(num);
			result = 100/n;
			
		}
		catch(NumberFormatException e) {
			System.out.println("숫자의 형태가 아닙니다. ");
		}catch(ArithmeticException e) {
			System.out.println("분모가 0 입니다. ");
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println(e.toString());
			e.printStackTrace();//문제가 어디서 발생했는지 구체적으로 알려줌 

		}finally {//정상적으로 흘러가거나 문제 발생시 실행할 곳 , 따라서 항상 실행되는 영역 
			
		}

		System.out.println(result);
		
		

	}

}
