package day12;

public class ObjectTestApp {

	public static void main(String[] args) {
		//object�� �ֻ��� Ŭ�����̴�. 
		Object obj = new String("abc");
		Object obj2 = new CustomerVO("id01", "pwd01", "lee");
		
		//obj2���� CustomerVO ��ü�� �ִ� ������ ����ϱ� ���ؼ��� Ÿ�� ĳ������ �ٽ� �ؾ��Ѵ�. 
		CustomerVO c = (CustomerVO) obj2;//Ÿ�� ĳ���� 
		System.out.println(c.getName());
		
		Object obj3 = 10;//10�̶�°� ��ü Ÿ������ �ٲ� ����. 
		Object obj4 = new Integer(10);
		
		

	}

}
