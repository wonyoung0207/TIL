package day06;

import java.util.Random;
import java.util.Scanner;

public class ws01_numberGuessGame {
	public static void main(String[] args) {
//		 ���� �߻��ؼ� ���ٿ����� ���� ã�� 
//		 1~99 ������ �ϳ��� �������� �����մϴ� .
//		 �� 5���� ��ȸ�� �־����ϴ�. 
//		 �־��� Ƚ�� �ȿ� ���ڸ� �Է��Ͽ� ���� ã���ϴ�.
//		 �Է��� ���ڰ� �� ũ�� down, �� ������ up�� ����մϴ�. 
//		 5���� ��ȸ�ȿ� ã���� ����, ��ã���� ������ �����մϴ�. 
		
		
		int up=0,down=0,count = 1,num = 0;
		Random r = new Random();
		Scanner input = new Scanner(System.in);
		
		int random_num = r.nextInt(99)+1;
		System.out.println("random �� : " + random_num);
		
		
		while(true) {
			
			if(count > 6) {
				System.out.println("5���� ��ȸ�� ��� ����߽��ϴ�. ������ �����մϴ�. ");
				break;
			}
			
			System.out.println("ã�ƺ� ���ڸ� �Է��ϼ���. �Է°��� random ���ں��� ũ�� down, ������ up�� ����մϴ�. ");
			num = input.nextInt();
			
			if(num == random_num) {
				System.out.println("find!!");
				System.out.println(count + "������ ���ڸ� ã�ҽ��ϴ�. ");
				break;
			}else if(num < random_num){
				System.out.println("up!!");
				count++;
				continue;
			}else {
				count++;
				System.out.println("down!!");
				continue;
			}
		}
		
	}
}
