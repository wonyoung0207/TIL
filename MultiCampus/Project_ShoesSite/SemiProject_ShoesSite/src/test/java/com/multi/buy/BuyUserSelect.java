package com.multi.buy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.BuyBiz;
import com.multi.vo.BuyVO;

@SpringBootTest
class BuyUserSelect {

	@Autowired
	BuyBiz biz;
	
	@Test
	void contextLoads() {
		List<BuyVO> obj = null;
		
		try {
			obj = biz.selectUserBuy("id02");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

}
