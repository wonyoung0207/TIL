package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.ProductMapper;
import com.vo.ProductVO;

@org.springframework.stereotype.Service("pservice")
public class ProductService implements Service<String,ProductVO>{
	//Dao<String, UserVO> userDao;//UserDao ���� @Repository �� ������ �̸��� ������ �����Ѵ�. 
		
	@Autowired
	ProductMapper dao;//�ڵ����� dao�� �˻��ؼ� �����Ͷ� .
	
	@Override
	public void register(ProductVO v) {
		dao.insert(v);//UserMapper���� insert�� �Ѵ�. 
	}

	@Override
	public void remove(String k) {
		int n = Integer.parseInt(k);//product�� id�� int���̱� ������ 
		
		dao.delete(n);
	}

	@Override
	public void modify(ProductVO v) {
		dao.update(v);
	}

	@Override
	public ProductVO get(String k) {
		int n = Integer.parseInt(k);//product�� id�� int���̱� ������ 
		
		return dao.select(n);
	}

	@Override
	public List<ProductVO> getAll() {
		
		return dao.selectAll();
	}
	
	
}
