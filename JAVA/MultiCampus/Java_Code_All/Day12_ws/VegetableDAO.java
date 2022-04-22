package day12_ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class VegetableDAO implements Ifruit<String, Vegetable>,Search<Vegetable>{

	HashMap<String, Vegetable> map;

	public VegetableDAO() {
		map = new HashMap<String, Vegetable>();

	}

	@Override
	public void create(Vegetable v) throws Exception {
		String key = v.getName();
		if(map.containsKey(key)) {
			throw new Exception("��ä�̸� �ߺ�");
		}
		map.put(key, v);
		System.out.println(v.getName() + "��ä �ڳʰ� �����Ǿ����ϴ�.");

	}

	@Override
	public Vegetable read(String k) throws Exception {
		Vegetable v = null;
		if(!map.containsKey(k)) {
			throw new Exception("���� ��ä�Դϴ�.");
		}
		v = map.get(k);
		return v;
	}

	@Override
	public void update(Vegetable v) throws Exception {
		if(!map.containsKey(v.getName())) {
			throw new Exception("���� ��ä�Դϴ�.");
		}
		map.put(v.getName(), v);		
	}

	@Override
	public void delete(String k) throws Exception {
		if(!map.containsKey(k)) {
			throw new Exception("���� ��ä�Դϴ�.");
		}
		for(String a : map.keySet()) {
			if(a.equals(k)) {//���� ������ �������� ������Ʈ ���� 
				map.remove(a);
				System.out.println(a + " ��ä�� �����߽��ϴ�. ");
			}
		}
	}

	@Override
	public ArrayList<Vegetable> search() throws Exception {
		ArrayList<Vegetable> list = new ArrayList<Vegetable>();
		if (map.size() ==0) {
			throw new Exception("�����Ͱ� �����ϴ�.");

		}
		Collection<Vegetable> col = map.values();
		Iterator<Vegetable> it = col.iterator();

		while(it.hasNext()) {
			Vegetable v = it.next();
			list.add(v);
		}
		return list;
	}

}
