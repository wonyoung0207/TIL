package day12_Oracle2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import day11.CustomerVO;
import day11.NotFoundException;

public class CustomerDAO implements DAO<String, CustomerVO> {
	
	HashMap<String, CustomerVO> map;
	
	public CustomerDAO() {
		map = new HashMap<String, CustomerVO>();
		
	}

	@Override
	public void insert(CustomerVO v) throws Exception {
		
		if(map.containsKey(v.getId())) {//id�� �����ϸ� ���� �߻� 
			throw new Exception("ID�ߺ� ");
		}
		map.put(v.getId(), v);
		
		
	}

	@Override
	public void delete(String k) throws Exception {
		
		if(!map.containsKey(k)) {
			throw new Exception("ID�� ���� ");
		}
		map.remove(k);
		
		
		
	}

	@Override
	public void update(CustomerVO v) throws Exception {
		if(!map.containsKey(v.getId())) {
			throw new Exception("ID�� ����");
			
		}
		
		map.put(v.getId(), v);

	}

	@Override
	public CustomerVO select(String k) throws Exception {
		CustomerVO c = null;
		
		if(!map.containsKey(k)) {
			throw new Exception("ID�� ����");
			
		}
		c = map.get(k);
		
		return c;
	}

	@Override
	public List<CustomerVO> selectAll() throws Exception {
		ArrayList<CustomerVO> list = new ArrayList<>();
		
		if(map.size() == 0) {//map�� ��������� ���� 
			throw new Exception("�����Ͱ� ����־�� ");
			
		}
		for(String keys : map.keySet()) {
			list.add(map.get(keys));
			
		}
		
		return list;
	}

}
