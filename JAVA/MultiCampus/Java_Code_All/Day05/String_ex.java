package day05;

public class String_ex {

	public static void main(String[] args) {

		String str = "abcde";
		int c = str.indexOf("c");
		System.out.println("문자 c는 " + str + "의 " + c + "번쨰 index 에 있습니다. ");
		
		String str2 = str.substring(0,2);// 0번째에서 시작해서 2번째전까지 잘라내라. 
		System.out.println(str2);
		
		String mail = "ahnwy27@gmail.com";
		
		String my_email = mail.substring(0,mail.indexOf("@"));
		String domain = mail.substring(mail.indexOf("@")+1,mail.indexOf("."));
		
		System.out.println("my email : " + my_email);
		System.out.println("What is domain : " + domain );
		
		
		
	}

}
