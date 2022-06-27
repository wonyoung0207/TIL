package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@SpringBootTest
public class CateInsertTests {

	@Autowired
	CateBiz catebiz;
	
	@Test
	void contextLoads() {
		CateVO c = new CateVO(60,"formal",(Integer) null);

		try {
			catebiz.register(c);
	
			System.out.println("New Category Registered OK");		
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
	}
}
