package day12;

public class ObjectTestApp {

	public static void main(String[] args) {
		//object는 최상위 클래스이다. 
		Object obj = new String("abc");
		Object obj2 = new CustomerVO("id01", "pwd01", "lee");
		
		//obj2에서 CustomerVO 객체에 있는 값들을 사용하기 위해서는 타입 캐스팅을 다시 해야한다. 
		CustomerVO c = (CustomerVO) obj2;//타입 캐스팅 
		System.out.println(c.getName());
		
		Object obj3 = 10;//10이라는게 객체 타입으로 바뀌어서 들어간다. 
		Object obj4 = new Integer(10);
		
		

	}

}
