package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.Addr_listVO;

@Repository
@Mapper
public interface Addr_listMapper {
	public void insert(Addr_listVO addr) throws Exception;
	public void delete(int id) throws Exception;
	public void update(Addr_listVO addr) throws Exception;
	public Addr_listVO select(int id) throws Exception;
	public List<Addr_listVO> selectall() throws Exception;
	public Addr_listVO getcustinfo(String id) throws Exception;
	// 안원영 추가 
	public List<Addr_listVO> getcustinfoAll(String id) throws Exception;
	// 안원영 추가 
	public void deleteuserid(String id) throws Exception;
}
