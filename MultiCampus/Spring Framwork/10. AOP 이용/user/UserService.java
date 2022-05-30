package com.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;

@org.springframework.stereotype.Service("uservice")
public class UserService implements Service<String,UserVO>{
	//Dao<String, UserVO> userDao;//UserDao ���� @Repository �� ������ �̸��� ������ �����Ѵ�. 
	
	@Autowired
	Dao<String, UserVO> dao;//�ڵ����� dao�� �˻��ؼ� �����Ͷ� . 
	
	
	
	@Override
	public void register(UserVO v) {
		dao.insert(v);
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
	
//	@Override
//	public void register(UserVO v) {
//		userDao.insert(v);
//	}
//
//	@Override
//	public void remove(String k) {
//		userDao.delete(k);
//	}
//
//	@Override
//	public void modify(UserVO v) {
//		userDao.update(v);
//	}
//
//	@Override
//	public UserVO get(String k) {
//		return userDao.select(k);
//	}
//
//	@Override
//	public List<UserVO> getAll() {
//		
//		return userDao.selectAll();
//	}
	
}
