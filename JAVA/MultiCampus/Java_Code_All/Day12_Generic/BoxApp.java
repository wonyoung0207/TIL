package day12_Generic;

import java.util.List;

public class BoxApp {

	public static void main(String[] args) {
		Box<ItemVO> box = new Box<>();//���׸��� ItemVO�� Ÿ���� ���� �ڵ���ġ�ȴ�. 
		//Ÿ��ĳ�����ϴ� ���ŷο��� �ٿ��ش�. 
		
		box.setBox(new ItemVO(100,"pants", 10000));
		
		ItemVO item = box.getBox();//���׸� ���ϸ� Ÿ��ĳ�����ؾ��� 
//		ItemVO item = (ItemVO)box.getBox();// 
		
		System.out.println(item);
		
		Box<CustomerVO> box2 = new Box<>();
		
		box2.setBox(new CustomerVO("id01", "pw123", "ahn"));
		System.out.println(box2.getBox());
		
	}

}
