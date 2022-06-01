package com.mapper;

import java.util.List;

import com.vo.ProductVO;
import com.vo.UserVO;

public interface UserMapper {
	public void insert(UserVO v) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(UserVO obj) throws Exception;

	public UserVO select(String obj) throws Exception;
	public List<UserVO> selectAll() throws Exception;
	public List<UserVO> searchName(String name) throws Exception;
	

}
