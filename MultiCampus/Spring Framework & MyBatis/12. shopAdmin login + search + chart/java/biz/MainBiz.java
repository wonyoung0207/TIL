package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.mapper.MainMapper;
import com.multi.vo.AdminVO;
import com.multi.vo.ProductVO;

@Service
public class MainBiz implements MainMapper{

	@Autowired
	MainMapper dao;
	
	@Override
	public int getCustCnt() throws Exception{
		return dao.getCustCnt();
	}

	@Override
	public int getProductCnt() throws Exception{
		return dao.getProductCnt();
		
	}
	
	public List<ProductVO> searchproduct(String txt) throws Exception{
		return dao.searchproduct(txt);
	}
	
	public AdminVO getadmin(String id) throws Exception{
		return dao.getadmin(id);
	}

}
