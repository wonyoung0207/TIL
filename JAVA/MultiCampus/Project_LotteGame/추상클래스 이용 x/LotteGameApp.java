package day16;

import java.util.Scanner;

public class LotteGameApp {

	public static void main(String[] args) {
		LotteGame[] arr = null;
		Scanner input = new Scanner(System.in);
		int num;


		LotteGame.randArray();//로또 정답 발표 
		
		try {
			System.out.println("몇 줄을 사시겠어요?");
			num = input.nextInt();
			if(num >= 10) {
				System.out.println("10줄 이상은 살수 없습니다. 다시 시작해주세요.");
				return ;
			}
			arr = new LotteGame[num];
			System.out.println("총 " + num + "줄을 입력합니다. ");
				
		}catch(Exception e) {
			System.out.println("잘못입력하셨습니다. 프로그램 종료 ");
			return ;
		}
		
		
		
		
		for(int i = 0; i < arr.length; i++) {
			try {
				System.out.println("********** "+(i+1) + "번째 줄의 당첨번호를 맞춰봅니다. " + " **********");
				arr[i] = new LotteGame();
				arr[i].insertArray();//숫자 입력 
				
			}catch(Exception e) {
				System.out.println((i+1) + "번째 줄을 다시 입력받습니다. ");
				i--;
			}
		}
//
//		game.randArray();//로또 정답 발표 
		
		for(int i = 0; i< arr.length; i++) {//정답을 맞추는 곳
			System.out.println("******"+(i+1) + "번째 줄의 당첨번호를 맞춰봅니다. " + "******");
			
			arr[i].compare();
			arr[i].rank();
		}
		

		System.out.println("********** 상금 ********** ");
		System.out.printf("나의 총 상금은 %,3d원 입니다. ",LotteGame.correct_money);
		
		
		
		

	}

}
