package day12_Oracle2;

import java.util.List;

public interface DAO<K,V> {//제네릭으로 선언 
		
	//기능 정의만 함 -> 추상화 
	//CRUD
	public void insert(V v) throws Exception;// id 중복의 문제가 발생할 수 있다. 
	public void delete(K k) throws Exception;// 삭제할 아이디가 없을 수 있다.  
	public void update(V v) throws Exception;//업데이트 할 아이디가 없을 수 있다.
	public V select(K k) throws Exception;//가져올 아이디가 없을 수 있다.
	public List<V> selectAll() throws Exception;
	
	
}
