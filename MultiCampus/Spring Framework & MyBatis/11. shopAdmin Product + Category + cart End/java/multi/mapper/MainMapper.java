package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainMapper {
	public int getCustCnt() throws Exception;
	public int getProductCnt() throws Exception;
	
	
	
}
