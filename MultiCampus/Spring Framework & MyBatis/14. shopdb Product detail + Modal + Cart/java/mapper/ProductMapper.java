package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.ProductAVGVO;
import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface ProductMapper {

	public void insert(ProductVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ProductVO obj) throws Exception;
	public ProductVO select(int obj) throws Exception;
	public List<ProductVO> selectall() throws Exception;
	public List<ProductVO> selectall2() throws Exception;
	public List<ProductAVGVO> selectAVG() throws Exception;
	public List<ProductVO> selectproduct(int id) throws Exception;
	
}
