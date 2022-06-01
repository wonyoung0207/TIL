package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductSelectTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		System.out.println("Spring End ..");
		
		Service<String, ProductVO> service = (Service<String, ProductVO>) factory.getBean("pservice");

		System.out.println(service.get("2"));
	}

}
