package com.multi.addr_list;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.Addr_listBiz;
import com.multi.vo.Addr_listVO;

@SpringBootTest
class Addr_listSelectAllTest {

	@Autowired
	Addr_listBiz biz;
	
	@Test
	void contextLoads() {
		try {
			List<Addr_listVO> addr;
			addr = biz.get();
			for (Addr_listVO addr_listVO : addr) {
				System.out.println(addr_listVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}

}
