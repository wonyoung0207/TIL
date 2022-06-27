package com.multi.buy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.BuyBiz;
import com.multi.vo.BuyVO;

@SpringBootTest
class BuySelectId {

	@Autowired
	BuyBiz biz;
	
	@Test
	void contextLoads() {
		int obj ;
		try {
			obj = biz.selectid();
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
