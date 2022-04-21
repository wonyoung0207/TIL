package day11;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerDAO implements DAO {

	HashMap<String, CustomerVO> map = new HashMap<>();

	public CustomerDAO() {
		map = new HashMap<String, CustomerVO>();
	}

	@Override
	public void insert(CustomerVO c) throws DuplicatedIDException {
		String id = c.getId();
		if(map.containsKey(id)){
			//id�� �ִ��� Ȯ��
			throw new DuplicatedIDException("ID �ߺ�!");
		}
		 
		
		map.put(id, c);
	}

	@Override
	public void delete(String c) throws NotFoundException {
		if(!map.containsKey(c)) {
			throw new NotFoundException("���̵� ã�� �� �����ϴ�. ");
		}
		
		map.remove(c);
		
	}

	@Override
	public void update(CustomerVO c) throws NotFoundException {
		String id = c.getId();
		
		if(!map.containsKey(id)) {
			throw new NotFoundException("���̵� ã�� �� �����ϴ�. ");
		}
		
		map.put(id, c);
	}

	@Override
	public CustomerVO select(String id) throws NotFoundException {
		if(!map.containsKey(id)) {
			throw new NotFoundException("���̵� ã�� �� �����ϴ�. ");
		}
		
		
		return map.get(id);
	}

	@Override
	public ArrayList<CustomerVO> selectAll() throws NotFoundException {
		ArrayList<CustomerVO> list = new ArrayList<>();
		
		if(map.size() == 0) {//map�� ��������� ���� 
			throw new NotFoundException("�����Ͱ� ����־�� ");
			
		}
		for(String keys : map.keySet()) {
			list.add(map.get(keys));
			
		}
		
		return list;
	}

}
