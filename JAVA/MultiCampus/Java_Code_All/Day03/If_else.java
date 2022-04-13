package day03;
//���ڿ��� ��ü�� stack ������ �ƴ� heap ������ ����ȴ�. 
// stack �������� heap�� �ּҸ� ������ �ִ�. 
// stack�� ���� �������̰�, heap�� ���� ���� ����� �̿��Ѵ�. 

import java.util.Scanner;

public class If_else {// ���ǹ��� �ݺ��� 

	public static void main(String[] args) {
		String s1 = "ABC";//string�� �ѹ� ���� ����Ǹ� �ٲ� �� ����. 
		//-> ���� �ٲٱ� ���ؼ��� �ٽ� ������ �Ҵ��ؾ� �Ѵ�. 
		String s2 = "ABC";
		String s3 = s1.toLowerCase();

		System.out.println(s3);


		char c3 = s3.charAt(0);
		System.out.println("s3.charAt(0) = " + c3);

		if(s1 == s1) {// �̷��� �ϸ� string�� ���� ���ϴ� ���� �ƴ� �ּҸ� ���ϴ� ���̴�. 
			System.out.println("Equals Reference");
		}
		if(s1.equals(s2)) {//equals �� ���� ���Ѵ�. -> string�� ��� equals�� �̿��ؾ� �Ѵ�. 
			System.out.println("Equals String.. ");
		}


		String s4 = "ABC";
		String s5 = new String("ABC");		
		String s6 = "ABC";
		String s7 = new String("ABC");


		if(s4 == s6) {//���� �ּҸ� ������.
			System.out.println("���� ���϶� new�� ������� ������ ���� �ּҸ� ����Ų��. ");

		}
		if(s4.equals(s5)) {// ���� �� 
			System.out.println("new �� �����ϸ� �ٸ� �ּҸ� ������.");
		}
		//		System.out.println(s4.hashCode());
		//		System.out.println(s6.hashCode());
		//		System.out.println(s5.hashCode());

		System.out.println("new�� ������ String s5 : " + System.identityHashCode(s5));		
		System.out.println("�׳� ������ String s4 : " +System.identityHashCode(s4));// �޸� �ּҰ� 10������ ��µȴ�. 
		System.out.println("�׳� ������ String s6 : " +System.identityHashCode(s6));		

		Scanner input = new Scanner(System.in);
		System.out.println("���ڸ� �Է��ϼ��� ");
		String str = input.next();// int�� ���� input.nextInt()
		System.out.println("�Է¹��� ���ڴ� " + str + "\n");

//		input.nextLine();// �տ��� �Է¹��� enter�� �����ϱ� ���ؼ� 
//		System.out.println("������ �Է¹޽��ϴ�. ���鵵 �о�ɴϴ�. ");
//		String str1 = input.nextLine();
//		System.out.println("�Է��� ������ " + str1 );

		int input_num = Integer.parseInt(str);
		
		String level = (input_num >= 7) ? "��" : (input_num >= 3 ? "��" : "��");
		System.out.println(level);
		
		System.out.println("ù��° ���ڸ� �Է��ϼ��� ");
		String n1 = input.next();// int�� ���� input.nextInt()
		System.out.println("�ι�° ���ڸ� �Է��ϼ��� ");
		String n2 = input.next();// int�� ���� input.nextInt()

		if(n1.length() > 1 || n2.length() > 1) {
			System.out.println("�Է¹��� ���� �� �Ѱ��� �̻��� ���ڸ� ������ Ů�ϴ�. ");
		}else {
			System.out.println("�Է¹��� �� ���ڴ� ���ڸ� ���Դϴ�. ");
		}
		
		//���ڸ� �Է� �޴´�. 
		char c1 = n1.charAt(0);
		char c2 = n2.charAt(0);
		
//		if( (c1 >= '1' && c1 <= '9') && (c2 >= '1' && c2 <= '9') ) {
		if( !(c1 >= '1' && c1 <= '9') || !(c2 >= '1' && c2 <= '9') ) {
			System.out.println("�Է¹��� �� ���� ���ڰ� �ƴմϴ�. ");
		}else {
			System.out.println("�Է¹��� �� ���� �����Դϴ�. ");
		}
		
		
		// ���� 
		double num1 = 0;
		double num2 = 0;
		double result = 0;
		String calc;
		
		System.out.println("���� 2���� �Է��� �ּ���");
		num1 = input.nextDouble();
		num2 = input.nextDouble();
		
		
		
		do {
			
			System.out.println("������ +,-,/,* �� �ϳ��� �Է��ϼ��� ");
			calc = input.next();
			
			if((calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*"))) {
				break;
			}
			
		}while( !(calc.equals("+") || calc.equals("-") || calc.equals("/") || calc.equals("*")));
			
		
		String a1 = "" + num1 + calc + num2;

		
		

		input.close();

	}

}
