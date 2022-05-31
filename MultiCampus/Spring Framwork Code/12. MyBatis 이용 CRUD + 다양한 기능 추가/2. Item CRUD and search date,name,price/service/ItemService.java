package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.ItemMapper;
import com.vo.ItemVO;

@org.springframework.stereotype.Service("itemservice")
public class ItemService implements Service<Integer,ItemVO>{
	//Dao<String, ItemVO> userDao;//UserDao ���� @Repository �� ������ �̸��� ������ �����Ѵ�. 
		
	@Autowired
	ItemMapper dao;//�ڵ����� dao�� �˻��ؼ� �����Ͷ� .
	
	@Override
	public void register(ItemVO v) throws Exception{
		dao.insert(v);//UserMapper���� insert�� �Ѵ�. 
	}

	@Override
	public void remove(Integer k) throws Exception{
		dao.delete(k);
	}

	@Override
	public void modify(ItemVO v) throws Exception{
		dao.update(v);
	}

	@Override
	public ItemVO get(Integer k) throws Exception{
		return dao.select(k);
	}

	@Override
	public List<ItemVO> getAll() throws Exception{
		
		return dao.selectAll();
	}
	
	public List<ItemVO> searchName(String name) throws Exception{
		return dao.searchName(name);
	}
	
	public List<ItemVO> searchPrice(Map<String,Integer> map) throws Exception{
		return dao.searchPrice(map);
	}
	
	public List<ItemVO> searchDate(Date date) throws Exception{
		return dao.searchDate(date);
	}
	
	
	
}
