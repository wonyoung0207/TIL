package day12_Oracle;

import java.util.ArrayList;

public class ItemDAO implements DAO {

	@Override
	public void insert(Object obj) throws Exception {
		connect();
		
		ItemVO item = (ItemVO)obj;
		System.out.println(item);
		System.out.println("Inserted...");
		
		close();
		
	}

	@Override
	public void delete(Object obj) throws Exception {
		connect();
		
		Integer i = (Integer)obj;
		int id = i.intValue();
		System.out.println(id + "값이 삭제 되었습니다. ");
		
		close();
	}

	@Override
	public void update(Object obj) throws Exception {

	}

	@Override
	public Object select(Object obj) throws Exception {
		
		return null;
	}

	@Override
	public ArrayList<Object> selectAll() throws Exception {
		
		return null;
	}

}
