package day12;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("Original Date format : " + date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd  E a hh:mm:ss");
		String str = sdf.format(date);
		System.out.println("Simpledata format Test : " + str);
		
		
		TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss");
		sdf2.setTimeZone(tz);//���⼭ �ð� ������ �������κ���� 
		System.out.println("TimeZone(LA) : " + sdf2.format(new Date()));
		
		
		Calendar now = Calendar.getInstance();//Calendar �߻�Ŭ�����̱� ������ ��ü ���� ���� 
		Date d = now.getTime();//��ǻ�� �ð����� ������ 
		System.out.println("myComputer TimeZone : " + d);
		
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;//���� + 1 �ؾ��� 
		int day = now.get(Calendar.DAY_OF_MONTH);//���� ����Ѵ�. 
		int ampm = now.get(Calendar.AM_PM);//����, ���ĸ� ��� 
		int hour = now.get(Calendar.HOUR);//�ð�  
		int minute = now.get(Calendar.MINUTE);//�ð�  
		int second = now.get(Calendar.SECOND);//�ð�  
		
		System.out.printf("%d�� %d�� %d��  %d�� %d�� %d�� �Դϴ�.", year, month, day, hour, minute, second);
		
		
		
		
	}

}
