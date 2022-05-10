package day10;

import java.util.HashMap;

public class FruitDAO implements Ifruit{

	HashMap<String, fruit> map = new HashMap<String, fruit>();
	
	@Override
	public void create(fruit f) {
		map.put(f.getName(), f);
		System.out.println(f.getName() + "���� �ڳʰ� �����Ǿ����ϴ�.");
	}

	@Override
	public fruit read(String id) {
		
		for(String f : map.keySet()) {
			if(f.equals(id)) {//�Ѿ�� id�� ������ ���� 
				return map.get(id);//��ü�� �ѱ� 
			}
			
		}
		System.out.println("ã�� ������ �����ϴ�. ");
		return null;
	}

	@Override
	public int update(fruit f) {
		
		
		for(String a : map.keySet()) {
			if(a.equals(f.getName())) {//���� ������ �������� ������Ʈ ���� 
				map.put(f.getName(),f);
				return 1;
			}
		}
		
		System.out.println("������Ʈ�� ������ �̸��� �߸��Ǿ����ϴ�. ������Ʈ�� ����մϴ�. ");
		
		return 0;
	}

	@Override
	public int delete(String id) {
		for(String a : map.keySet()) {
			if(a.equals(id)) {//���� ������ �������� ������Ʈ ���� 
				map.remove(a);
				return 1;
			}
		}
		
		System.out.println("������ ������ �����ϴ�. ");
		return 0;
		
	}
	

}
