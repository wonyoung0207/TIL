package day12;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(100, "A");
		map.put(101, "B");
		map.put(102, "C");
		map.put(103, "D");
		
		System.out.println(map.toString());
		
		map.remove(102);
		System.out.println(map.toString());
		
		String str = map.get(103);
		System.out.println(str);
		
		
		

	}

}
