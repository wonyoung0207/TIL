package day12_Oracle;

import java.util.ArrayList;

public interface DAO {
	
	
	public default void connect() {
		System.out.println("Connect...");
	}
	
	public default void close() {
		System.out.println("Close...");
		
	}
	
	//기능 정의만 함 -> 추상화 
	public void insert(Object obj) throws Exception;// id 중복의 문제가 발생할 수 있다. 
	public void delete(Object obj) throws Exception;// 삭제할 아이디가 없을 수 있다.  
	public void update(Object obj) throws Exception;//업데이트 할 아이디가 없을 수 있다.
	public Object select(Object obj) throws Exception;//가져올 아이디가 없을 수 있다.
	public ArrayList<Object> selectAll() throws Exception;
	
	
}
