package day10;

import java.util.ArrayList;

public class OracleDAO {
	//CustomerVO 형태의 데이터를 받아와 정제후 Oracle 데이터베이스에 보낸다. 
	// OracleDAO 는 객체와 oracle 데이터베이스를 연결시켜주는 역할을 한다.
	
	public OracleDAO() {
		
	}

	public void connect() {//db에 연결 
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
		//자동으로 크기가 조절된다. 
		list.add(new CustomerVO("id01", "pwd01", "jasmin1"));
		list.add(new CustomerVO("id02", "pwd02", "jasmin2"));
		list.add(new CustomerVO("id03", "pwd03", "jasmin3"));
		
		return list;
		
	}
	
	
	
}
