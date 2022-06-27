package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.Filter;
import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface ProductMapper {
	public void insert(ProductVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO obj) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List<ProductVO> selectall() throws Exception;
	
	// 추가함수
	public List<ProductVO> selectfilter(Filter obj) throws Exception;
}
