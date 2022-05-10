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
			//id가 있는지 확인
			throw new DuplicatedIDException("ID 중복!");
		}
		 
		
		map.put(id, c);
	}

	@Override
	public void delete(String c) throws NotFoundException {
		if(!map.containsKey(c)) {
			throw new NotFoundException("아이디를 찾을 수 없습니다. ");
		}
		
		map.remove(c);
		
	}

	@Override
	public void update(CustomerVO c) throws NotFoundException {
		String id = c.getId();
		
		if(!map.containsKey(id)) {
			throw new NotFoundException("아이디를 찾을 수 없습니다. ");
		}
		
		map.put(id, c);
	}

	@Override
	public CustomerVO select(String id) throws NotFoundException {
		if(!map.containsKey(id)) {
			throw new NotFoundException("아이디를 찾을 수 없습니다. ");
		}
		
		
		return map.get(id);
	}

	@Override
	public ArrayList<CustomerVO> selectAll() throws NotFoundException {
		ArrayList<CustomerVO> list = new ArrayList<>();
		
		if(map.size() == 0) {//map이 비어있으면 에러 
			throw new NotFoundException("데이터가 비어있어요 ");
			
		}
		for(String keys : map.keySet()) {
			list.add(map.get(keys));
			
		}
		
		return list;
	}

}
