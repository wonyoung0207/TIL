package com.multi.product;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectTest {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		ProductVO p = null;
		List<ProductVO> list = null;
		try {
			p = biz.get(3018);
			System.out.println("get Product : " + p);
			System.out.println("product select ok... ");
			
			list = biz.get();
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
			
			System.out.println("product selectAll ok... ");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


