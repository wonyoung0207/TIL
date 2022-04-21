package day11;

import java.util.ArrayList;

public interface DAO {
	
	
	public default void connect() {
		System.out.println("Connect...");
	}
	
	public default void close() {
		System.out.println("Close...");
		
	}
	
	//기능 정의만 함 -> 추상화 
	public void insert(CustomerVO c) throws DuplicatedIDException;// id 중복의 문제가 발생할 수 있다. 
	public void delete(String c) throws NotFoundException;// 삭제할 아이디가 없을 수 있다.  
	public void update(CustomerVO c) throws NotFoundException;//업데이트 할 아이디가 없을 수 있다.
	public CustomerVO select(String id) throws NotFoundException;//가져올 아이디가 없을 수 있다.
	public ArrayList<CustomerVO> selectAll() throws NotFoundException;
	
	
}
