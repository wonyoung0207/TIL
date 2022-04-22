package day12;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();//중복이 불가능하고, 순서가 없
		Set<Integer> setInt = new HashSet<>();//중복이 불가능하고, 순서가 없
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
			setInt.add(num);//정수를 레퍼클래스에 넣으면 자동으로 Integer로 변경시켜준다. 
			System.out.print(count++ + ", " );
		}
		System.out.println();
		
		for(Integer i : setInt) {
			System.out.print(i + ", ");
		}
		
		
		
		
		
		
		
		

	}

}
