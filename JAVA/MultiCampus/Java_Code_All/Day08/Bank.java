package day08;

public class Bank {

	public static void main(String[] args) {
		Customer c = new Customer("Kim");
		System.out.println(c);
		
		Account[] accs = new Account[3];
		accs[0] = new Account("1111", 10000);
		accs[1] = new Account("2222", 20000);
		accs[2] = new Account("3333", 30000);
		
		c.setAccs(accs);
//		System.out.println(c);
		
		Account myacc = c.getAc("2222");//계좌번호가 2222인것을 리턴받음 
		System.out.println(myacc);
		
		c.deposit("2222",10000);
		System.out.println(myacc);
		
		System.out.println("Account All money sum : "+c.getBalanceSum());
		
		
		
	}

}
