package day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Map {

	public static void main(String[] args) {
		// Map = Key , value �� �����ϴ� ���� 
		HashMap<String ,CustomerVO> map = new HashMap<String, CustomerVO>();
		
		CustomerVO c1 = new CustomerVO("id01", "pwd01", "jasmin1");
		CustomerVO c2 = new CustomerVO("id02", "pwd02", "jasmin2");
		CustomerVO c3 = new CustomerVO("id03", "pwd03", "jasmin3");
		CustomerVO c4 = new CustomerVO("id04", "pwd04", "jasmin4");
		CustomerVO c5 = new CustomerVO("id05", "pwd05", "jasmin5");
		
		map.put(c1.getId(), c1);
		map.put(c2.getId(), c2);
		map.put(c3.getId(), c3);
		map.put(c4.getId(), c4);
		map.put(c5.getId(), c5);
		
//		System.out.println(map.get(c1.getId()));
		
		// ��� ��� 1
		Collection<CustomerVO> col = map.values();
		Iterator<CustomerVO> it = col.iterator();
		ArrayList<CustomerVO> list = new ArrayList<>();
		
		while(it.hasNext()) {
			CustomerVO has_c = it.next();
			list.add(has_c);
		}
		
		for(CustomerVO list_c : list) {
			System.out.println(list_c);
		}
		
		//��� ��� 2
		Iterator<String> keys = map.keySet().iterator(); 
		while(keys.hasNext()) {
			String key = keys.next();
			System.out.println("iterator hasNext �̿�: " + map.get(key));
		}
		
		//��� ��� 3
		for(String keys2 : map.keySet()) {
			System.out.println("���� for�� �̿� : " + map.get(keys2));
		}
		
		
		// ���� 
		map.remove("id01");
		for(String keys2 : map.keySet()) {
			System.out.println("id01���� : " + map.get(keys2));
		}
		
		// ���
		map.remove("id01");
		for(String keys2 : map.keySet()) {
			if(keys2.equals("id02")) {
				System.out.println("id01���� : " + map.get(keys2));
				
			}
			else {
				System.out.println("??? : " + map.get(keys2));
				
			}
		}
		
		
		// �ߺ����� �Ǵ��� 
		map.put("id01",new CustomerVO("id01", "pwd01", "jasmin1"));
		
		for(String keys2 : map.keySet()) {
			System.out.println("�ߺ����� �Ǵ��� : "+map.get(keys2));
		}
		
		
		
		System.out.println(map.keySet());

	}

}
