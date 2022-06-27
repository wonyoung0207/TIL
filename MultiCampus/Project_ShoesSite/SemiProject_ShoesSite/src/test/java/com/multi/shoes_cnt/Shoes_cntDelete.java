package com.multi.shoes_cnt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Shoes_cntBiz;

@SpringBootTest
class Shoes_cntDelete {

	@Autowired
	Shoes_cntBiz biz;
	
	@Test
	void contextLoads() {
		try {
			biz.remove(7008);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
