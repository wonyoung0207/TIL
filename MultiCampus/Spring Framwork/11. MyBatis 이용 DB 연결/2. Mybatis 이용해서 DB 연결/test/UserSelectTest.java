package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserSelectTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring ȯ���� ���������. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		System.out.println("Spring End ..");
		
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");

		System.out.println(service.get("id89"));
	}

}
