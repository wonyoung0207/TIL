package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CateVO;

@Repository
@Mapper
public interface CateMapper {
	public void insert(CateVO cate) throws Exception;
	public void delete(int id) throws Exception;
	public void update(CateVO cate) throws Exception;
	public CateVO select(int id) throws Exception;
	public List <CateVO> selectall() throws Exception;
}
