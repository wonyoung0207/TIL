package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;
import com.multi.vo.AdminVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class GetAdminTest {

	@Autowired
	MainBiz mbiz;
	
	@Test
	void contextLoads() {
		AdminVO admin = null;
		
		
		try {
			admin = mbiz.getadmin("admin");
			System.out.println(admin);
			
			System.out.println("Admin Search ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


