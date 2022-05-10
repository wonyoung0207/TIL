package day10;

import java.util.ArrayList;

public class NotArrayUse {

	public static void main(String[] args) {
		
		CustomerVO [] c_arr = new CustomerVO[4];
		//배열은 크기를 정해줘야 하기 때문에 실무에서 거의 사용하지 않는다. 
		
		ArrayList<CustomerVO> list = new ArrayList<>();
		//자동으로 크기가 조절된다. 
		list.add(new CustomerVO("id01", "pwd01", "jasmin1"));
		list.add(new CustomerVO("id02", "pwd02", "jasmin2"));
		list.add(new CustomerVO("id03", "pwd03", "jasmin3"));
		
		for(CustomerVO i : list) {
			System.out.println(i);
		}
		
		

	}

}
