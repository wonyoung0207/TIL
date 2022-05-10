package day11;

public class NotFoundException extends Exception{

	public NotFoundException() {
		
	}
	public NotFoundException(String msg) {
		super(msg);//상위 Exception으로 msg의 내용을 보낸다, 
	}
	
}
