package day05;

public class String_ex {

	public static void main(String[] args) {

		String str = "abcde";
		int c = str.indexOf("c");
		System.out.println("���� c�� " + str + "�� " + c + "���� index �� �ֽ��ϴ�. ");
		
		String str2 = str.substring(0,2);// 0��°���� �����ؼ� 2��°������ �߶󳻶�. 
		System.out.println(str2);
		
		String mail = "ahnwy27@gmail.com";
		
		String my_email = mail.substring(0,mail.indexOf("@"));
		String domain = mail.substring(mail.indexOf("@")+1,mail.indexOf("."));
		
		System.out.println("my email : " + my_email);
		System.out.println("What is domain : " + domain );
		
		
		
	}

}
