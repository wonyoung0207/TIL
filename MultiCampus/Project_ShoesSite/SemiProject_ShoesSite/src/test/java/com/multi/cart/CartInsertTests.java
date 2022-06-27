package com.multi.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;

@SpringBootTest
public class CartInsertTests {

@Autowired
CartBiz cartbiz;

@Test
void contextLoads() {
	CartVO cart = new CartVO(2, "id02", 3003, 280);
		try {
			cartbiz.register(cart);
			System.out.println(cart);
			System.out.println("Insert Complited");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
