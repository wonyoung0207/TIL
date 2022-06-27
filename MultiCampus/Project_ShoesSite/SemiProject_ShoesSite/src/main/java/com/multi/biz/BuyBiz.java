package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.BuyMapper;
import com.multi.vo.BuyVO;

@Service
public class BuyBiz implements Biz<Integer, BuyVO> {

	@Autowired
	BuyMapper dao;
	
	@Override
	public void register(BuyVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(BuyVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public BuyVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<BuyVO> get() throws Exception {
		return dao.selectall();
	}
	public int selectid() throws Exception{
		return dao.selectid();
	}
	public List<BuyVO> selectUserBuy(String id) throws Exception {
		return dao.selectUserBuy(id);
	}
	// 06.27 안원영 추가
	public List<BuyVO> selectUserBuyGroup(String id) throws Exception {
		return dao.selectUserBuyGroup(id);
	}
}
