package day11_ws;

public class MyNullPointException extends Exception{

	public MyNullPointException() {
		
	}
	public MyNullPointException(String msg) {
		super(msg);//상위 Exception으로 msg의 내용을 보낸다, 
	}
	
}
