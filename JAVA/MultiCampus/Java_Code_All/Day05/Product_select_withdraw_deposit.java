package day05;

import java.util.Scanner;

public class Product_select_withdraw_deposit {
	public static void main(String[] args) {
		String product;
		double balance = 100000;

		Scanner input = new Scanner(System.in);

		while(true) {
			try {
				System.out.println("� ���� �Ͻðھ��? ( q:����, d: Deposit, w: withdraw, s: Select, i: ������)");
				String work = input.next();
				work = work.toLowerCase();//�빮�ڸ� �ҹ��ڷ� ���� 


				if(work.equals("q")) {
					System.out.println("Bye");
					break;
				}else if(work.equals("d")) {//�Ա�
					System.out.println("Deposit..");
					System.out.println("Amount : ");
					String snum = input.next();
					System.out.println(snum);
					int amount = Integer.parseInt(snum);
					if(amount < 0) {
						System.out.println("�����Դϴ�. �ٽ� �������ּ���.");
						continue;

					}
					balance += amount;
					System.out.printf("Balance : %.2f \n ", balance);


				}else if(work.equals("w")) {
					System.out.println("Withdraw..");
					System.out.println("Amount : ");
					String snum = input.next();
					System.out.println(snum);
					int amount = Integer.parseInt(snum);
					if(amount < 0) {
						System.out.println("�����Դϴ�. �ٽ� �������ּ���.");
						continue;

					}
					if(amount > balance) {
						System.out.println(" �ݾ��� �����մϴ�. ");
						continue;

					}
					balance -= amount;
					System.out.printf("Balance : %.2f \n ", balance);

				}else if(work.equals("s")) {
					System.out.println("select Balence..");
					System.out.printf("Balance : %.2f \n ", balance);
				}else if(work.equals("i")) {
					//�������� �Է��ϸ� ���ڰ� �������� ���
					// ���ݰ� ���� �������� ���� ���
					System.out.println("�������� �Է��ϼ���");
					String sinterest = input.next();
					double interestRate = Double.parseDouble(sinterest);
					double interest = balance * (interestRate / 100);
					System.out.println("�������� " + interestRate + "%, ���ڴ� " + interest + "�Դϴ�. ");

				}

			}catch(Exception e) {
				System.out.println("�߸��� �Է��Դϴ�. ���� q,d,w,s�� �Է��ϼ���.");

			}
		}
		input.close();
	}
}
