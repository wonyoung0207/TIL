package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;

@SpringBootTest
class MainCountTests {

	@Autowired
	MainBiz mbiz;
	
	@Test
	void contextLoads() {
		int count;
		
		try {
			count = mbiz.getCustCnt();
			System.out.println("CustCnt : " + count);
			
			count = mbiz.getProductCnt();
			System.out.println("ProductCnt : " + count);
			
			
			
			System.out.println("Cust Product Count ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


