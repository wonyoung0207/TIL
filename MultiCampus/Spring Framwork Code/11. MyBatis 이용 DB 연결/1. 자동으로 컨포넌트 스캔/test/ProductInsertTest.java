package com.test;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;
import com.vo.UserVO;

public class ProductInsertTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");

		//Spring ȯ���� ���������. 
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
		System.out.println("Spring End ..");

		//IOC (Inversion Of Control) ���� ����  -> ���� ������ ( Service s = new UserService())
		//XML ������ �̿��� ��ü�� ���� -> �����ϸ� DI ������ ������ �Ͼ��. 
		Service<Integer, ProductVO> service = (Service<Integer, ProductVO>) factory.getBean("pservice");

		ProductVO p = new ProductVO(101, "pants1", 12300);
		service.register(p);
		
		service.get(101);
		
		System.out.println(service.getAll() + "\n");
		

	}

}
