package day02;
//삼각형의 빗변 구하기 -> 제곱근( Math.sqrt) 과 제곱( Math.pow) 사용  
// a^2 * b^2 = c^2
// a 가로    b 세로    c 빗변

public class Triangle {

	public static void main(String[] args) {
		double a = 5;
		double b = 7;
		double triangle = 0;
		
		String t = String.format("%.3f", Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
		triangle = (double) Math.round(Math.sqrt(Math.pow(a,2) + Math.pow(b,2)) * 1000) / 1000;
		//triangle = Math.round(Math.sqrt(Math.pow(a,2) + Math.pow(b,2)) * 1000.0) / 1000.0;
		
		System.out.println(" String fomat 이용 삼각형 빗변 = " + t);
		System.out.println( "Math 이용 삼각형 빗변 = " + triangle);
		System.out.printf("%.3f",Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
		
//		int width = 5; // 가로 5
//		int length = 7; // 세로 7
//		
//		double resultt = Math.sqrt(Math.pow(width, 2)+Math.pow(length, 2));
//		
//		System.out.printf("%.3f",resultt);// printf 는 println과 다른 형식으로 사용됨 
		
	}

}
