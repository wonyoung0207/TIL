package day07;

import java.util.Scanner;

public class bank {

	public static void main(String[] args) {
		person p1;
		String p_name;
		double p_deposit;
		String p_pw;
		String p_accNum;
		
		Scanner input = new Scanner(System.in);
		Account acc = new Account();
		String num;


		while(true) {
			System.out.println("���� ������ �����մϴ�. �̸�, �ݾ�, ��й�ȣ, ���¹�ȣ ������ �Է��� �ּ���.");
			p_name = input.next();
			p_deposit = Double.parseDouble(input.next());
			p_pw = input.next();
			p_accNum = input.next();
			
			p1 = new person(p_name, p_deposit, p_pw, p_accNum);
			
			if(p1.userinfo_checkout()) {
				System.out.println("���°� ���󰳼� �Ǿ����ϴ�. ");
				break;
			}else {
				System.out.println("������ ��й�ȣ�� ���¹�ȣ�� �߸������Ǿ����ϴ�. ");
			}

		}

		while(true) {//����� 
			System.out.println("���¿��� ������ ���� �����ΰ���? ��ȣ�� �����ϼ���.(1.�Ա� , 2.���, 3.����)");
			num = input.next();

			if(num.equals("1")) {//�� �Ա�
				System.out.println("�Ա��� ���� �Է��ϼ���. ");
				num = input.next();
				acc.deposit(p1, Double.parseDouble(num));

			}else if(num.equals("2")) {
				System.out.println("����� ���� �Է��ϼ���. ");
				num = input.next();
				acc.withdraw(p1, Double.parseDouble(num));

			}else if(num.equals("3")) {
				System.out.println("���� �ý����� �����մϴ�. ");
				break;
			}else {
				System.out.println("��ȣ�� �߸��Է��߽��ϴ�. �ٽ� �Է����ּ���. ");
			}

			System.out.printf("���� ���´� %s�� %s �̰�, ����ݾ��� %.1f �Դϴ�. \n", p1.getAccNum(),p1.getUserName(), p1.getBalance());


		}




	}

}
