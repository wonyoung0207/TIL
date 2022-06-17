package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.Buy_detailVO;
import com.multi.vo.CustVO;

@Repository
@Mapper
public interface Buy_detailMapper {
	public void insert(Buy_detailVO cust) throws Exception;
	public void delete(int id) throws Exception;
	public void update(Buy_detailVO cust) throws Exception;
	public Buy_detailVO select(int id) throws Exception;
	public List<Buy_detailVO> selectall() throws Exception;
}
