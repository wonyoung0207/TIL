package day11;

public class NotFoundException extends Exception{

	public NotFoundException() {
		
	}
	public NotFoundException(String msg) {
		super(msg);//���� Exception���� msg�� ������ ������, 
	}
	
}
