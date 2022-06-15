package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@SpringBootTest
class CateInsertTest {

	@Autowired
	CateBiz biz;
	
	@Test
	void contextLoads() {
//		CateVO obj = new CateVO(40, "Bags");//최상위 카테고리부터 해야함 
//		CateVO obj = new CateVO(41, "long Bags", 40);//최상위 카테고리부터 해야함 
		CateVO obj = new CateVO(30, "Hat", 0); 
		try {
			biz.register(obj);
			System.out.println("Insert ok...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


