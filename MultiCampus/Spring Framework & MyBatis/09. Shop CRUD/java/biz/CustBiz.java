package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.CustMapper;
import com.multi.vo.CustVO;
@Service
//Spring container 안에서 spring 컴포넌트로 만들기 위해 사용 -> 하나의 컴포넌트로 모두 지정하다 보면 나중에 어떤 기능을 하는 컴포넌트인지 햇갈리게 됨.
// service가 mapper(database) 와 Ui(화면) 의 중점이 되서  
// service = 업무 진행시 같이 진행되야 할 일을 처리 하는 곳 
public class CustBiz implements Biz<String,CustVO>{

	@Autowired
	CustMapper dao;
	
	@Override
	public void register(CustVO v) throws Exception {
		// 데이터 검증 	
		dao.insert(v);
		// 메일 전송 
	}

	@Override
	public void modify(CustVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public CustVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<CustVO> get() throws Exception {
		return dao.selectall();
	}

}
