package day05;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ws3 {

	public static void main(String[] args) {
		int arr[] = new int[6];
		int count = arr.length;
		
		Scanner input = new Scanner(System.in);
		Random r = new Random();
		
		
		try {
//			//���� �Է��ϴ� �ڵ� 
//			int i=0;
//			while(i < arr.length) {
//				System.out.println("1~6������ ���� �Է��ϼ���. �� �ߺ����� �ʾƾ� �մϴ�. ");
//				arr[i] = input.nextInt();
//				
//				for(int j=0; j<i; j++) {//���� �� �ִ��� Ȯ��
//					if(arr[i] == arr[j]) {//�������� ������ �ٽ��Է�
//						System.out.println("�������� �ԷµǾ����ϴ�. �ٽ� �Է��ϼ���.");
//						i--;
//					}
//				}
//				
//				i++;
//				
//			}	
			
			//������ ���� �޾ƿ� �����ϴ� �ڵ� 
			int i=0;
			while(i < arr.length) {
				arr[i] = r.nextInt(6)+1;
				
				for(int j=0; j<i; j++) {//���� �� �ִ��� Ȯ��
					if(arr[i] == arr[j]) {//�������� ������ �ٽ��Է�
						System.out.println("������" + arr[i] +  "�� �ԷµǾ����ϴ�. �ٽ� �Է¹޽��ϴ�. ");
						i--;
					}
				}
				i++;
			}	
		}catch(Exception e) {
			System.out.println("���ڸ� �Է����ּ���.");
		}
		
		
		System.out.println(Arrays.toString(arr));
		
	}

}
