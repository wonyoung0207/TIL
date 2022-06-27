package com.multi.buy_detail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Buy_detailBiz;
import com.multi.vo.Buy_detailVO;

@SpringBootTest
class Buy_detailGetbuy_detailllTest {

	@Autowired
	Buy_detailBiz biz;
	
	@Test
	void contextLoads() {
		try {
			List<Buy_detailVO> list;
			list = biz.getbuy_detail("id02");
			
			for (Buy_detailVO buy_detailVO : list) {
				biz.register(buy_detailVO);
				System.out.println(biz.selectid(2024));
			}
			for (Buy_detailVO buy_detailVO : list) {
				System.out.println(buy_detailVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
