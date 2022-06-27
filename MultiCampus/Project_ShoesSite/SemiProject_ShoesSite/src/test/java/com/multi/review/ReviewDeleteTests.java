package com.multi.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ReviewBiz;
import com.multi.vo.ReviewVO;


@SpringBootTest
public class ReviewDeleteTests {

	@Autowired
	ReviewBiz rbiz;
	
	@Test
	void contextLoads() {
		try {
			rbiz.remove(6000);
		} catch (Exception e) {
			System.out.println("Review is deleted");
			e.printStackTrace();
		}
		
	}
}
