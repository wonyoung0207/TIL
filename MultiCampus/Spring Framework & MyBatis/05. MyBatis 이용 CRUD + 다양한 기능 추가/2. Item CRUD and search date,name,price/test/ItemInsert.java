package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemInsert {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		
		Service<Integer, ItemVO> service = (Service<Integer, ItemVO>) factory.getBean("itemservice");
		
		ItemVO i = new ItemVO("pants6",30000, "pants6.jpg");
		
		try {
			service.register(i);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring end ..");
		}
		
	}

}
