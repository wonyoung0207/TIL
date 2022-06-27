package com.multi.cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustSelectTest {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		try {
			System.out.println(biz.get("id01"));
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

}
