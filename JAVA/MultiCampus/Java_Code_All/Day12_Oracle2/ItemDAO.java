package day12_Oracle2;

import java.util.HashMap;
import java.util.List;

public class ItemDAO implements DAO<Integer, ItemVO> {
	
	HashMap<Integer, ItemVO> map;
	
	public ItemDAO() {
		map = new HashMap<Integer, ItemVO>();
		
	}
	

	@Override
	public void insert(ItemVO v) throws Exception {
		if(map.containsKey(v.getId())) {
			throw new Exception("�̹� ID�� �����մϴ�. ");
			
		}
		
		map.put(v.getId(), v);

	}

	@Override
	public void delete(Integer k) throws Exception {
		if(!map.containsKey(k)) {
			throw new Exception("�ش� ID�� �������� �ʽ��ϴ� .");
		}
		map.remove(k);
		
	}

	@Override
	public void update(ItemVO v) throws Exception {
		
		if(!map.containsKey(v.getId())) {
			throw new Exception("�ش� ID�� �������� �ʽ��ϴ� .");
		}
		
		map.put(v.getId(), v);
		
	}

	@Override
	public ItemVO select(Integer k) throws Exception {
		ItemVO i = null;
		if(!map.containsKey(k)) {
			throw new Exception("�ش� ID�� �������� �ʽ��ϴ� .");
			
		}
		
		i = map.get(k);
		
		return i;
	}

	@Override
	public List<ItemVO> selectAll() throws Exception {
		List<ItemVO> list = null;
		
		if(map.size() == 0) {
			throw new Exception("�����Ͱ� �������� �ʽ��ϴ�. ");
		}
		
		for(Integer keys : map.keySet()) {
			list.add(map.get(keys));
		}
		
		return list;
	}

}
