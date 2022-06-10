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
		List<ProductVO> list = null;
		try {
			list = biz.get();
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
			
			System.out.println("cate name selectAll ok... ");
			
			list = biz.get2();
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
			System.out.println("cate main name selectAll ok... ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


