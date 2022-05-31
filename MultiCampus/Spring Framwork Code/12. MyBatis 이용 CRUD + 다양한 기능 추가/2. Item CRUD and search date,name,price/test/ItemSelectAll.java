package com.test;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemSelectAll {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		
		Service<Integer, ItemVO> service = (Service<Integer, ItemVO>) factory.getBean("itemservice");

		List<ItemVO> list = null;
		try {
			list = service.getAll();
			for(ItemVO item : list) {
				System.out.println(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}
		


	}

}
