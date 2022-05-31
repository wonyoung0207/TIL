package com.mapper;

import java.util.List;

import com.vo.ProductVO;

public interface ProductMapper {//여기가 불리면 자동적으로 com.config 에 있는 usermapper가 호출된다
	//com.config 에 있는 usermapper.xml 에서 작성한 id를 변수에 저장 
	public void insert(ProductVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ProductVO obj) throws Exception;

	public ProductVO select(int n) throws Exception;
	public List<ProductVO> selectAll() throws Exception;
	public List<ProductVO> searchName(String name) throws Exception;
	public List<ProductVO> getRate(double rate) throws Exception;
	
	

}
