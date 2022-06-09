package com.multi.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.mapper.MainMapper;

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


}
