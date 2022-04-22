package day12_Oracle2;

import java.util.List;

public interface DAO<K,V> {//���׸����� ���� 
		
	//��� ���Ǹ� �� -> �߻�ȭ 
	//CRUD
	public void insert(V v) throws Exception;// id �ߺ��� ������ �߻��� �� �ִ�. 
	public void delete(K k) throws Exception;// ������ ���̵� ���� �� �ִ�.  
	public void update(V v) throws Exception;//������Ʈ �� ���̵� ���� �� �ִ�.
	public V select(K k) throws Exception;//������ ���̵� ���� �� �ִ�.
	public List<V> selectAll() throws Exception;
	
	
}
