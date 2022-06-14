package com.multi.cust;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustInsertTest {
	
	@Autowired
	CustBiz cbiz;

	@Test
	void contextLoads() {

		CustVO cust = new CustVO("id02","lee","Seoul","pwd02");
		
		try {
			cbiz.register(cust);
			System.out.println("insert OK..");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
