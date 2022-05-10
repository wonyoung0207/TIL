package day12;

public class StringTest {

	public static void main(String[] args) {
		String str = "abcdef   gh";
		
		//3번쨰에 있는 char를 가져옴 -> d 출력
		System.out.println(str.charAt(3));
		
		System.out.println(str.indexOf('c'));
		//c는 몇번쨰 인덱스에 있는지 
		
		String str2 = str.substring(1,3);//1~3전까지만 출력 
		System.out.println(str2);
		
		
		String str3 = str.trim();//공백제거
		System.out.println(str3);
		
		
		char cs[] = str.toCharArray();//String을 char형의 배열을 만들어 리턴해준다.
		for(char d : cs) {
			System.out.print(d + ",");
		}
		System.out.println();
		
		
		String ss = "1,2,3,4,5";
		String result[] = ss.split(",");//, 를 기준으로 잘라낸후 배열로 리턴한다. 
		for(String st : result) {
			System.out.print(st);
			
		}
		
		
		
		

	}

}
