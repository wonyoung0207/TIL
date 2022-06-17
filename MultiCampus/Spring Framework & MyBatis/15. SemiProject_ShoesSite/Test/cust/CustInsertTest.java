package com.multi.cust;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustInsertTest {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		CustVO cust = new CustVO("id07", "pwd07", "hong");
		try {
			biz.register(cust);
			System.out.println(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
