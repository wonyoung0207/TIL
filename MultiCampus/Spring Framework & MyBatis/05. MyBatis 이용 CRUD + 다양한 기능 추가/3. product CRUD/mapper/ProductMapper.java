package com.mapper;

import java.util.List;

import org.eclipse.core.internal.runtime.Product;

import com.vo.ProductVO;

public interface ProductMapper {
	public void insert(ProductVO p) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO p) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List<ProductVO> selectAll() throws Exception;
}
