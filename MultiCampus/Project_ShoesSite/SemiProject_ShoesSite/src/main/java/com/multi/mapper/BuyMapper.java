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
	//김민식추가
	public int selectid() throws Exception;
	// 안원영 추가 
	public List<BuyVO> selectUserBuy(String id) throws Exception;
	//06.27 안원영 추가 수정
	public List<BuyVO> selectUserBuyGroup(String id) throws Exception;
}
