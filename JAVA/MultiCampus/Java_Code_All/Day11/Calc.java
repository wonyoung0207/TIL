package day11;

public class Calc {
	
	
	public void sum(int a, int b) {
		
	}
	public double div(int a, int b) throws Exception{//�Ǽ��� 0���� ������ ���Ѵ�, ������ 0���� ������ 
		int result=0;
		try {
			result = a/b;
		}catch(Exception e){
			throw e;//������ �� �Լ��� ȣ���� ������ ������. �׻� thorws �� �Բ� ���δ�.  
		}
		return result;
		
	}
}
