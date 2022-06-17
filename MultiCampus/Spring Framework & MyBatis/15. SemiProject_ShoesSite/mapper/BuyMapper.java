package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.BuyVO;

@Repository
@Mapper
public interface BuyMapper {
	public void insert(BuyVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public void update(BuyVO obj) throws Exception;
	public BuyVO select(int id) throws Exception;
	public List<BuyVO> selectall() throws Exception;
}
