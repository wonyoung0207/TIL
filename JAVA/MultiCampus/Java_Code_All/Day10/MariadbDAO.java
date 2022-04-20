package day10;

import java.util.ArrayList;

public class MariadbDAO implements DAO{

	public void insert(CustomerVO c) {
		connect();
		
		System.out.println(c);
		System.out.println("Mariadb Inserted...");
		close();
	}

	@Override
	public void delete(String c) {
		connect();
		System.out.println("Mariadb delete...");
		close();
	}

	@Override
	public CustomerVO select(String id) {
		connect();

		System.out.println("Mariadb select...");
		close();
		return null;
	}

	public ArrayList<CustomerVO> select(){
		connect();
		System.out.println("Mariadb List selecdt...");
		
		ArrayList<CustomerVO> list = new ArrayList<>();
		//자동으로 크기가 조절된다. 
		list.add(new CustomerVO("id01", "pwd01", "jasmin1"));
		list.add(new CustomerVO("id02", "pwd02", "jasmin2"));
		list.add(new CustomerVO("id03", "pwd03", "jasmin3"));
		
		close();
		
		return list;
		
	}

	@Override
	public void update(CustomerVO c) {
		// TODO Auto-generated method stub
		
	}
}
