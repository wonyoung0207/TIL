package com.multi.cart;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;

@SpringBootTest
public class CartUpdateTests {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		List<CartVO> clist = new ArrayList<CartVO>();
		CartVO c = new CartVO(5001, 5);
		CartVO a = new CartVO(5002, 5);
		CartVO b = new CartVO(5003, 5);
		
		clist.add(c);
		clist.add(a);
		clist.add(b);
		try {
			for (CartVO cartVO : clist) {
				biz.modify(cartVO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
