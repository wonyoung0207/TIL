package day11_ws;

import java.util.ArrayList;
import java.util.HashMap;

public interface Ifruit {
	
	
	public void create(fruit f) throws NotNumberException;
	public fruit read(String id) throws NotFindException;
	public void update(fruit f) throws NotNumberException,NotFindException;
	public void delete(String id) throws NotNumberException, NotFindException;
	
	

}
