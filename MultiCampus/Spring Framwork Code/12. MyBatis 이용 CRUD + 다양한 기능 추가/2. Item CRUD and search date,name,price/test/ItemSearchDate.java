package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ItemService;
import com.vo.ItemVO;

public class ItemSearchDate {

	public static void main(String[] args) {
		Date mydate = setDate();//������ ��¥ ����Ǵ� ���� 
		
		System.out.println("Spring Start ..");
		
		//Spring ȯ���� ���������. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		ItemService service = (ItemService) factory.getBean("itemservice");
		
		List<ItemVO> list = null;
		try {
			
			list = service.searchDate(mydate); 
			for(ItemVO item : list) {
				System.out.println(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}

	}
	
	public static Date setDate() {
		Scanner input = new Scanner(System.in);
		int year=0, month=0, day=0;
		
		System.out.println("��, ��, �� ������ �Է��� �ּ���.");
		year = input.nextInt();
		month = input.nextInt();
		day = input.nextInt();
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		return new Date(cal.getTimeInMillis());
		
	}

}
