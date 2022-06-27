package com.multi.product;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductUpdateTest {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		int Update_id = 3018;
		
		ProductVO p = new ProductVO(Update_id,"UpdateTest",26000,"imgUpdate","imgUpdate",12,"colorUpdate","Gender Update");

		try {
			biz.modify(p);
			ProductVO pUpdate = biz.get(Update_id);
			
			System.out.println("update " + Update_id + " : " + pUpdate);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


