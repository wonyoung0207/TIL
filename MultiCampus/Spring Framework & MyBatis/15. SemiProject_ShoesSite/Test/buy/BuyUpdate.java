package com.multi.buy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.BuyBiz;
import com.multi.vo.BuyVO;

@SpringBootTest
class BuyUpdate {

	@Autowired
	BuyBiz biz;
	
	@Test
	void contextLoads() {
		BuyVO obj = new BuyVO(2005, "seo", "updatetest", "010", "hello");
		try {
			biz.modify(obj);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
