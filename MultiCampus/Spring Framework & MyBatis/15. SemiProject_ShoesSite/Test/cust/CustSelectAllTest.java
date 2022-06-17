package com.multi.cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustSelectAllTest {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		try {
			List<CustVO> cust;
			cust =biz.get();
			for (CustVO custVO : cust) {
				System.out.println(custVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

}
