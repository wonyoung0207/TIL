package day16;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class Lotte {
	protected int lotte_my[];//�Է��� ��ȣ�� �����ϴ� �迭 
	protected int count = 0;//���� ���� 
	protected String rank = "0";//���
	protected static int lotte_correct[] = new int[6];//�ζ� ������ �����ϴ� �迭 
	protected static Random r = new Random();
	protected static int correct_money = 0;//���� ����� ���� 
	Scanner input = new Scanner(System.in);
	
	public abstract void insertArray() throws Exception;
	public abstract void compare();
	public abstract void rank();
	
	protected static void randArray() {//�ζ� ���� ��ǥ -> ���� ���� 
		System.out.println("********** �ζ��� ������ ��ǥ�մϴ�. ********** ");
		for(int i=0; i<lotte_correct.length; i++) {
			lotte_correct[i] = r.nextInt(45)+1;
			for(int j=0; j<i; j++) {
				if(lotte_correct[i] == lotte_correct[j]) {
					i--;
				}
			}

		}

		System.out.println(Arrays.toString(lotte_correct) + " �Դϴ�! ��÷�ǽ� ��� �е� ���ϵ帳�ϴ�!");

		
	}

}
