package day12_Generic;

import java.util.List;

public class BoxApp {

	public static void main(String[] args) {
		Box<ItemVO> box = new Box<>();//제네릭에 ItemVO의 타입이 들어가서 자동매치된다. 
		//타입캐스팅하는 번거로움을 줄여준다. 
		
		box.setBox(new ItemVO(100,"pants", 10000));
		
		ItemVO item = box.getBox();//제네릭 안하면 타입캐스팅해야함 
//		ItemVO item = (ItemVO)box.getBox();// 
		
		System.out.println(item);
		
		Box<CustomerVO> box2 = new Box<>();
		
		box2.setBox(new CustomerVO("id01", "pw123", "ahn"));
		System.out.println(box2.getBox());
		
	}

}
