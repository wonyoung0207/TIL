package day12_Oracle;

import java.util.ArrayList;

public class CustomerTestApp {

	public static void main(String[] args) {
		DAO dao = new CustomerDAO();
		
		CustomerVO c = new CustomerVO("id01", "pwd01", "lee");
		try {
			dao.insert(c);
			ArrayList<Object> list = dao.selectAll();
			for(Object obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
