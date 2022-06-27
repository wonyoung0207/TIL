package com.multi.product;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductInsertTests {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		// String name, int price ,String imgname1, String imgname2, int cid, String color, String gender
		ProductVO obj = new ProductVO("canverse2",25000,"imgTest1","imgTest2",11,"ColorTest","GenderTest");
		try {
			biz.register(obj);
			System.out.println("insert ok... ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


