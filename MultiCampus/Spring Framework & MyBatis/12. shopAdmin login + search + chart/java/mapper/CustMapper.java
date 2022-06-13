package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@Repository// Spring boot에 연결되기 위해 
@Mapper//mybatis에 연결되기 위해 
public interface CustMapper {

	public void insert(CustVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(CustVO obj) throws Exception;
	public CustVO select(String obj) throws Exception;
	public List<CustVO> selectall() throws Exception;
	
}
