package day05;

import java.util.Scanner;

public class Product_select_withdraw_deposit {
	public static void main(String[] args) {
		String product;
		double balance = 100000;

		Scanner input = new Scanner(System.in);

		while(true) {
			try {
				System.out.println("어떤 일을 하시겠어요? ( q:종료, d: Deposit, w: withdraw, s: Select, i: 이자율)");
				String work = input.next();
				work = work.toLowerCase();//대문자를 소문자로 만듬 


				if(work.equals("q")) {
					System.out.println("Bye");
					break;
				}else if(work.equals("d")) {//입금
					System.out.println("Deposit..");
					System.out.println("Amount : ");
					String snum = input.next();
					System.out.println(snum);
					int amount = Integer.parseInt(snum);
					if(amount < 0) {
						System.out.println("음수입니다. 다시 실행해주세요.");
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
						System.out.println("음수입니다. 다시 실행해주세요.");
						continue;

					}
					if(amount > balance) {
						System.out.println(" 금액이 부족합니다. ");
						continue;

					}
					balance -= amount;
					System.out.printf("Balance : %.2f \n ", balance);

				}else if(work.equals("s")) {
					System.out.println("select Balence..");
					System.out.printf("Balance : %.2f \n ", balance);
				}else if(work.equals("i")) {
					//이자율을 입력하면 이자가 얼마인지를 출력
					// 원금과 이자 이자율을 같이 출력
					System.out.println("이자율을 입력하세요");
					String sinterest = input.next();
					double interestRate = Double.parseDouble(sinterest);
					double interest = balance * (interestRate / 100);
					System.out.println("이자율은 " + interestRate + "%, 이자는 " + interest + "입니다. ");

				}

			}catch(Exception e) {
				System.out.println("잘못된 입력입니다. 글자 q,d,w,s를 입력하세요.");

			}
		}
		input.close();
	}
}
