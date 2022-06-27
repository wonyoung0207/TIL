package com.multi.shoes_cnt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Shoes_cntBiz;
import com.multi.vo.Shoes_cntVO;

@SpringBootTest
class Shoes_cntInsert {

	@Autowired
	Shoes_cntBiz biz;
	
	@Test
	void contextLoads() {
		Shoes_cntVO obj = new Shoes_cntVO(3010, 250, 5);
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
