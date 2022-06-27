package com.multi.review;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ReviewBiz;

@SpringBootTest
public class ReviewSelectTests {
	@Autowired
	ReviewBiz rbiz;
	
	@Test
	void ContextLoads() {
		try {
			rbiz.get(6002);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
