package day12_ws;

public interface Ifruit <K,V>{
	
//	public void create(fruit f) throws Exception;
//	public fruit read(String id)throws Exception;
//	public void update(fruit f)throws Exception;
//	public void delete(String id)throws Exception;
//	
	
	public void create(V v) throws Exception;
	public V read(K k)throws Exception;
	public void update(V v)throws Exception;
	public void delete(K k)throws Exception;
	
	
	

}
