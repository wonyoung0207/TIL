package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;
import com.vo.UserVO;

public class ProductUpdateTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");

		
		//IOC (Inversion Of Control) 제어 역행  -> 원래 방향은 ( Service s = new UserService())
		//XML 파일을 이용해 객체를 생성 -> 생성하며 DI 의존성 주입이 일어난다. 
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
