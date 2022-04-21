package day11;

import java.util.ArrayList;

public class DeleteApp {

	public static void main(String[] args) {
		
		DAO dao = new CustomerDAO();
		
		try {
			dao.delete("id02");
			
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
