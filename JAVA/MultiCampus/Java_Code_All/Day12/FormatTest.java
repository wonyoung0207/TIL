package day12;

import java.text.DecimalFormat;

public class FormatTest {

	public static void main(String[] args) {
		double num = 123456.789;
		DecimalFormat df = new DecimalFormat("####.######");//숫자 형식 클래스 
		DecimalFormat df1 = new DecimalFormat("#,###.0");//숫자 형식 클래스 
		DecimalFormat df2 = new DecimalFormat("\u00A4 #,###.0");//숫자 형식 클래스 
		
		String str = df.format(num);//String 으로 리턴이 된다. 
		System.out.println(str);
		
		String str1 = df1.format(num);
		System.out.println(str1);
		
		String str2 = df2.format(num);
		System.out.println(str2);
		
		
	}

}
