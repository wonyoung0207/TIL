package com.multi.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ReviewBiz;
import com.multi.vo.ReviewVO;

@SpringBootTest
public class ReviewUpdateTests {
	@Autowired
	ReviewBiz rbiz;
	
	@Test
	void contextLoads() {
		ReviewVO r = new ReviewVO(6000,3,"좋아요");
		try {
			rbiz.modify(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
