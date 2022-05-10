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
			System.out.println("인덱스 오류입니다. ");
		}catch(NullPointerException e) {
			System.out.println("Null 을 가리킵니다.  소문자로 변경이 불가합니다. ");
		}finally{
			System.out.println(str.toLowerCase());
			
		}
	}

}
