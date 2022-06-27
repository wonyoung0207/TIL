package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.CartMapper;
import com.multi.vo.Buy_detailVO;
import com.multi.vo.CartVO;

@Service("cartbiz")
public class CartBiz implements Biz<Integer, CartVO> {

	@Autowired
	CartMapper dao;
	
	@Override
	public void register(CartVO v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(CartVO v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
		
	}

	@Override
	public CartVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<CartVO> get() throws Exception {

		return dao.selectall();
	}
	public List<CartVO> uidselect(String id) throws Exception{
		return dao.uidselect(id);
	}
	public int gettotal(String id) throws Exception{
		return dao.gettotal(id);
	}
	// 김민식 추가 메서드 6/24
	public void deleteall(String uid) throws Exception {
		dao.deleteall(uid);
		
	}
	// 추가 메서드 6/23
	public int checkcart(CartVO v) throws Exception{
		return dao.checkcart(v);
	}
	// 안원영 추가 회원탈퇴 - uid로된 정보 모두 삭제 
	public void deleteuserid(String id) throws Exception{
		dao.deleteuserid(id);
	}
	public int selectcnt(String uid) throws Exception{
		return dao.selectcnt(uid);
	}
	
}