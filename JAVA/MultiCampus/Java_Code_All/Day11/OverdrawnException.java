package day11;

public class OverdrawnException extends Exception{

	public OverdrawnException() {
		
	}
	public OverdrawnException(String msg) {
		super(msg);//상위 Exception으로 msg의 내용을 보낸다, 
	}
	
}
