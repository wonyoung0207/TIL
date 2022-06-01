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

		
		Service<String, UserVO> service = (Service<String, UserVO>) factory.getBean("uservice");

		List<UserVO> list = null;
		try {
			list = service.getAll();
			
			for(UserVO userVO : list) {
				System.out.println(userVO);
			}
			
			
		} catch (Exception e) {
			System.out.println("SelectAll 오류 입니다. 데이터 또는 입력형식을 다시 확인하세요.");
//			e.printStackTrace();
		}
		finally {
			System.out.println("Spring End ..");
		}


	}

}
