package day11;

public class UpdateApp {

	public static void main(String[] args) {
		DAO dao = new CustomerDAO();
		
		CustomerVO c1 = new CustomerVO("id01", "pwd01", "lee");
		try {
			dao.update(new CustomerVO("id02","pwd01", "lee"));
			
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(c1);
	}

}
