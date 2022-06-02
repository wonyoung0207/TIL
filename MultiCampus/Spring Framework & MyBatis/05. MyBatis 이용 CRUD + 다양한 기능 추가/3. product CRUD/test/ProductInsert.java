package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductInsert {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring ȯ���� ���������. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");

		
		//IOC (Inversion Of Control) ���� ����  -> ���� ������ ( Service s = new UserService())
		//XML ������ �̿��� ��ü�� ���� -> �����ϸ� DI ������ ������ �Ͼ��. 
		Service<String, ProductVO> service = (Service<String, ProductVO>) factory.getBean("pservice");
		
		ProductVO u = new ProductVO("pants05",30000,11);//database�� ���������� �ɷ��־ 11,21�ۿ� �ȵ� 
		
		try {
			service.register(u);
		} catch (Exception e) {
			System.out.println("insert �Է� ������ �߻��߽��ϴ�. ");
			//e.printStackTrace();
		}
		System.out.println("Spring End ..");

	}

}
