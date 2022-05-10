package day16;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LotteGame {
	int lotte_my[];//�Է��� ��ȣ�� �����ϴ� �迭 
	int count = 0;//���� ���� 
	String rank = "0";//���
	static int lotte_correct[] = new int[6];//�ζ� ������ �����ϴ� �迭 
	static Random r = new Random();
	static int correct_money = 0;//���� ����� ���� 
	Scanner input = new Scanner(System.in);
	

	public LotteGame() {
		lotte_my = new int[6];
		
	}
	
	
	public void insertArray() throws Exception{//���� 6���� ��ȣ�� ��� ��
		
		
		try {
			for(int k = 0; k < lotte_my.length; k++) {
				System.out.print("�ζ� ���� �Է� : ");
				
				lotte_my[k] = input.nextInt();//���߱� ���� ���� �Է�


				if(lotte_my[k] < 0 || lotte_my[k] > 45) {
					System.out.println("������ �ʰ��߾��. ");
					k--;
				}

				for(int a=0; a < k; a++) {
					if(lotte_my[k] == lotte_my[a]) {// �Է��� ���� �ߺ��� ��� ����
						System.out.println("�ߺ��Ǵ� ���� �Է��ϼ̾��. �ٽ��Է¹޽��ϴ�. ");
						k--;//�ߺ��̸� �ٽ� �Է¹ޱ� 
					}
				}
			}
			
			System.out.print("�Էµ� 6�� ���� : ");
			for(int a : lotte_my) {
				System.out.print(a + ", ");
			}
			System.out.println();
			
		}catch(Exception e) {
			System.out.println("���� �Է� �� ������ �ֽ��ϴ�. ");
			throw e;
		}

		
	}
	
	public static void randArray() {//�ζ� ���� ��ǥ -> ���� ���� 
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
	
	public void compare() {// �� �ζ� ��ȣ�� ��÷ ��ȣ ���纸�� �޼ҵ� 
		
		for(int q = 0; q < lotte_correct.length; q++) {//���� ���纸�� 
			for(int l=0; l<lotte_my.length; l++) {
				if(lotte_correct[q] == lotte_my[l]) {//������ 
					System.out.println(lotte_my[l] + "�� ������!");
					count++;//���� ���� ī���� 
				}
			}
		}
		System.out.println("�� " + count + "���� ������ϴ�. ");
		
	}
	
	
	public void rank() {
		if(this.count == 6) {
			System.out.println("1� ��÷�Ǿ����ϴ�."); 
			rank = "1";
			correct_money += 2000000000;//20��

		}else if(this.count == 5) {
			System.out.println("2� ��÷�Ǿ����ϴ�.");
			rank = "2";
			correct_money += 800000000;//8��

		}else if(this.count == 4) {
			System.out.println("3� ��÷�Ǿ����ϴ�.");   
			rank = "3";
			correct_money += 20000000;//2õ��

		}else if(this.count == 3) {
			System.out.println("4� ��÷�Ǿ����ϴ�.");   
			rank = "4";
			correct_money += 3000000;//3�鸸

		}else if(this.count == 2){	        	  
			System.out.println("5� ��÷�Ǿ����ϴ�.");
			rank = "5";
			correct_money += 5000;//5õ��
			

		}else {
			rank = "0";

			System.out.println("�ƽ��Ե� ��÷�ϼ̾��.");
		}
	}
	


}
