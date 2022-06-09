package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CateVO;

@Repository
@Mapper
public interface CateMapper {

	public void insert(CateVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(CateVO obj) throws Exception;
	public CateVO select(int obj) throws Exception;
	public List<CateVO> selectall() throws Exception;
	public List<CateVO> selectmain() throws Exception;
	
}
