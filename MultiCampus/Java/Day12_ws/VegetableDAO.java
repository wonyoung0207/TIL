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
			throw new Exception("야채이름 중복");
		}
		map.put(key, v);
		System.out.println(v.getName() + "야채 코너가 생성되었습니다.");

	}

	@Override
	public Vegetable read(String k) throws Exception {
		Vegetable v = null;
		if(!map.containsKey(k)) {
			throw new Exception("없는 야채입니다.");
		}
		v = map.get(k);
		return v;
	}

	@Override
	public void update(Vegetable v) throws Exception {
		if(!map.containsKey(v.getName())) {
			throw new Exception("없는 야채입니다.");
		}
		map.put(v.getName(), v);		
	}

	@Override
	public void delete(String k) throws Exception {
		if(!map.containsKey(k)) {
			throw new Exception("없는 야채입니다.");
		}
		for(String a : map.keySet()) {
			if(a.equals(k)) {//같은 과일이 있을때만 업데이트 가능 
				map.remove(a);
				System.out.println(a + " 야채를 삭제했습니다. ");
			}
		}
	}

	@Override
	public ArrayList<Vegetable> search() throws Exception {
		ArrayList<Vegetable> list = new ArrayList<Vegetable>();
		if (map.size() ==0) {
			throw new Exception("데이터가 없습니다.");

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
