package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.Buy_detailVO;
import com.multi.vo.CartVO;


@Repository
@Mapper
public interface CartMapper {
	public void insert(CartVO cart) throws Exception;
	public void delete(int id) throws Exception;
	public void update(CartVO cart) throws Exception;
	public CartVO select(int id) throws Exception;
	public List <CartVO> selectall() throws Exception;
	public List<CartVO> uidselect(String id) throws Exception;
	public int gettotal(String id) throws Exception; 
	// 김민식 메서드 6/23
	public void deleteall(String uid) throws Exception;
	public int selectcnt(String id) throws Exception;
	// 추가 메서드 6/23
	public int checkcart(CartVO cart) throws Exception;
	// 안원영 추가 회원탈퇴 - uid로된 정보 삭제 
	public void deleteuserid(String id) throws Exception; 
}
