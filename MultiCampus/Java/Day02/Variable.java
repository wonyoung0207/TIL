package day02;

public class Variable {//파일의 이름은 대문자로 시작하는게 규칙이다. 

	public static void main(String[] args) {
		byte b1 = 127;
		//byte b2 = 128;//128이라는 숫자가 안된다.
		System.out.println(b1);
		
		
		char c1 = 'A';
		char c2 = 65;//char형에서 숫자는 65라는 10진수가 2진수로 변환되어 문자를 출력한다.
		// 0x41
		char c3 = '\u0041';//A의 유니코드를 뜻한다.      
		//char c1 = 'AB';//2개의 문자 = 문자열
		System.out.println(c1 + ", " + c2 + "," + c3);		
		System.out.println('\uAC00');
		
		//int 형 
		int v1 = 10; //10진수
		int v2 = 012; //10진수
		int v3 = 0x000A; //10진수
		
		System.out.println(v1 + ", " + c2  + ", " + c3);
		
		byte d1 = 100;
		byte d2 = 100;
		//byte d3 = d1 + d2;//정수의 연산 + 가 일어나면 int로 된다. 
		byte d3 = (byte)(d1 + d2);//정수의 연산 + 가 일어나면 int로 된다. 
		int d4 = d1 + d2; 
		//따라서 byte형인 d3를 int형으로 변형해야한다. 
		System.out.println("표현 범위 벗어남 : " + d3);//200 이라는 숫자는 1byte(-127 ~ 128)로 표현이 불가하다.-> 앞자리가 날아간다.
		System.out.println(d4); 
		
		int vt1 = 1500000000;
		int vt2 = 1500000000;
		int result = vt1 + vt2;
		long result2 = (long)(vt1 + vt2);//연산이 된 후 long으로 바꿔주면 결과 이상함 -> 각각 long으로 바꿔줘야
		//이유는 변수에 저장하기 전 임시로 메모리에 저장되는데 이때 기본 데이터 타입이 int이다. 따라서 임시 저장과정에서 에러가 난다. 

		System.out.println("표현 범위를 벗어남 : " + result);
		System.out.println("표현 범위를 벗어남 : " + result2);
		
		
		long vt3 = 1500000000L;//long형은 소수점이 표현되지 않는다. 
		//-> int의 범위를 벗어난 숫자를 long으로 처리하려면 뒤에 L을 붙여줘야 한다. 
		long vt4 = 1500000000L;
		long result3 = vt3 + vt4;
		
		System.out.println("long 타입에 L을 붙여줌 : " + result3);
		
		float f1 = 2.5F;//F를 붙이지 않으면 에러남 -> 소수의 기본은 double형 이기 때문에 해당 수를 flot으로 변경해야 한다. 
		//float f2 = 5.4 + f1;// f1이 double형으로 변경되어 계산된다. -> 따라서 5.4를 double형으로 변경해야 한다.  
		
		
		
		//단항연산자
		int a= 10;
		int b= 10;
		int sum = ++a + b++;//이러면 결국에는 a와 b 둘다 증가됨 
		System.out.println("sum = " + sum + ", a= "+ a +  ", b= "+ b + " ");
		
		
		//이항 연산자=> 피연산자 2개 요구    < - > 단항 연산자 : 피연산자 1개 요구 
		double e = 3 % 6;//나머지
		double f = 3 / 6;//나누기 
		double g = (double)10 / (double)3;// (double)10/3 은 안된다.
		//왜냐하면 애초에 정수기를 나누기 때문에 소수자리가 나오지 않는다. 
		
		System.out.println(" 나눈 값 : " + f + " 나머지 : " + e);
		System.out.println("소수자리 까지 출력하는 나누기 : " + g);
		
		//논리 연산
		int a1 = 10;
		int a2 = 20;
		int a3 = 10;
		int a4 = 20;
		
		if( (a1 < a2) || (a3< a4)) {//or 연산자   < - > and 연산자
			// && 와 &의 차이 : 첫번째가 false면 &&일 떄는 첫번째 조건 하나만 , &는 조건 두개다 확인해야한다.  
			System.out.println("OK");
		}
		else {
			System.out.println("FAIL");
		}
		
		
		//삼항 연산자 
		int x = 10;
		int y = 20;
		String r = (x < y) ? "bigger is y" : "bigger is x"; 
		System.out.println(r);
		
		
		//조건문 
		int sum1 = 98;
		char grade = 0; // 로컬 변수는 사용 전 반드시 초기화 해야한다.		
		
		if(sum1 >= 90) {
			grade = 'A';
		}else if(sum1 >= 80) {
			grade = 'B';
		}
		else {
			grade = 'D';
		}
		System.out.println( "grade : " + grade);

		
		char grade2 = (sum1 >= 90) ? 'A' : ((sum1 >= 80) ? 'B' : 'C');
		System.out.println( "삼항 연산 진행 grade2: " + grade2);
		
		
		
		//분모를 0.0으로 나누면 infinite가 나온다. -> 무한대로 나눠지기 때문에 
		int aa = 100;
		double bb = 0.0;
		double result4 = 0.0;
		
		result4 = aa / bb;
		if(Double.isInfinite(result4)) {
			System.out.println("분모가 0.0 입니다. 무한대로 나옵니다. ");
		}else {
			System.out.println(result);
		}
		
		
		int aaa = 100;
		int bbb = 200;
		int result5 = Math.max(aaa, bbb);
		System.out.println(" max값 구하기 : " + result5);
		
		
		int num = 1000000000;
		System.out.println(String.format("%,d", num));
		
	}
}