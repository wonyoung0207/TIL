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
	public void register(UserVO v) {
		dao.insert(v);//UserMapper���� insert�� �Ѵ�. 
	}

	@Override
	public void remove(String k) {
		dao.delete(k);
	}

	@Override
	public void modify(UserVO v) {
		dao.update(v);
	}

	@Override
	public UserVO get(String k) {
		return dao.select(k);
	}

	@Override
	public List<UserVO> getAll() {
		
		return dao.selectAll();
	}
	
	
}
