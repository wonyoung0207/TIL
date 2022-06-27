package com.multi.buy_detail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Buy_detailBiz;
import com.multi.vo.Buy_detailVO;

@SpringBootTest
class Buy_detailDeleteTest {

	@Autowired
	Buy_detailBiz biz;
	
	@Test
	void contextLoads() {
		try {
			biz.remove(4004);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
