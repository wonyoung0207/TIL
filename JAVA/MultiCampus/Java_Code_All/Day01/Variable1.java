package day01;

public class Variable1 {
	public static void main(String[] args) {
		int a;
		//a = 20000000000;// 메모리 문제 때문에 length가 존재 한다. 
		double b = (double) Math.round(2.6);
		int c= (int) (Math.random() * 10);
		double d= (Math.round(Math.random() * 10));
		
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		
	}

}
