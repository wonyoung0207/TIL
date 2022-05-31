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

		
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");

		try {
			System.out.println(service.get("id89"));
		} catch (Exception e) {
			System.out.println("Select �� �����߻� ");
			e.printStackTrace();
		}finally {
			
			System.out.println("Spring End ..");
		}
	}

}
