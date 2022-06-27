package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.Shoes_cntMapper;
import com.multi.vo.Shoes_cntVO;

@Service
public class Shoes_cntBiz implements Biz<Integer,Shoes_cntVO> {

	@Autowired
	Shoes_cntMapper dao;
	
	@Override
	public void register(Shoes_cntVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(Shoes_cntVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public Shoes_cntVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<Shoes_cntVO> get() throws Exception {
		return dao.selectall();
	}
	
	public List<Shoes_cntVO> getproduct(Integer k) throws Exception {
		return dao.selectproduct(k);
	}
	public int checkcnt(Shoes_cntVO v) throws Exception {
		return dao.checkcnt(v);
	}

}
