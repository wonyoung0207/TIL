package day06;
//2���� �迭 
// ���� �̸��� �������� �� �迭 ���� => 4���� ��ü��� ġ��, ���� 4, ���� 2��, ���� �̸��� ������ ��Ÿ��
// ���� � ����� �Է�
// �� ���ϴ� ��պ��� �� �����ϴ�.
// �� �󸶰� ���Խ��ϴ�.

import java.util.Scanner;

public class ws01_fruit {
	public static void main(String[] args) {
		String arr[][] = {
				{"���", "1000"},
				{"�ٳ���", "2000"},
				{"��", "3000"},
				{"����", "3000"},
				{"����", "2000"}
		};
		
		
		Scanner input = new Scanner(System.in);
		
		int num = 0;//�� ���� �Է� 
		int my_money;
		int fruit_find_fail = 0;//0�̸� ã����, 1�̸� ��ã���� 
		int price = 0;// ���� ����
		String str;
		String fruit = null;
		
		
		while(true) {
			System.out.println("���� ������ �Է��� �ּ���. ");
			my_money = input.nextInt();
			if(my_money > 0) {
				break;
			}else {
				System.out.println("������ -�� �ƴմϴ�. �ٽ��Է����ּ���.");
			}
		}
		

		for(int i=0; i < arr.length; i++) {
			for(int j=0; j< 2; j=j+2) {
				
				int fruit_price = Integer.parseInt(arr[i][1]);//���� ������ �����ϴ� ���� 
				
				if( my_money >= fruit_price ) {//���� �ݾ����� ������ ���ִ� ���� ����Ʈ 
					int count =0;
					int a = my_money / fruit_price;//10
					int b = my_money % fruit_price;//10
					System.out.println("���� ���� "+ my_money + "���� " + arr[i][0] + "�� " + a + "�� ��� �ֽ��ϴ�. ");
				}
			}
		}

		while(true) {
			System.out.println("������ ������ �̸��� �Է��ϼ���. (���,�ٳ���,��,����,����)");
			str = input.next();
			
			for(int i=0; i< arr.length; i++) {
				if(arr[i][0].equals(str)) {
					fruit = arr[i][0];
					price = Integer.parseInt(arr[i][1]);
					break;
				}else {
					fruit = "fail";
					price = 0;
				}
			}
			
			if((my_money < price ) || (fruit.equals("fail"))) {
				System.out.println("�Է��� ������ ������ ���� ���꺸�� ��ΰų� ������ ã�� ���߽��ϴ�. �����մϴ�.");
				return ;
			}else {
				break;
			}
			
		}
		
		System.out.println("�Էµ� ���� ���� : " + fruit + "," + price);
		System.out.println(fruit + "���� " + my_money / price + "���� ��� �ֽ��ϴ�. �Ž������� " + my_money % price + " �Դϴ�. ");
		
	}
}
