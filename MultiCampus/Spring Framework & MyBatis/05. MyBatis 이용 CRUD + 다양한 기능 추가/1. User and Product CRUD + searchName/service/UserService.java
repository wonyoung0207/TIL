package com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.UserMapper;
import com.vo.UserVO;

@org.springframework.stereotype.Service("uservice")
public class UserService implements Service<String,UserVO>{
	//Dao<String, UserVO> userDao;//UserDao ���� @Repository �� ������ �̸��� ������ �����Ѵ�. 
		
	@Autowired
	UserMapper dao;//�ڵ����� dao�� �˻��ؼ� �����Ͷ� .
	
	@Override
	public void register(UserVO v) throws Exception{
		dao.insert(v);//UserMapper���� insert�� �Ѵ�. 
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
