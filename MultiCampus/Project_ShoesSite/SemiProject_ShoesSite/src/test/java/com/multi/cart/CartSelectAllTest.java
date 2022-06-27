package com.multi.cart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.Buy_detailVO;
import com.multi.vo.CartVO;

@SpringBootTest
class CartSelectAllTest {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		try {
			List<CartVO> list;
			list = biz.get();
			for (CartVO buy_detailVO : list) {
				System.out.println(buy_detailVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
