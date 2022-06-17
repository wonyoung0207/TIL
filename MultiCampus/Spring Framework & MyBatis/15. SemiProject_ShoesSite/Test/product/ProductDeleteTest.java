package com.multi.product;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductDeleteTest {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		int delete_id = 3018;
		
		try {
			biz.remove(delete_id);

			
			System.out.println("delete " + delete_id + " success... ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


