package day08;

public class Account {
	private String AccNo;
	private double money;
	
	public Account(String AccNo, double money) {
		this.AccNo = AccNo;
		this.money = money;
		
	}

	public String getAccNo() {
		return AccNo;
	}

	public void setName(String AccNo) {
		this.AccNo = AccNo;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Account [AccNo=" + AccNo + ", money=" + money + "]";
	}
	
	

}
