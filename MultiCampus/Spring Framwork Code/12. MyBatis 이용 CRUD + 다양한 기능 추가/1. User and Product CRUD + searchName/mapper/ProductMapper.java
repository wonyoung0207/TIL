package com.mapper;

import java.util.List;

import com.vo.ProductVO;

public interface ProductMapper {//���Ⱑ �Ҹ��� �ڵ������� com.config �� �ִ� usermapper�� ȣ��ȴ�
	//com.config �� �ִ� usermapper.xml ���� �ۼ��� id�� ������ ���� 
	public void insert(ProductVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ProductVO obj) throws Exception;

	public ProductVO select(int n) throws Exception;
	public List<ProductVO> selectAll() throws Exception;
	public List<ProductVO> searchName(String name) throws Exception;
	public List<ProductVO> getRate(double rate) throws Exception;
	
	

}
