package com.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.UserVO;

//DataBase �� �����ϴ� �ڵ�� 
@Repository("userDao")//userDao�� ����Ҷ�� ���� ��Ÿ����. �̸� �����Ӱ� �������� 
public class UserDao implements Dao<String,UserVO>{

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted : " + v);
	}

	@Override
	public void delete(String k) {
		System.out.println("Deleted : " + k);
	}

	@Override
	public void update(UserVO v) {
		System.out.println("Updated : " + v);//id, password, name �ٵ����� 
		
	}

	@Override
	public UserVO select(String k) {
		UserVO user = new UserVO(k,"pwd02", "kim");
		
		return user;
	}

	@Override
	public List<UserVO> selectAll() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		list.add(new UserVO("id01", "pwd01","kim"));
		list.add(new UserVO("id02", "pwd02","lee"));
		list.add(new UserVO("id03", "pwd03","park"));
		list.add(new UserVO("id04", "pwd04","ahn"));
		list.add(new UserVO("id05", "pwd05","che"));
		
		return list;
	}
	
	

}
