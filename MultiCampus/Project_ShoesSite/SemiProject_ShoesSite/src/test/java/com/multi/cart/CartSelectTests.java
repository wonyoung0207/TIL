package com.multi.cart;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;

@SpringBootTest
public class CartSelectTests {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		CartVO cart = null;
		List<CartVO> list = null;
		
		try {
			cart = biz.get(6000);
			System.out.println("카트(no.6000)를 불러옵니다.");
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	
		
	}
}
