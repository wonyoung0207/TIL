package com.multi.buy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.BuyBiz;
import com.multi.vo.BuyVO;

@SpringBootTest
class BuyInsert {

	@Autowired
	BuyBiz biz;
	
	@Test
	void contextLoads() {
		BuyVO obj = new BuyVO("id01", "kim", "Seoul","Guro", "010-1234-1234", "hello", 1000);
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
