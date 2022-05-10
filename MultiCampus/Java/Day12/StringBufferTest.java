package day12;

public class StringBufferTest {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("abcdef");
		
		sb.append("ghi");
		System.out.println(sb);
		sb.reverse();
		System.out.println(sb);
		
		sb.delete(2,3);//2~3전까지의 내용을 삭제 
		System.out.println(sb);
		
	}

}
