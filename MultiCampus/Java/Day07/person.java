package day07;

public class person {
	
	private String userName;// �̸� 
	private double balance;// ���� ������ �ִ� �ݾ� 
	private String userPw;//���� ��й�ȣ
	private String accNum;//���¹�ȣ
	
	
	public person() {
		this.userName = "Unknown";
		this.balance = 0;
		this.userPw = "0000";
		this.accNum = "00000000";
	}
	
	public person(String username, double balance, String userPw, String accNum) {
		this.userName = username;
		this.balance = balance;
		this.userPw = userPw;
		this.accNum = accNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	
	public boolean userinfo_checkout() {
		if(accNum.length() != 8) {//���¹�ȣ�� 8�ڸ��� �ƴϸ� ���� 
			System.out.println("���¹�ȣ�� �ٽ� �����ϼ���. ");
			return false;
		}
		if(userPw.length() != 4) {// ���� ��й�ȣ�� 4�ڸ� �ƴϸ� ����
			System.out.println("���� ��й�ȣ�� 4�ڸ��� ������ �ּ���. ");
			return false;
		}
		return true;
		
	}


	@Override
	public String toString() {
		return "person [userName=" + userName + ", balance=" + balance + ", userPw=" + userPw + ", accNum=" + accNum
				+ "]";
	}
	
	
	
	

}
