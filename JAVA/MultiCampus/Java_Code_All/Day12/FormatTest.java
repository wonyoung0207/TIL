package day12;

import java.text.DecimalFormat;

public class FormatTest {

	public static void main(String[] args) {
		double num = 123456.789;
		DecimalFormat df = new DecimalFormat("####.######");//���� ���� Ŭ���� 
		DecimalFormat df1 = new DecimalFormat("#,###.0");//���� ���� Ŭ���� 
		DecimalFormat df2 = new DecimalFormat("\u00A4 #,###.0");//���� ���� Ŭ���� 
		
		String str = df.format(num);//String ���� ������ �ȴ�. 
		System.out.println(str);
		
		String str1 = df1.format(num);
		System.out.println(str1);
		
		String str2 = df2.format(num);
		System.out.println(str2);
		
		
	}

}
