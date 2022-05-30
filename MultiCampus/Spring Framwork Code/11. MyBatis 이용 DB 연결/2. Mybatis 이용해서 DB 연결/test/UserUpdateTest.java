package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserUpdateTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring ȯ���� ���������. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		System.out.println("Spring End ..");
		
		//IOC (Inversion Of Control) ���� ����  -> ���� ������ ( Service s = new UserService())
		//XML ������ �̿��� ��ü�� ���� -> �����ϸ� DI ������ ������ �Ͼ��. 
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");
		
		UserVO u = new UserVO("id89", "pwd899", "lee123");
		service.modify(u);

	}

}
