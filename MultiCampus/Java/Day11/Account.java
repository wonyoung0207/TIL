package day11;

public class Account {
	private String id;
	private double balance;
	public Account(String id, double price) {
	}

	
	public void deposit(double money) throws MinusException {//입금 
		if(money < 1) {
			throw new MinusException("입금 금액 오류");//오류를 발생시킴 
		}
		
		this.balance += money;

	}
	
	public void withdraw(double money)throws MinusException, OverdrawnException {//출금 
		if(money < 1) {
			throw new MinusException("입금 금액 오류");//오류를 발생시킴 
		}
		if(this.balance < money) {
			throw new OverdrawnException("잔액 부족");
		}
		
		this.balance -= money;
		

		
	}

	

}
