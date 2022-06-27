package com.multi.buy_detail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Buy_detailBiz;
import com.multi.vo.Buy_detailVO;

@SpringBootTest
class Buy_detailUpdateTest {

	@Autowired
	Buy_detailBiz biz;
	
	@Test
	void contextLoads() {
		Buy_detailVO buy = new Buy_detailVO(4004,2002, 3000, 260, 1);
		try {
			biz.register(buy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
