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
			System.out.println("������ ���°� �ƴմϴ�. ");
		}catch(ArithmeticException e) {
			System.out.println("�и� 0 �Դϴ�. ");
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println(e.toString());
			e.printStackTrace();//������ ��� �߻��ߴ��� ��ü������ �˷��� 

		}finally {//���������� �귯���ų� ���� �߻��� ������ �� , ���� �׻� ����Ǵ� ���� 
			
		}

		System.out.println(result);
		
		

	}

}
