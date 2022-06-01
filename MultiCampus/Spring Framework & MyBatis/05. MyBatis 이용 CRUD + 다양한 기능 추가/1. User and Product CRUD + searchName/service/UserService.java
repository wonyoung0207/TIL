package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.UserMapper;
import com.vo.UserVO;

@org.springframework.stereotype.Service("uservice")
public class UserService implements Service<String,UserVO>{
	//Dao<String, UserVO> userDao;//UserDao 에서 @Repository 로 설정한 이름을 변수로 설정한다. 
		
	@Autowired
	UserMapper dao;//자동으로 dao를 검색해서 가져와라 .
	
	@Override
	public void register(UserVO v) throws Exception{
		dao.insert(v);//UserMapper에게 insert를 한다. 
	}

	@Override
	public void remove(String k) throws Exception{
		dao.delete(k);
	}

	@Override
	public void modify(UserVO v) throws Exception{
		dao.update(v);
	}

	@Override
	public UserVO get(String k) throws Exception{
		return dao.select(k);
	}

	@Override
	public List<UserVO> getAll() throws Exception{
		
		return dao.selectAll();
	}
	
	public List<UserVO> searchName(String name) throws Exception{
		return dao.searchName(name);
	}
	
	
}
