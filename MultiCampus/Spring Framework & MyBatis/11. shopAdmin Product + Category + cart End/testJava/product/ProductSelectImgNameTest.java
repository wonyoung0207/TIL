package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectImgNameTest {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		List<String> list = null;
		try {
			list = biz.getSelectAllImgName();
			for (String obj : list) {
				System.out.println(obj);
			}
			
			System.out.println("selectAllImgName ok... ");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


