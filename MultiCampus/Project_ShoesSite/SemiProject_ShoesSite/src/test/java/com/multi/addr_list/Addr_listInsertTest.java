package com.multi.addr_list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Addr_listBiz;
import com.multi.vo.Addr_listVO;

@SpringBootTest
class Addr_listInsertTest {

	@Autowired
	Addr_listBiz biz;
	
	@Test
	void contextLoads() {
		try {
			Addr_listVO addr = new Addr_listVO("id01", "seoul", "gangnam", 311);
			biz.register(addr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
