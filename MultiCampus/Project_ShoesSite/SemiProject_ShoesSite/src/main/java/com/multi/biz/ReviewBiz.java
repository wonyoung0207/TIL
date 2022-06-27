package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.ReviewMapper;
import com.multi.vo.ReviewVO;

@Service("reviewbiz")
public class ReviewBiz implements Biz<Integer, ReviewVO> {

	@Autowired
	ReviewMapper dao;
	
	@Override
	public void register(ReviewVO v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(ReviewVO v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
		
	}

	@Override
	public ReviewVO get(Integer k) throws Exception {
		return dao.select(k);
		
	
	}

	@Override
	public List<ReviewVO> get() throws Exception {
		return dao.selectall();
	}
	
	
	// 추가
	public List<ReviewVO> getproduct(Integer k) throws Exception {
		return dao.selectproduct(k);
	}
	
	public int getstaravg(Integer k) throws Exception {
		return dao.selectstaravg(k);
	}
	
	public int getreviewcnt(Integer k) throws Exception {
		return dao.selectreviewcnt(k);
	}
	// 안원영 추가 mypage ( 리뷰 모아보기 ) 
	public List<ReviewVO> selectuserall(String id) throws Exception {
		return dao.selectuserall(id);
	}
	
	// 안원영 추가 회원탈퇴
	public void deleteuserid(String id) throws Exception{
		dao.deleteuserid(id);
	}
	
}