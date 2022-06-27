package com.multi.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;

@SpringBootTest
class CartCntSelectTest {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		try {
			int list;
			list = biz.selectcnt("id02");
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
