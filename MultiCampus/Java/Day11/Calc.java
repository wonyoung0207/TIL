package day11;

public class Calc {
	
	
	public void sum(int a, int b) {
		
	}
	public double div(int a, int b) throws Exception{//실수를 0으로 나누면 무한대, 정수는 0으로 못나눔 
		int result=0;
		try {
			result = a/b;
		}catch(Exception e){
			throw e;//오류를 이 함수를 호출한 상위로 던진다. 항상 thorws 와 함께 쓰인다.  
		}
		return result;
		
	}
}
