package day07;

public class person {
	
	private String userName;// 이름 
	private double balance;// 내가 가지고 있는 금액 
	private String userPw;//계좌 비밀번호
	private String accNum;//계좌번호
	
	
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
		if(accNum.length() != 8) {//계좌번호가 8자리가 아니면 오류 
			System.out.println("계좌번호를 다시 설정하세요. ");
			return false;
		}
		if(userPw.length() != 4) {// 계좌 비밀번호가 4자리 아니면 오류
			System.out.println("계좌 비밀번호를 4자리로 설정해 주세요. ");
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
