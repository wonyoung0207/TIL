package com.multi.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ReviewBiz;
import com.multi.vo.ReviewVO;

@SpringBootTest
public class ReviewInsertTests {
	@Autowired
	ReviewBiz rbiz;
	
	@Test
	void contextLoads() {
		ReviewVO r = new ReviewVO(6005,3002,3,"나쁘지 않아요", null, "id05");
		try {
			rbiz.register(r);
			System.out.println("New Review!!");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
