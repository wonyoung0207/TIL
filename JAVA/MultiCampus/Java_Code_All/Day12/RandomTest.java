package day12;

import java.util.Random;

public class RandomTest {
	public static void main(String[] args) {
		double d = Math.random();// 0.0 ~ 1.0 ������ ������ �߻� 
		System.out.println(d);
		
		int dice = (int)(Math.random() * 6) + 1;
		System.out.println(dice);
		
		
		Random r1 = new Random(3);//�õ尪�� �����ϸ� ���� random���� ��´�. 
		System.out.println(r1.nextInt(6));
		System.out.println(r1.nextInt(6));
		
		Random r = new Random();
		int i3 = r.nextInt(45) + 1;
		System.out.println(i3);
		
		
		
	}
}
