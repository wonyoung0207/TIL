package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserSelectAllTest {

	public static void main(String[] args) {
		System.out.println("Spring Start ..");
		
		//Spring 환경이 만들어진다. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		System.out.println("Spring End ..");
		
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");

		List<UserVO> list = null;
		list = service.getAll();
		
		for(UserVO userVO : list) {
			System.out.println(userVO);
		}

	}

}
