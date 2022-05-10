package day10;

import java.util.ArrayList;

public interface DAO {
	
	
	public default void connect() {
		System.out.println("Connect...");
	}
	
	public default void close() {
		System.out.println("Close...");
		
	}
	
	//기능 정의만 함 -> 추상화 
	public void insert(CustomerVO c);//abstarct 형이 묵시적으로 생략되어있다. 
	public void delete(String c);//abstarct 형이 묵시적으로 생략되어있다. 
	public void update(CustomerVO c);
	public CustomerVO select(String id);
	public ArrayList<CustomerVO> select();
	
	
}
