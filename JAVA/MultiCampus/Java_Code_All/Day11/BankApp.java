package day11;

public class BankApp {

	public static void main(String[] args) {
		Account acc = new Account("1111", 10000);
		
		try {
			acc.deposit(-100);
			
		}catch(MinusException e){
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			
			acc.withdraw(1000);
		}catch(MinusException | OverdrawnException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(acc);
		
	}

}
