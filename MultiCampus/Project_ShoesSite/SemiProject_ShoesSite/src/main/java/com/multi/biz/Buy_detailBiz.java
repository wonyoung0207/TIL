package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.Buy_detailMapper;
import com.multi.vo.Buy_detailVO;

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
	// 김민식추가
	public List<Buy_detailVO> selectid(Integer k) throws Exception{
		return dao.selectid(k);
	}
	public List<Buy_detailVO> getbuy_detail(String id) throws Exception {

		return dao.getbuy_detail(id);
	}
	// 안원영 추가 - 배송현황 주문취소 선택 
	public void deletebuyid(Integer k) throws Exception {
		dao.deletebuyid(k);
	}
	// 06.24 안원영 추가 - buy_detail 페이지 추가 
	public List<Buy_detailVO> selectdetailproduct(int oid) throws Exception {
		return dao.selectdetailproduct(oid);
	}
}
