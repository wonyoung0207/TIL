package com.multi.review;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ReviewBiz;

@SpringBootTest
public class ReviewSelectStarCnt {
	@Autowired
	ReviewBiz rbiz;
	
	@Test
	void ContextLoads() {
		int v1 = 0;
		int v2 = 0;
		try {
			v1 = rbiz.getreviewcnt(3000);
			v2 = rbiz.getstaravg(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(v1);
		System.out.println(v2);
	}
}
