package day04;

import java.util.Random;

public class switch_case {

	public static void main(String[] args) {
		int a = 0;
		switch (a) {
		case 10://10 자리에는 조건문을 사용할 수 없다. 
			System.out.println("큰수");
			break;
		case 5:
			System.out.println(("중간수"));
			break;
		default :
			System.out.println("작은수");

		}
		System.out.println('끝');
		
		
		Random r = new Random();
		int n = r.nextInt(3);// 0~2사이의 값 
		
		System.out.println("난수 발생 값 : " + n);
		
		switch (n) {
		case 0://10 자리에는 조건문을 사용할 수 없다. 
			System.out.println("0등 입니다.");
			break;
		case 1:
			System.out.println(("1등 입니다."));
			break;
		case 2:
			System.out.println(("2등 입니다."));
			break;
		default:
			System.out.println("잘못입력");

		}
		

	}

	
	

}
