package day02;
//�ﰢ���� ���� ���ϱ� -> ������( Math.sqrt) �� ����( Math.pow) ���  
// a^2 * b^2 = c^2
// a ����    b ����    c ����

public class Triangle {

	public static void main(String[] args) {
		double a = 5;
		double b = 7;
		double triangle = 0;
		
		String t = String.format("%.3f", Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
		triangle = (double) Math.round(Math.sqrt(Math.pow(a,2) + Math.pow(b,2)) * 1000) / 1000;
		//triangle = Math.round(Math.sqrt(Math.pow(a,2) + Math.pow(b,2)) * 1000.0) / 1000.0;
		
		System.out.println(" String fomat �̿� �ﰢ�� ���� = " + t);
		System.out.println( "Math �̿� �ﰢ�� ���� = " + triangle);
		System.out.printf("%.3f",Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
		
//		int width = 5; // ���� 5
//		int length = 7; // ���� 7
//		
//		double resultt = Math.sqrt(Math.pow(width, 2)+Math.pow(length, 2));
//		
//		System.out.printf("%.3f",resultt);// printf �� println�� �ٸ� �������� ���� 
		
	}

}
