package day08;

import java.util.Scanner;

public class calcApp {
	public static void main(String []args) {
		calc c1 = new calc(10,2);
		int avg = c1.avg();
		System.out.println("평균 : " + avg);
		System.out.println("현재 " + calc.count + "개의 계산기가 있습니다. ");
		
	}
}
