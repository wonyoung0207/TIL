package day10;

import java.util.ArrayList;

public interface DAO {
	
	
	public default void connect() {
		System.out.println("Connect...");
	}
	
	public default void close() {
		System.out.println("Close...");
		
	}
	
	//��� ���Ǹ� �� -> �߻�ȭ 
	public void insert(CustomerVO c);//abstarct ���� ���������� �����Ǿ��ִ�. 
	public void delete(String c);//abstarct ���� ���������� �����Ǿ��ִ�. 
	public void update(CustomerVO c);
	public CustomerVO select(String id);
	public ArrayList<CustomerVO> select();
	
	
}
