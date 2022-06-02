package com.multi.frame;

import java.util.List;

import com.multi.vo.CustVO;


public interface Biz<K,V> {
	public void register(V v) throws Exception;
	public void modify(V v) throws Exception;
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> getAll() throws Exception;
	
}
