package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.Addr_listMapper;
import com.multi.vo.Addr_listVO;
import com.multi.vo.CustVO;

@Service("addr_listbiz")
public class Addr_listBiz implements Biz<Integer,Addr_listVO>{

	@Autowired
	Addr_listMapper dao;
	
	@Override
	public void register(Addr_listVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(Addr_listVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public Addr_listVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<Addr_listVO> get() throws Exception {
		return dao.selectall();
	}
	public Addr_listVO getcustinfo(String id) throws Exception {
		return dao.getcustinfo(id);
	}
	// 안원영 추가 
	public List<Addr_listVO> getcustinfoAll(String id) throws Exception {
		return dao.getcustinfoAll(id);
	}
	// 안원영 추가 회원탈퇴 - 모든 uid 삭제 
	public void deleteuserid(String id) throws Exception {
		dao.deleteuserid(id);
	}
}
