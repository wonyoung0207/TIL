package day12_Oracle2;

public class App {

	public static void main(String[] args) {
		DAO i = new ItemDAO();
		
		try {
			i.insert(new ItemVO(100,"pants", 10000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		

	}

}
