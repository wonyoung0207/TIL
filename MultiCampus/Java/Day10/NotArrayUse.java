package day10;

import java.util.ArrayList;

public class NotArrayUse {

	public static void main(String[] args) {
		
		CustomerVO [] c_arr = new CustomerVO[4];
		//�迭�� ũ�⸦ ������� �ϱ� ������ �ǹ����� ���� ������� �ʴ´�. 
		
		ArrayList<CustomerVO> list = new ArrayList<>();
		//�ڵ����� ũ�Ⱑ �����ȴ�. 
		list.add(new CustomerVO("id01", "pwd01", "jasmin1"));
		list.add(new CustomerVO("id02", "pwd02", "jasmin2"));
		list.add(new CustomerVO("id03", "pwd03", "jasmin3"));
		
		for(CustomerVO i : list) {
			System.out.println(i);
		}
		
		

	}

}
