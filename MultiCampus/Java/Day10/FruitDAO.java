package day10;

import java.util.HashMap;

public class FruitDAO implements Ifruit{

	HashMap<String, fruit> map = new HashMap<String, fruit>();
	
	@Override
	public void create(fruit f) {
		map.put(f.getName(), f);
		System.out.println(f.getName() + "과일 코너가 생성되었습니다.");
	}

	@Override
	public fruit read(String id) {
		
		for(String f : map.keySet()) {
			if(f.equals(id)) {//넘어온 id와 같은걸 리턴 
				return map.get(id);//객체를 넘김 
			}
			
		}
		System.out.println("찾는 과일이 없습니다. ");
		return null;
	}

	@Override
	public int update(fruit f) {
		
		
		for(String a : map.keySet()) {
			if(a.equals(f.getName())) {//같은 과일이 있을때만 업데이트 가능 
				map.put(f.getName(),f);
				return 1;
			}
		}
		
		System.out.println("업데이트할 과일의 이름이 잘못되었습니다. 업데이트를 취소합니다. ");
		
		return 0;
	}

	@Override
	public int delete(String id) {
		for(String a : map.keySet()) {
			if(a.equals(id)) {//같은 과일이 있을때만 업데이트 가능 
				map.remove(a);
				return 1;
			}
		}
		
		System.out.println("삭제할 과일이 없습니다. ");
		return 0;
		
	}
	

}
