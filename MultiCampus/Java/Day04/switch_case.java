package day04;

import java.util.Random;

public class switch_case {

	public static void main(String[] args) {
		int a = 0;
		switch (a) {
		case 10://10 �ڸ����� ���ǹ��� ����� �� ����. 
			System.out.println("ū��");
			break;
		case 5:
			System.out.println(("�߰���"));
			break;
		default :
			System.out.println("������");

		}
		System.out.println('��');
		
		
		Random r = new Random();
		int n = r.nextInt(3);// 0~2������ �� 
		
		System.out.println("���� �߻� �� : " + n);
		
		switch (n) {
		case 0://10 �ڸ����� ���ǹ��� ����� �� ����. 
			System.out.println("0�� �Դϴ�.");
			break;
		case 1:
			System.out.println(("1�� �Դϴ�."));
			break;
		case 2:
			System.out.println(("2�� �Դϴ�."));
			break;
		default:
			System.out.println("�߸��Է�");

		}
		

	}

	
	

}
