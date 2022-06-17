package com.multi.buy_detail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Buy_detailBiz;
import com.multi.vo.Buy_detailVO;

@SpringBootTest
class Buy_detailSelectAllTest {

	@Autowired
	Buy_detailBiz biz;
	
	@Test
	void contextLoads() {
		try {
			List<Buy_detailVO> list;
			list = biz.get();
			for (Buy_detailVO buy_detailVO : list) {
				System.out.println(buy_detailVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
