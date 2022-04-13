package day04;

public class for문_while문 {

	public static void main(String[] args) {
		 
		//i가 7일때만 실행
		System.out.print("for loop : " );
		for(int i=1; i <=10; i++) {
			System.out.print(i + ", ");
			if(i== 7) {
				break;
			}
		}
		System.out.println();
		
		System.out.print("whle loop : ");
		int s = 1;
		while(s <= 10) {
			if(s == 7) {
				break;
			}
			System.out.print(s + ", ");
			s++;
		}
		
		
		
		//i가 짝수일 때만 출력 하시오

		System.out.println();
		System.out.print ("짝수 for: ");
		for (int i = 1; i <= 10; i++) {
			if(i % 2 == 0) {
				System.out.print(i+ ", ");
			}			
		}
		
		System.out.println();
		System.out.print("짝수 whle loop : ");
		int s1 = 1;
		while(s1 <= 10) {
			if(s1 % 2 == 1) {
				s1++;
				continue;
			}
			System.out.print(s1 + ", ");
			s1++;
		}
		


	}

}
