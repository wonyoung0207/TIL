package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductAVGVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectAVGTests {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		List<ProductAVGVO> list = null;
		try {

			list = biz.getAvg();
			for (ProductAVGVO obj : list) {
				System.out.println(obj);
			}
			System.out.println("cate avg price ok... ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


