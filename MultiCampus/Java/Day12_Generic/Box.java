package day12_Generic;

public class Box<T> {
	T obj;
	
	public Box() {
		
	}
	
	public void setBox(T c) {
		obj = c;
		
	}
	
	public T getBox(){
		return obj;
	}

}
