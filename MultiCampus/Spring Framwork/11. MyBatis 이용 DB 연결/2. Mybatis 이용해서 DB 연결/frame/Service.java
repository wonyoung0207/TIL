package com.frame;

import java.util.List;

public interface Service<K,V> {
	
	public void register(V v);
	public void remove(K k);
	public void modify(V v);
	public V get(K k);
	public List<V> getAll();
	
	

}
