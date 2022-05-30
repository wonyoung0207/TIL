package com.user;

import java.util.List;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;

public class UserService implements Service<String,UserVO>{
	Dao<String, UserVO> dao;//DI ������ ���� -> XML ���Ͽ��� UserDao �� ��ü�� dao�κ����� �̰����� �޴´�.
	
	//���� ��ü�� �������� �ʰ� spring�� �̿��� ��ü�� �����Ѵ�. 
	
	public Dao<String, UserVO> getDao() {
		return dao;
	}

	public void setDao(Dao<String, UserVO> dao) {
		this.dao = dao;
	}

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
	
}
