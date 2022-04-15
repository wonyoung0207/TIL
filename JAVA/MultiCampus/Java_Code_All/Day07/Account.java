package day07;

public class Account {

	public Account() {
	}

	
	public void deposit(person p, double money) {//입금 
		if(money < 1) {
			System.out.println("입금 금액 오류 ");
		}
		else {
			p.setBalance(p.getBalance() + money);
			System.out.println(money + "원 입금이 완료되었습니다. ");
			
		}
	}
	
	public void withdraw(person p,double money) {//출금 
		if(p.getBalance() < money) {
			System.out.println("가지고 있는 돈이 부족합니다. 출금을 취소합니다. ");
			
		}

		else {
			p.setBalance(p.getBalance() - money);
		}
		
	}

	

}
