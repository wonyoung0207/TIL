package day08;

import java.util.Scanner;

public class calc {
	private int a;
	private int b;
	static int count;
	
	public calc() {
		
	}
	public calc(int a, int b) {
		this.a = a;
		this.b = b;
		count++;
	}
	
	
	public int sum() {
		return (this.a + this.b);
		
	}
	
	public int div() {
		return (this.a / this.b);
		
	}
	
	public int avg() {
		return (this.a + this.b) / 2;
		
	}

}
