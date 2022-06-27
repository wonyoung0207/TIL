package com.multi.review;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ReviewBiz;
import com.multi.vo.ReviewVO;

@SpringBootTest
public class ReviewSelectProduct {
	@Autowired
	ReviewBiz rbiz;
	
	@Test
	void ContextLoads() {
		List<ReviewVO> list = null;
		try {
			list = rbiz.getproduct(3000);
			for (ReviewVO r : list) {
				System.out.println(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
