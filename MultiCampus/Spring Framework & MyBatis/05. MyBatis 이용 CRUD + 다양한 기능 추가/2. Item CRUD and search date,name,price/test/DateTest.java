package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = setDate(2022,9,3);
		
		System.out.println(sdf.format(myDate));
	}
	
	public static Date setDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date);
		
		return new Date(cal.getTimeInMillis());
		
	}
	
	

}
