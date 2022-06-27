package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.Buy_detailVO;

@Repository
@Mapper
public interface Buy_detailMapper {
	public void insert(Buy_detailVO cust) throws Exception;
	public void delete(int id) throws Exception;
	public void update(Buy_detailVO cust) throws Exception;
	public Buy_detailVO select(int id) throws Exception;
	public List<Buy_detailVO> selectall() throws Exception;
	//김민식추가
	public List<Buy_detailVO> selectid(int oid);
	public List<Buy_detailVO> getbuy_detail(String id) throws Exception;
	public void deletebuyid(int id) throws Exception;
	// 06.24 안원영 추가 - buy_detail 페이지
	public List<Buy_detailVO> selectdetailproduct(int oid);
}
