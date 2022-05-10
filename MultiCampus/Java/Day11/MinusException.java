package day11;

public class MinusException extends Exception{

	public MinusException() {
		
	}
	public MinusException(String msg) {
		super(msg);//상위 Exception으로 msg의 내용을 보낸다, 
	}
	
}
