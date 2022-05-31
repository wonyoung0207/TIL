package com.test;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ItemService;
import com.vo.ItemVO;

public class ItemSearchName {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		
		//Service<String, ProductVO> service = (Service<String, ProductVO>) factory.getBean("pservice");
		ItemService service = (ItemService) factory.getBean("itemservice");

		List<ItemVO> list = null;
		try {
			list = service.searchName("p");
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
