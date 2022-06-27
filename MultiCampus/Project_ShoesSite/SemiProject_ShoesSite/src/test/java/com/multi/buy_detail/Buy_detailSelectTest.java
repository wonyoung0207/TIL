package com.multi.buy_detail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Buy_detailBiz;
import com.multi.vo.Buy_detailVO;

@SpringBootTest
class Buy_detailSelectTest {

	@Autowired
	Buy_detailBiz biz;
	
	@Test
	void contextLoads() {
		try {
			System.out.println(biz.get(4003));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
