package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.ReviewVO;

@Repository
@Mapper
public interface ReviewMapper {
	public void insert(ReviewVO review) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ReviewVO review) throws Exception;
	public ReviewVO select(int id) throws Exception;
	public List <ReviewVO> selectall() throws Exception;
	
	// 추가
	public List <ReviewVO> selectproduct(int pid) throws Exception;
	public int selectstaravg(int pid) throws Exception;
	public int selectreviewcnt(int pid) throws Exception;
	
	//안원영 추가 
	public List<ReviewVO> selectuserall(String id) throws Exception;
	//안원영 추가 
	public void deleteuserid(String id) throws Exception;
}
