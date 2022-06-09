package com.multi.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class CartInsertTests {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		//(NULL, 'id01',1006,CURDATE(),3);
		CartVO obj = new CartVO("id01",1011,3);
		try {
			biz.register(obj);
			System.out.println("insert OK...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


