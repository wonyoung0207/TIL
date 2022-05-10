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
			System.out.println("계좌 개설을 시작합니다. 이름, 금액, 비밀번호, 계좌번호 순으로 입력해 주세요.");
			p_name = input.next();
			p_deposit = Double.parseDouble(input.next());
			p_pw = input.next();
			p_accNum = input.next();
			
			p1 = new person(p_name, p_deposit, p_pw, p_accNum);
			
			if(p1.userinfo_checkout()) {
				System.out.println("계좌가 정상개설 되었습니다. ");
				break;
			}else {
				System.out.println("계좌의 비밀번호나 계좌번호가 잘못설정되었습니다. ");
			}

		}

		while(true) {//입출금 
			System.out.println("계좌에서 진행할 일이 무엇인가요? 번호를 선택하세요.(1.입금 , 2.출금, 3.종료)");
			num = input.next();

			if(num.equals("1")) {//돈 입금
				System.out.println("입금할 돈을 입력하세요. ");
				num = input.next();
				acc.deposit(p1, Double.parseDouble(num));

			}else if(num.equals("2")) {
				System.out.println("출금할 돈을 입력하세요. ");
				num = input.next();
				acc.withdraw(p1, Double.parseDouble(num));

			}else if(num.equals("3")) {
				System.out.println("계좌 시스템을 종료합니다. ");
				break;
			}else {
				System.out.println("번호를 잘못입력했습니다. 다시 입력해주세요. ");
			}

			System.out.printf("현재 계좌는 %s의 %s 이고, 현재금액은 %.1f 입니다. \n", p1.getAccNum(),p1.getUserName(), p1.getBalance());


		}




	}

}
