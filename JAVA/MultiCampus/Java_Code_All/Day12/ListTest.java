package day12;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		//List<String> list = new LinkedList<>();
		//ArrayList<String> list = new ArrayList<>();//���� �ǹ̸� ������. 
		
		list.add("First");
		list.add("Second");
		list.add(1,"Third");//1������ ���� �ִ´�. 
		
		for(String a : list) {
			System.out.print(a + " ,");
		}
		

	}

}
