package day08;

import java.util.Arrays;

public class Customer {
	private String name;
	private Account[] accs;
	public Customer(String name) {
		this.name = name;
	}
	public Customer(String name, Account[] accs) {
		this.name = name;
		this.accs = accs;
		
	}
	
	public String getName() {
		return name;
	}
	
	public Account[] getAccs() {
		return accs;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAccs(Account[] accs) {
		this.accs = accs;
	}
	
	public Account getAc(String accNo) {//계좌번호 맞는지 비교 
		Account acc = null;
		
		for(int i=0; i< accs.length; i++) {
			if(accs[i].getAccNo().equals(accNo)) {//계좌번호가 같으면 넣어준다. 
				acc = accs[i];
			}
		}
		return acc;
	}
	
	public void deposit(String accNo, double money) {//계좌번호와 입금할 돈을 넣어주면 해당 계좌에 입금 
		for(int i = 0; i<accs.length; i++) {
			if(accs[i].getAccNo().equals(accNo)) {
				accs[i].setMoney(accs[i].getMoney() + money);
			}
		}
		
	}
	
	public double getBalanceSum() {
		double sum = 0.0;
		for(int i = 0; i<accs.length; i++) {
			sum += accs[i].getMoney();
		}
		
		return sum;
		
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", accs=" + Arrays.toString(accs) + "]";
	}
}
