package com.mapper;

import java.util.List;

import com.vo.ProductVO;

public interface ProductMapper {//���Ⱑ �Ҹ��� �ڵ������� com.config �� �ִ� usermapper�� ȣ��ȴ�
	//com.config �� �ִ� usermapper.xml ���� �ۼ��� id�� ������ ���� 
	public void insert(ProductVO obj);
	public void delete(int obj);
	public void update(ProductVO obj);

	public ProductVO select(int n);
	public List<ProductVO> selectAll();
	

}
