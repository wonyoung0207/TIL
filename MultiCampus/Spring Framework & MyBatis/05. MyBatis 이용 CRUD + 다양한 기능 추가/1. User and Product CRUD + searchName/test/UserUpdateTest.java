package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserUpdateTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");

		
		//IOC (Inversion Of Control) 제어 역행  -> 원래 방향은 ( Service s = new UserService())
		//XML 파일을 이용해 객체를 생성 -> 생성하며 DI 의존성 주입이 일어난다. 
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");
		
		UserVO u = new UserVO("id89", "pwd899", "lee123");
		try {
			service.modify(u);
		} catch (Exception e) {
			System.out.println("data 수정에서 오류발생. 입력데이터 또는 형식을 확인해 주세요.");
//			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}

	}

}
