package day11;

public class CalcMain {

	public static void main(String[] args) {
		Calc calc = new Calc();
		
		int a = 10;
		int b = 5;
		double result = 0;
		
		try {
			result = calc.div(a,b);
			
		}catch(ArithmeticException e) {
			System.out.println("�и� 0 �Դϴ�. ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}

}
