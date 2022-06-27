package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;

@SpringBootTest
public class CateDeleteTests {

	@Autowired
	CateBiz biz;
	
	@Test
	void contextLoads() {
		int id = 50;
		try {
			biz.remove(id);
			System.out.println(id +"가/이 삭제되었습니다.");
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}
}
