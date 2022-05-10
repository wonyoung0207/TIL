package day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class OracleDAO2 implements DAO{
	//CustomerVO 형태의 데이터를 받아와 정제후 Oracle 데이터베이스에 보낸다. 
	// OracleDAO 는 객체와 oracle 데이터베이스를 연결시켜주는 역할을 한다.
	
	HashMap<String, CustomerVO> map;


	public void insert(CustomerVO c) {
		connect();
		String key = c.getId();
		map.put(key, c);
		
		close();
	}

	@Override
	public void delete(String id) {
		connect();
		map.remove(id);

		close();
	}

	@Override
	public CustomerVO select(String id) {
		connect();
		CustomerVO c = null;
		c = map.get(id);
		

		close();
		return null;
	}

	
	public ArrayList<CustomerVO> select(){
		connect();
		Collection<CustomerVO> col = map.values();
		Iterator<CustomerVO> it = col.iterator();
		ArrayList<CustomerVO> list = new ArrayList<>();
		
		while(it.hasNext()) {
			CustomerVO has_c = it.next();
			list.add(has_c);
		}

		
		close();
		
		return list;
		
	}

	@Override
	public void update(CustomerVO c) {
		
	}

	
	
	
}
