package day07;

public class Account {

	public Account() {
	}

	
	public void deposit(person p, double money) {//�Ա� 
		if(money < 1) {
			System.out.println("�Ա� �ݾ� ���� ");
		}
		else {
			p.setBalance(p.getBalance() + money);
			System.out.println(money + "�� �Ա��� �Ϸ�Ǿ����ϴ�. ");
			
		}
	}
	
	public void withdraw(person p,double money) {//��� 
		if(p.getBalance() < money) {
			System.out.println("������ �ִ� ���� �����մϴ�. ����� ����մϴ�. ");
			
		}

		else {
			p.setBalance(p.getBalance() - money);
		}
		
	}

	

}
