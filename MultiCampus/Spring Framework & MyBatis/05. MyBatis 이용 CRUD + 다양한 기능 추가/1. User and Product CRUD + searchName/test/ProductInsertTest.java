package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;
import com.vo.UserVO;

public class ProductInsertTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		
		//IOC (Inversion Of Control) 제어 역행  -> 원래 방향은 ( Service s = new UserService())
		//XML 파일을 이용해 객체를 생성 -> 생성하며 DI 의존성 주입이 일어난다. 
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
