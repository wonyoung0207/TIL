package com.multi.shoes_cnt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Shoes_cntBiz;
import com.multi.vo.Shoes_cntVO;

@SpringBootTest
class Shoes_cntSelect {

	@Autowired
	Shoes_cntBiz biz;
	
	@Test
	void contextLoads() {
		Shoes_cntVO obj = null;
		try {
			obj = biz.get(7000);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
