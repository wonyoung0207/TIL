package com.multi.main;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSearchTest {

	@Autowired
	MainBiz mbiz;
	
	@Test
	void contextLoads() {
		List<ProductVO> list = null;
		
		
		try {
			list = mbiz.searchproduct("sh");
			for(ProductVO p : list) {
				System.out.println(p);
			}
			
			System.out.println("Product Search ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


