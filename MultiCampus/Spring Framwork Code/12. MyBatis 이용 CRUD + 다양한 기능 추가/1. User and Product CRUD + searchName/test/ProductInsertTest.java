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
		
		
		//IOC (Inversion Of Control) ���� ����  -> ���� ������ ( Service s = new UserService())
		//XML ������ �̿��� ��ü�� ���� -> �����ϸ� DI ������ ������ �Ͼ��. 
		Service<String, ProductVO> service = (Service<String, ProductVO>) factory.getBean("pservice");
		
		ProductVO p = new ProductVO("pants4", 2000,5.3);
		try {
			service.register(p);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}
	}

}
