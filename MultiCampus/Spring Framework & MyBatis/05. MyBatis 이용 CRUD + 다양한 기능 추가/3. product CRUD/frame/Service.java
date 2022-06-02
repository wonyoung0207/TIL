package com.frame;

import java.util.List;

public interface Service<K,V> {
	
	public void register(V v) throws Exception;
	public void remove(K k) throws Exception;
	public void modify(V v) throws Exception;
	public V get(K k) throws Exception;
	public List<V> getAll() throws Exception;
	
	

}
