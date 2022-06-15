package com.multi.cart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class CartUserSelectTests {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		List<CartVO> list = null;
		try {
			list = biz.selectuserlist("id01");
			for (CartVO obj : list) {
				System.out.println(obj);
			}
			System.out.println("SelectAll ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


