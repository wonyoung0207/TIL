package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserDeleteTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		//IOC (Inversion Of Control) 제어 역행  -> 원래 방향은 ( Service s = new UserService())
		//XML 파일을 이용해 객체를 생성 -> 생성하며 DI 의존성 주입이 일어난다. 
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");
		
		UserVO u = new UserVO("id89", "pwd89", "lee");
		try {
			service.remove("id89");
		} catch (Exception e) {
			System.out.println("시스템 장애 : DataBase 포트번호를 확인해보세요. ");
//			e.printStackTrace();
		}

		System.out.println("Spring End ..");
	}

}
