package day11;

public class Account {
	private String id;
	private double balance;
	public Account(String id, double price) {
	}

	
	public void deposit(double money) throws MinusException {//�Ա� 
		if(money < 1) {
			throw new MinusException("�Ա� �ݾ� ����");//������ �߻���Ŵ 
		}
		
		this.balance += money;

	}
	
	public void withdraw(double money)throws MinusException, OverdrawnException {//��� 
		if(money < 1) {
			throw new MinusException("�Ա� �ݾ� ����");//������ �߻���Ŵ 
		}
		if(this.balance < money) {
			throw new OverdrawnException("�ܾ� ����");
		}
		
		this.balance -= money;
		

		
	}

	

}
