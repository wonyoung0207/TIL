package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.ProductMapper;
import com.vo.ProductVO;

@org.springframework.stereotype.Service("pservice")
public class ProductService implements Service<String,ProductVO>{
	//Dao<String, UserVO> userDao;//UserDao 에서 @Repository 로 설정한 이름을 변수로 설정한다. 
		
	@Autowired
	ProductMapper dao;//자동으로 dao를 검색해서 가져와라 .
	
	@Override
	public void register(ProductVO v) {
		dao.insert(v);//UserMapper에게 insert를 한다. 
	}

	@Override
	public void remove(String k) {
		int n = Integer.parseInt(k);//product의 id는 int형이기 때문에 
		
		dao.delete(n);
	}

	@Override
	public void modify(ProductVO v) {
		dao.update(v);
	}

	@Override
	public ProductVO get(String k) {
		int n = Integer.parseInt(k);//product의 id는 int형이기 때문에 
		
		return dao.select(n);
	}

	@Override
	public List<ProductVO> getAll() {
		
		return dao.selectAll();
	}
	
	
}
