package day02;

public class Variable {//������ �̸��� �빮�ڷ� �����ϴ°� ��Ģ�̴�. 

	public static void main(String[] args) {
		byte b1 = 127;
		//byte b2 = 128;//128�̶�� ���ڰ� �ȵȴ�.
		System.out.println(b1);
		
		
		char c1 = 'A';
		char c2 = 65;//char������ ���ڴ� 65��� 10������ 2������ ��ȯ�Ǿ� ���ڸ� ����Ѵ�.
		// 0x41
		char c3 = '\u0041';//A�� �����ڵ带 ���Ѵ�.      
		//char c1 = 'AB';//2���� ���� = ���ڿ�
		System.out.println(c1 + ", " + c2 + "," + c3);		
		System.out.println('\uAC00');
		
		//int �� 
		int v1 = 10; //10����
		int v2 = 012; //10����
		int v3 = 0x000A; //10����
		
		System.out.println(v1 + ", " + c2  + ", " + c3);
		
		byte d1 = 100;
		byte d2 = 100;
		//byte d3 = d1 + d2;//������ ���� + �� �Ͼ�� int�� �ȴ�. 
		byte d3 = (byte)(d1 + d2);//������ ���� + �� �Ͼ�� int�� �ȴ�. 
		int d4 = d1 + d2; 
		//���� byte���� d3�� int������ �����ؾ��Ѵ�. 
		System.out.println("ǥ�� ���� ��� : " + d3);//200 �̶�� ���ڴ� 1byte(-127 ~ 128)�� ǥ���� �Ұ��ϴ�.-> ���ڸ��� ���ư���.
		System.out.println(d4); 
		
		int vt1 = 1500000000;
		int vt2 = 1500000000;
		int result = vt1 + vt2;
		long result2 = (long)(vt1 + vt2);//������ �� �� long���� �ٲ��ָ� ��� �̻��� -> ���� long���� �ٲ����
		//������ ������ �����ϱ� �� �ӽ÷� �޸𸮿� ����Ǵµ� �̶� �⺻ ������ Ÿ���� int�̴�. ���� �ӽ� ����������� ������ ����. 

		System.out.println("ǥ�� ������ ��� : " + result);
		System.out.println("ǥ�� ������ ��� : " + result2);
		
		
		long vt3 = 1500000000L;//long���� �Ҽ����� ǥ������ �ʴ´�. 
		//-> int�� ������ ��� ���ڸ� long���� ó���Ϸ��� �ڿ� L�� �ٿ���� �Ѵ�. 
		long vt4 = 1500000000L;
		long result3 = vt3 + vt4;
		
		System.out.println("long Ÿ�Կ� L�� �ٿ��� : " + result3);
		
		float f1 = 2.5F;//F�� ������ ������ ������ -> �Ҽ��� �⺻�� double�� �̱� ������ �ش� ���� flot���� �����ؾ� �Ѵ�. 
		//float f2 = 5.4 + f1;// f1�� double������ ����Ǿ� ���ȴ�. -> ���� 5.4�� double������ �����ؾ� �Ѵ�.  
		
		
		
		//���׿�����
		int a= 10;
		int b= 10;
		int sum = ++a + b++;//�̷��� �ᱹ���� a�� b �Ѵ� ������ 
		System.out.println("sum = " + sum + ", a= "+ a +  ", b= "+ b + " ");
		
		
		//���� ������=> �ǿ����� 2�� �䱸    < - > ���� ������ : �ǿ����� 1�� �䱸 
		double e = 3 % 6;//������
		double f = 3 / 6;//������ 
		double g = (double)10 / (double)3;// (double)10/3 �� �ȵȴ�.
		//�ֳ��ϸ� ���ʿ� �����⸦ ������ ������ �Ҽ��ڸ��� ������ �ʴ´�. 
		
		System.out.println(" ���� �� : " + f + " ������ : " + e);
		System.out.println("�Ҽ��ڸ� ���� ����ϴ� ������ : " + g);
		
		//�� ����
		int a1 = 10;
		int a2 = 20;
		int a3 = 10;
		int a4 = 20;
		
		if( (a1 < a2) || (a3< a4)) {//or ������   < - > and ������
			// && �� &�� ���� : ù��°�� false�� &&�� ���� ù��° ���� �ϳ��� , &�� ���� �ΰ��� Ȯ���ؾ��Ѵ�.  
			System.out.println("OK");
		}
		else {
			System.out.println("FAIL");
		}
		
		
		//���� ������ 
		int x = 10;
		int y = 20;
		String r = (x < y) ? "bigger is y" : "bigger is x"; 
		System.out.println(r);
		
		
		//���ǹ� 
		int sum1 = 98;
		char grade = 0; // ���� ������ ��� �� �ݵ�� �ʱ�ȭ �ؾ��Ѵ�.		
		
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
		System.out.println( "���� ���� ���� grade2: " + grade2);
		
		
		
		//�и� 0.0���� ������ infinite�� ���´�. -> ���Ѵ�� �������� ������ 
		int aa = 100;
		double bb = 0.0;
		double result4 = 0.0;
		
		result4 = aa / bb;
		if(Double.isInfinite(result4)) {
			System.out.println("�и� 0.0 �Դϴ�. ���Ѵ�� ���ɴϴ�. ");
		}else {
			System.out.println(result);
		}
		
		
		int aaa = 100;
		int bbb = 200;
		int result5 = Math.max(aaa, bbb);
		System.out.println(" max�� ���ϱ� : " + result5);
		
		
		int num = 1000000000;
		System.out.println(String.format("%,d", num));
		
	}
}