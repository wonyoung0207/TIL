package day10;

import java.util.ArrayList;

public class OracleDAO {
	//CustomerVO ������ �����͸� �޾ƿ� ������ Oracle �����ͺ��̽��� ������. 
	// OracleDAO �� ��ü�� oracle �����ͺ��̽��� ��������ִ� ������ �Ѵ�.
	
	public OracleDAO() {
		
	}

	public void connect() {//db�� ���� 
		System.out.println("Oracle Server connected..."); 
		
	}
	public void close() {
		System.out.println("Oracle Server Close..."); 
		
	}
	
	public void insert(CustomerVO c) {
		connect();
		
		System.out.println(c);
		System.out.println("Inserted...");
		close();
	}
	
	public void delete(String id) {
		connect();
		System.out.println(id);
		System.out.println("Deleted...");
		close();
	}
	public CustomerVO selet(String id) {
		connect();
		String pwd = "pwd01";
		String name = "james";
		CustomerVO c = new CustomerVO(id,pwd, name);
		close();
		
		return c;
	}
	
	public ArrayList<CustomerVO> select(){
		ArrayList<CustomerVO> list = new ArrayList<>();
		//�ڵ����� ũ�Ⱑ �����ȴ�. 
		list.add(new CustomerVO("id01", "pwd01", "jasmin1"));
		list.add(new CustomerVO("id02", "pwd02", "jasmin2"));
		list.add(new CustomerVO("id03", "pwd03", "jasmin3"));
		
		return list;
		
	}
	
	
	
}
