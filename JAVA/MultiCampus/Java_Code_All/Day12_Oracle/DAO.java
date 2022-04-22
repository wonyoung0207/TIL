package day12_Oracle;

import java.util.ArrayList;

public interface DAO {
	
	
	public default void connect() {
		System.out.println("Connect...");
	}
	
	public default void close() {
		System.out.println("Close...");
		
	}
	
	//��� ���Ǹ� �� -> �߻�ȭ 
	public void insert(Object obj) throws Exception;// id �ߺ��� ������ �߻��� �� �ִ�. 
	public void delete(Object obj) throws Exception;// ������ ���̵� ���� �� �ִ�.  
	public void update(Object obj) throws Exception;//������Ʈ �� ���̵� ���� �� �ִ�.
	public Object select(Object obj) throws Exception;//������ ���̵� ���� �� �ִ�.
	public ArrayList<Object> selectAll() throws Exception;
	
	
}
