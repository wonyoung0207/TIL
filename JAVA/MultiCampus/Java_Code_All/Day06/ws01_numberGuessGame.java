package day06;

import java.util.Random;
import java.util.Scanner;

public class ws01_numberGuessGame {
	public static void main(String[] args) {
//		 난수 발생해서 업다운으로 숫자 찾기 
//		 1~99 사이의 하나의 랜덤값을 생성합니다 .
//		 총 5번의 기회가 주어집니다. 
//		 주어진 횟수 안에 숫자를 입력하여 값을 찾습니다.
//		 입력한 숫자가 더 크면 down, 더 작으면 up을 출력합니다. 
//		 5번의 기회안에 찾으면 성공, 못찾으면 게임을 종료합니다. 
		
		
		int up=0,down=0,count = 1,num = 0;
		Random r = new Random();
		Scanner input = new Scanner(System.in);
		
		int random_num = r.nextInt(99)+1;
		System.out.println("random 값 : " + random_num);
		
		
		while(true) {
			
			if(count > 6) {
				System.out.println("5번의 기회를 모두 사용했습니다. 게임을 종료합니다. ");
				break;
			}
			
			System.out.println("찾아볼 숫자를 입력하세요. 입력값이 random 숫자보다 크면 down, 작으면 up을 출력합니다. ");
			num = input.nextInt();
			
			if(num == random_num) {
				System.out.println("find!!");
				System.out.println(count + "번만에 숫자를 찾았습니다. ");
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
