package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@SpringBootTest
public class CateUpdateTests {

	@Autowired
	CateBiz biz;
	
	@Test
	void contextLoads() {
		CateVO c = new CateVO(31,"wedding shoes");
	
			try {
				biz.modify(c);
				System.out.println("카테고리가 수정되었습니다");
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		
	}
}
