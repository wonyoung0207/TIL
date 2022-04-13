package day03;
//문자열과 객체는 stack 영역이 아닌 heap 영역에 저장된다. 
// stack 영역에는 heap의 주소를 가지고 있다. 
// stack은 후입 선출방식이고, heap은 선입 선출 방식을 이용한다. 

import java.util.Scanner;

public class If_else {// 조건문과 반복문 

	public static void main(String[] args) {
		String s1 = "ABC";//string은 한번 값이 저장되면 바꿀 수 없다. 
		//-> 값이 바꾸기 위해서는 다시 공간을 할당해야 한다. 
		String s2 = "ABC";
		String s3 = s1.toLowerCase();

		System.out.println(s3);


		char c3 = s3.charAt(0);
		System.out.println("s3.charAt(0) = " + c3);

		if(s1 == s1) {// 이렇게 하면 string의 값을 비교하는 것이 아닌 주소를 비교하는 것이다. 
			System.out.println("Equals Reference");
		}
		if(s1.equals(s2)) {//equals 는 값을 비교한다. -> string일 경우 equals를 이용해야 한다. 
			System.out.println("Equals String.. ");
		}


		String s4 = "ABC";
		String s5 = new String("ABC");		
		String s6 = "ABC";
		String s7 = new String("ABC");


		if(s4 == s6) {//같은 주소를 가진다.
			System.out.println("같은 값일때 new를 사용하지 않으면 같은 주소를 가리킨다. ");

		}
		if(s4.equals(s5)) {// 값을 비교 
			System.out.println("new 로 생성하면 다른 주소를 가진다.");
		}
		//		System.out.println(s4.hashCode());
		//		System.out.println(s6.hashCode());
		//		System.out.println(s5.hashCode());

		System.out.println("new로 생성한 String s5 : " + System.identityHashCode(s5));		
		System.out.println("그냥 생성한 String s4 : " +System.identityHashCode(s4));// 메모리 주소가 10진수로 출력된다. 
		System.out.println("그냥 생성한 String s6 : " +System.identityHashCode(s6));		

		Scanner input = new Scanner(System.in);
		System.out.println("숫자를 입력하세요 ");
		String str = input.next();// int일 경우는 input.nextInt()
		System.out.println("입력받은 숫자는 " + str + "\n");

//		input.nextLine();// 앞에서 입력받은 enter를 제거하기 위해서 
//		System.out.println("한줄을 입력받습니다. 공백도 읽어옵니다. ");
//		String str1 = input.nextLine();
//		System.out.println("입력한 한줄은 " + str1 );

		int input_num = Integer.parseInt(str);
		
		String level = (input_num >= 7) ? "대" : (input_num >= 3 ? "중" : "소");
		System.out.println(level);
		
		System.out.println("첫번째 숫자를 입력하세요 ");
		String n1 = input.next();// int일 경우는 input.nextInt()
		System.out.println("두번째 숫자를 입력하세요 ");
		String n2 = input.next();// int일 경우는 input.nextInt()

		if(n1.length() > 1 || n2.length() > 1) {
			System.out.println("입력받은 숫자 중 한가지 이상은 한자리 수보다 큽니다. ");
		}else {
			System.out.println("입력받은 두 숫자는 한자리 수입니다. ");
		}
		
		//숫자만 입력 받는다. 
		char c1 = n1.charAt(0);
		char c2 = n2.charAt(0);
		
//		if( (c1 >= '1' && c1 <= '9') && (c2 >= '1' && c2 <= '9') ) {
		if( !(c1 >= '1' && c1 <= '9') || !(c2 >= '1' && c2 <= '9') ) {
			System.out.println("입력받은 두 값은 숫자가 아닙니다. ");
		}else {
			System.out.println("입력받은 두 값은 숫자입니다. ");
		}
		
		
		// 계산기 
		double num1 = 0;
		double num2 = 0;
		double result = 0;
		String calc;
		
		System.out.println("숫자 2개를 입력해 주세요");
		num1 = input.nextDouble();
		num2 = input.nextDouble();
		
		
		
		do {
			
			System.out.println("연산자 +,-,/,* 중 하나를 입력하세요 ");
			calc = input.next();
			
			if((calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*"))) {
				break;
			}
			
		}while( !(calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*")));
			
		
		String a1 = "" + num1 + calc + num2;

		
		

		input.close();

	}

}
