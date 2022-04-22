package day12_ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class FruitDAO implements Ifruit<String, fruit>,Search<fruit>{

	HashMap<String, fruit> map;
	public FruitDAO() {
		map = new HashMap<String, fruit>();
	}
	
	//�̸��� ���� ������ ���ڰ� ���´� ����
	public void create(fruit f) throws Exception {
		String key = f.getName();
		if(map.containsKey(key)) {
			throw new Exception("�����̸� �ߺ�");
		}
		map.put(key, f);
		System.out.println(f.getName() + "���� �ڳʰ� �����Ǿ����ϴ�.");
	}

	
	//���ڰ� �ƴѰ� �Է� �޾�����	
	//�����Ͱ� �����ϴ�.
	public fruit read(String id) throws Exception{
		fruit f = null;
		if(!map.containsKey(id)) {
			throw new Exception("���� �����Դϴ�.");
		}
		f = map.get(id);
		return f;
	}
	
	//���ڰ� �ƴѰ� �Է� �޾�����	
	//ã�� �����Ͱ� �����ϴ�.
	public void update(fruit f) throws Exception{
		if(!map.containsKey(f.getName())) {
			throw new Exception("���� �����Դϴ�.");
		}
		map.put(f.getName(), f);
	}
	
	//���ڰ� �ƴѰ� �Է� �޾�����	
	//ã�� �����Ͱ� �����ϴ�.
	public void delete(String id) throws Exception {
		if(!map.containsKey(id)) {
			throw new Exception("���� �����Դϴ�.");
		}
		for(String a : map.keySet()) {
			if(a.equals(id)) {//���� ������ �������� ������Ʈ ���� 
				map.remove(a);
				System.out.println(a + " ������ �����߽��ϴ�. ");
			}
		}
	}


	//search	
	//�����Ͱ� �����ϴ�.
	public ArrayList<fruit> search() throws Exception {
		ArrayList<fruit> list = new ArrayList<fruit>();
		if (map.size() ==0) {
			throw new Exception("�����Ͱ� �����ϴ�.");

		}
		Collection<fruit> col = map.values();
		Iterator<fruit> it = col.iterator();

		while(it.hasNext()) {
			fruit fr = it.next();
			list.add(fr);
		}
		return list;
	}




}
