package day11;

import java.util.ArrayList;

public interface DAO {
	
	
	public default void connect() {
		System.out.println("Connect...");
	}
	
	public default void close() {
		System.out.println("Close...");
		
	}
	
	//��� ���Ǹ� �� -> �߻�ȭ 
	public void insert(CustomerVO c) throws DuplicatedIDException;// id �ߺ��� ������ �߻��� �� �ִ�. 
	public void delete(String c) throws NotFoundException;// ������ ���̵� ���� �� �ִ�.  
	public void update(CustomerVO c) throws NotFoundException;//������Ʈ �� ���̵� ���� �� �ִ�.
	public CustomerVO select(String id) throws NotFoundException;//������ ���̵� ���� �� �ִ�.
	public ArrayList<CustomerVO> selectAll() throws NotFoundException;
	
	
}
