package com.user;

import java.util.List;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;

public class UserService implements Service<String,UserVO>{
	Dao<String, UserVO> dao;//DI 의존성 주입 -> XML 파일에서 UserDao 의 객체를 dao로보내서 이곳에서 받는다.
	
	//실제 객체를 생성하지 않고 spring을 이용해 객체를 주입한다. 
	
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
