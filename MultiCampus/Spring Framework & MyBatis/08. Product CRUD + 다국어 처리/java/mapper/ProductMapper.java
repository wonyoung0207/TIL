package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CustVO;
import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface ProductMapper {
	public void insert(ProductVO product) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO product) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List <ProductVO> selectall() throws Exception;
	public int selectcnt() throws Exception;
	
}
