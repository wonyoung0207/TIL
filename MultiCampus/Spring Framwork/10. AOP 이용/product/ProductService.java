package com.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.frame.Service;
import com.vo.ProductVO;

@org.springframework.stereotype.Service("pservice")
public class ProductService implements Service<Integer, ProductVO>{
	
	@Autowired
	Dao<Integer,ProductVO> dao;

	@Override
	public void register(ProductVO v) {
		dao.insert(v);
	}

	@Override
	public void remove(Integer k) {
		dao.delete(k);
	}

	@Override
	public void modify(ProductVO v) {
		dao.update(v);
	}

	@Override
	public ProductVO get(Integer k) {
		
		return dao.select(k);
	}

	@Override
	public List<ProductVO> getAll() {
		
		return dao.selectAll();
	}
	

}
