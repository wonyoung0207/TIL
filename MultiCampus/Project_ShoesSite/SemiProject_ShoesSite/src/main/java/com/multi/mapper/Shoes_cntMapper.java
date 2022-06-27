package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.Shoes_cntVO;

@Repository
@Mapper
public interface Shoes_cntMapper {
	public void insert(Shoes_cntVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public void update(Shoes_cntVO obj) throws Exception;
	public Shoes_cntVO select(int id) throws Exception;
	public List<Shoes_cntVO> selectall() throws Exception;
	public List<Shoes_cntVO> selectproduct(int pid) throws Exception;

	// 추가 메서드 서예린 6/23
	public int checkcnt(Shoes_cntVO obj) throws Exception;
}
