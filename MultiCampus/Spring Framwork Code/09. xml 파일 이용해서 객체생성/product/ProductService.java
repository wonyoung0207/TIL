package com.product;

import java.util.List;

import com.frame.Dao;
import com.frame.Service;
import com.vo.ProductVO;

public class ProductService implements Service<Integer, ProductVO>{
	Dao<Integer,ProductVO> dao;

	public ProductService(Dao<Integer, ProductVO> dao) {
		this.dao = dao;
	}
	
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
