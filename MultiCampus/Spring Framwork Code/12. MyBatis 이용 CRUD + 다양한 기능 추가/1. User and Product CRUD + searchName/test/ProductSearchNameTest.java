package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.service.ProductService;
import com.vo.ProductVO;

public class ProductSearchNameTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		
		//Service<String, ProductVO> service = (Service<String, ProductVO>) factory.getBean("pservice");
		ProductService service = (ProductService) factory.getBean("pservice");

		List<ProductVO> list = null;
		try {
			list = service.searchName("p");
			for(ProductVO product : list) {
				System.out.println(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}
		


	}

}
