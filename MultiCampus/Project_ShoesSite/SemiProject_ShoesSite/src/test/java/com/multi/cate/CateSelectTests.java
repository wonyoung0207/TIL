package com.multi.cate;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@SpringBootTest
public class CateSelectTests {

	@Autowired
	CateBiz biz;
	
	@Test
	void contextLoads() {
		
		try {
			System.out.println(biz.get(50));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
