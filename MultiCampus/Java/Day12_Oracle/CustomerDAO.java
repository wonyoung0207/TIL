package day12_Oracle;

import java.util.ArrayList;

public class CustomerDAO implements DAO {

	@Override
	public void insert(Object obj) throws Exception {
		connect();
		
		CustomerVO c = (CustomerVO) obj;//Ÿ��ĳ���� ����� CustomerVO�� ������ ����� ���ִ�. 
		System.out.println(c + "��ü�� �ԷµǾ����ϴ�. ");

		close();
	}

	@Override
	public void delete(Object obj) throws Exception {
		connect();
		
		String id = (String) obj;//Ÿ��ĳ���� ����� CustomerVO�� ������ ����� ���ִ�. 
		System.out.println(id + "��ü�� �����Ǿ����ϴ�. ");

		close();
	}

	@Override
	public void update(Object obj) throws Exception {

	}

	@Override
	public Object select(Object obj) throws Exception {
		connect();
		
		String id = (String) obj;//Ÿ��ĳ���� ����� CustomerVO�� ������ ����� ���ִ�.
		CustomerVO c = new CustomerVO(id,"pwd01","lee");
		

		close();
		
		return c;
	}

	@Override
	public ArrayList<Object> selectAll() throws Exception {
		connect();
		
		ArrayList<Object> list = new ArrayList<>();
		list.add(new CustomerVO("id01", "pwd01", "01"));
		list.add(new CustomerVO("id02", "pwd02", "02"));
		list.add(new CustomerVO("id03", "pwd03", "03"));
		
		
		close();
		return list;
	}

}
