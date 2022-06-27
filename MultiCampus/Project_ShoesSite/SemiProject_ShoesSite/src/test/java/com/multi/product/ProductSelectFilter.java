package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.Filter;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectFilter {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		Filter f = new Filter("GC", "Men", "12,11", 0, 0, null, null,2);
		
		// split으로 쪼개서 배열에 넣은 각 카테고리별 체크항목들이 배열에 잘 들어갔는지 확인용
		for (String str : f.getCidArr()) {
			System.out.println(str);
		}
		
		
		List<ProductVO> list = null;
		try {
			list = biz.getfilter(f);
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
			
			// 타입과 젠더 출력
			System.out.println(f.getType()+f.getGender());
			
			System.out.println("product selectfilter ok... ");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


