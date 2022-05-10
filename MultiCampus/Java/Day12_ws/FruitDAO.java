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
	
	//이름은 문자 가격은 숫자고 상태는 문자
	public void create(fruit f) throws Exception {
		String key = f.getName();
		if(map.containsKey(key)) {
			throw new Exception("과일이름 중복");
		}
		map.put(key, f);
		System.out.println(f.getName() + "과일 코너가 생성되었습니다.");
	}

	
	//문자가 아닌걸 입력 받았을때	
	//데이터가 없습니다.
	public fruit read(String id) throws Exception{
		fruit f = null;
		if(!map.containsKey(id)) {
			throw new Exception("없는 과일입니다.");
		}
		f = map.get(id);
		return f;
	}
	
	//문자가 아닌걸 입력 받았을때	
	//찾는 데이터가 없습니다.
	public void update(fruit f) throws Exception{
		if(!map.containsKey(f.getName())) {
			throw new Exception("없는 과일입니다.");
		}
		map.put(f.getName(), f);
	}
	
	//문자가 아닌걸 입력 받았을때	
	//찾는 데이터가 없습니다.
	public void delete(String id) throws Exception {
		if(!map.containsKey(id)) {
			throw new Exception("없는 과일입니다.");
		}
		for(String a : map.keySet()) {
			if(a.equals(id)) {//같은 과일이 있을때만 업데이트 가능 
				map.remove(a);
				System.out.println(a + " 과일을 삭제했습니다. ");
			}
		}
	}


	//search	
	//데이터가 없습니다.
	public ArrayList<fruit> search() throws Exception {
		ArrayList<fruit> list = new ArrayList<fruit>();
		if (map.size() ==0) {
			throw new Exception("데이터가 없습니다.");

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
