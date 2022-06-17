package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.Buy_detailMapper;
import com.multi.mapper.CustMapper;
import com.multi.vo.Buy_detailVO;
import com.multi.vo.CustVO;

@Service("buy_detailbiz")
public class Buy_detailBiz implements Biz<Integer,Buy_detailVO>{

	@Autowired
	Buy_detailMapper dao;
	
	@Override
	public void register(Buy_detailVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(Buy_detailVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public Buy_detailVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<Buy_detailVO> get() throws Exception {
		return dao.selectall();
	}

}
