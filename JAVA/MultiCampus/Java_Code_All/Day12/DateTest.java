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
		sdf2.setTimeZone(tz);//여기서 시간 설정이 뉴욕으로변경됨 
		System.out.println("TimeZone(LA) : " + sdf2.format(new Date()));
		
		
		Calendar now = Calendar.getInstance();//Calendar 추상클래스이기 때문에 객체 생성 못함 
		Date d = now.getTime();//컴퓨터 시간으로 설정됨 
		System.out.println("myComputer TimeZone : " + d);
		
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;//월은 + 1 해야함 
		int day = now.get(Calendar.DAY_OF_MONTH);//일을 출력한다. 
		int ampm = now.get(Calendar.AM_PM);//오전, 오후를 출력 
		int hour = now.get(Calendar.HOUR);//시간  
		int minute = now.get(Calendar.MINUTE);//시간  
		int second = now.get(Calendar.SECOND);//시간  
		
		System.out.printf("%d년 %d월 %d일  %d시 %d분 %d초 입니다.", year, month, day, hour, minute, second);
		
		
		
		
	}

}
