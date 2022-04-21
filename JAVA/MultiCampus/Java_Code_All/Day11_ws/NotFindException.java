package day11_ws;

public class NotFindException extends Exception{

	public NotFindException() {
		
	}
	public NotFindException(String msg) {
		super(msg);//상위 Exception으로 msg의 내용을 보낸다, 
	}
	
}
