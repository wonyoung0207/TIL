package day12_Oracle;

import java.util.ArrayList;

public class CustomerDAO implements DAO {

	@Override
	public void insert(Object obj) throws Exception {
		connect();
		
		CustomerVO c = (CustomerVO) obj;//타입캐스팅 해줘야 CustomerVO의 값들을 사용할 수있다. 
		System.out.println(c + "객체가 입력되었습니다. ");

		close();
	}

	@Override
	public void delete(Object obj) throws Exception {
		connect();
		
		String id = (String) obj;//타입캐스팅 해줘야 CustomerVO의 값들을 사용할 수있다. 
		System.out.println(id + "객체가 삭제되었습니다. ");

		close();
	}

	@Override
	public void update(Object obj) throws Exception {

	}

	@Override
	public Object select(Object obj) throws Exception {
		connect();
		
		String id = (String) obj;//타입캐스팅 해줘야 CustomerVO의 값들을 사용할 수있다.
		CustomerVO c = new CustomerVO(id,"pwd01","lee");
		

		close();
		
		return c;
	}

	@Override
	public ArrayList<Object> selectAll() throws Exception {
		connect();
		
		ArrayList<Object> list = new ArrayList<>();
		list.add(new CustomerVO("id01", "pwd01", "01"));
		list.add(new CustomerVO("id02", "pwd02", "02"));
		list.add(new CustomerVO("id03", "pwd03", "03"));
		
		
		close();
		return list;
	}

}
