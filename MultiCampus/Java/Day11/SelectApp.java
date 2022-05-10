package day11;

import java.util.ArrayList;

public class SelectApp {

	public static void main(String[] args) {

		DAO dao = new CustomerDAO();
		
//		CustomerVO c1 = new CustomerVO("id01", "pwd01", "lee");
//		
//		try {
//			dao.insert(c1);
//			
//		}catch(DuplicatedIDException e) {
//			System.out.println(e.getMessage());
//		}
//		
		
		
		try {
			System.out.println(dao.select("id02"));
			
			
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		try {
			ArrayList<CustomerVO> list = dao.selectAll();
			for(CustomerVO a : list) {
				System.out.println("1231" + a.toString());
			}
			
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
