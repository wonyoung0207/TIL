package day12;

public class StringTest {

	public static void main(String[] args) {
		String str = "abcdef   gh";
		
		//3������ �ִ� char�� ������ -> d ���
		System.out.println(str.charAt(3));
		
		System.out.println(str.indexOf('c'));
		//c�� ����� �ε����� �ִ��� 
		
		String str2 = str.substring(1,3);//1~3�������� ��� 
		System.out.println(str2);
		
		
		String str3 = str.trim();//��������
		System.out.println(str3);
		
		
		char cs[] = str.toCharArray();//String�� char���� �迭�� ����� �������ش�.
		for(char d : cs) {
			System.out.print(d + ",");
		}
		System.out.println();
		
		
		String ss = "1,2,3,4,5";
		String result[] = ss.split(",");//, �� �������� �߶��� �迭�� �����Ѵ�. 
		for(String st : result) {
			System.out.print(st);
			
		}
		
		
		
		

	}

}
