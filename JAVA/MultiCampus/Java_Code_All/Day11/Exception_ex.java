package day11;

public class Exception_ex {

	public static void main(String[] args) {
		String str = null;
		
		try {
			str = "ABC";
			int a[] = new int[3];
			for( int i =0; i<=a.length; i++	) {
				System.out.println(a[i]);
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("�ε��� �����Դϴ�. ");
		}catch(NullPointerException e) {
			System.out.println("Null �� ����ŵ�ϴ�.  �ҹ��ڷ� ������ �Ұ��մϴ�. ");
		}finally{
			System.out.println(str.toLowerCase());
			
		}
	}

}
