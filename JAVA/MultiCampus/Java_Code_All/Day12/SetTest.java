package day12;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();//�ߺ��� �Ұ����ϰ�, ������ ��
		Set<Integer> setInt = new HashSet<>();//�ߺ��� �Ұ����ϰ�, ������ ��
		Random r = new Random();
		int count = 0;
		
		
		set.add("a");
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("a");
		
		for(String s : set) {
			System.out.print(s);
		} 
		System.out.println();
		
		while(setInt.size() <= 5) {
			int num = r.nextInt(45) + 1;
			setInt.add(num);//������ ����Ŭ������ ������ �ڵ����� Integer�� ��������ش�. 
			System.out.print(count++ + ", " );
		}
		System.out.println();
		
		for(Integer i : setInt) {
			System.out.print(i + ", ");
		}
		
		
		
		
		
		
		
		

	}

}
