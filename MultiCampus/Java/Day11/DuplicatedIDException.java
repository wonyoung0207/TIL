package day11;

public class DuplicatedIDException extends Exception{

	public DuplicatedIDException() {
		
	}
	public DuplicatedIDException(String msg) {
		super(msg);//상위 Exception으로 msg의 내용을 보낸다, 
	}
	
}
