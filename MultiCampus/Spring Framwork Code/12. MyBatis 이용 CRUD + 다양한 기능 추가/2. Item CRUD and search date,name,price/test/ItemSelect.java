package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemSelect {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		
		Service<Integer, ItemVO> service = (Service<Integer, ItemVO>) factory.getBean("itemservice");

		try {
			System.out.println(service.get(1001));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
			
		}
	}

}
