package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;
import com.vo.UserVO;

public class ProductUpdateTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring ȯ���� ���������. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");

		
		//IOC (Inversion Of Control) ���� ����  -> ���� ������ ( Service s = new UserService())
		//XML ������ �̿��� ��ü�� ���� -> �����ϸ� DI ������ ������ �Ͼ��. 
		Service<String, ProductVO> service = (Service<String, ProductVO>) factory.getBean("pservice");
		
		ProductVO u = new ProductVO(2,"pants99", 2001, 2.0);
		try {
			service.modify(u);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}

	}

}
