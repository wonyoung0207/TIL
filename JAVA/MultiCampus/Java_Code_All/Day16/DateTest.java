package day16;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//		Date date = sdf.parse(new Date());
		Date date1 = new Date();
		Date date2 = new Date();
		
		
		String date_str1 = "2021-01-01 11:11:11";
		String date_str2 = "2000-03-01 01:12:12";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		date1 = transFormat.parse(date_str1);
//		date2 = transFormat.parse(date_str2);
				
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		
		
		
		
        Calendar cal3 = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
			cal3.setTime(df.parse("2019-07-04"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Calendar cal4 = Calendar.getInstance();
        cal4.setTime(new Date());
        System.out.println("cal: " + df.format(cal3.getTime()));
        System.out.println("cal2: " + df.format(cal4.getTime()));


	}

}
