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
	CustBiz cbiz;
	
	
	@Test
	void contextLoads() {
		List<CustVO> list = null;
		
		try {
			list = cbiz.get();
			for(CustVO obj : list) {
				System.out.println(obj);
			}
			System.out.println("Select ALL ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 1개 데이터 가져오기 

		CustVO obj = null;
		try {
			obj = cbiz.get("id01");
			System.out.println(obj);
			System.out.println("Select ok...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
