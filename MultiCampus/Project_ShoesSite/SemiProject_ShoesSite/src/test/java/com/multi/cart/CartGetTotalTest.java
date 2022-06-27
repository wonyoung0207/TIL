package com.multi.cart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.Buy_detailVO;
import com.multi.vo.CartVO;

@SpringBootTest
class CartGetTotalTest {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		try {
			int total;
			total = biz.gettotal("id02");
			System.out.println(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
