package day06;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ws02_LotteGame {
//	1. 1~45까지의 숫자중 중복되지 않은 6개의 숫자를 배열에 저장 출력한다.
//	2. 로또 맞출 숫자 6번 입력받는다.
//	2-1. 입력된 값은 중복시 다시 입력받는다. 
//	2-2범위에 벗어난 값은 다시 입력받는다.
//	3. count 변수 선언 -> 몇개 맞췃는지 
//	4. 1등은 6개, 2등은 5개, 3등은 4개, 4등은 3개 5등은 2개 6등은 1개 0개는 꽝


	public static void main(String[] args) {
		int lotte_my[] = new int[6];
		int lotte_success[] = new int[6];

		Random r = new Random();
		int count = 0;

		Scanner input = new Scanner(System.in);


		try {
			for(int i=0; i<lotte_success.length; i++) {
				lotte_success[i] = r.nextInt(45)+1;
				for(int j=0; j<i; j++) {
					if(lotte_success[i] == lotte_success[j]) {
						i--;
					}
				}

			}

			System.out.println("로또 정답 : " + Arrays.toString(lotte_success));


			for(int k = 0; k < lotte_my.length; k++) {
				System.out.print("로또 숫자 입력 : ");
				lotte_my[k] = input.nextInt();//맞추기 위한 숫자 입력

				if(lotte_my[k] < 0 || lotte_my[k] > 45) {
					System.out.println("범위를 초과했어요. ");
					k--;
				}

				for(int a=0; a < k; a++) {
					if(lotte_my[k] == lotte_my[a]) {// 입력한 값이 중복일 경우 실행
						System.out.println("중복되는 값을 입력하셨어요. 다시입력받습니다. ");
						k--;//중복이면 다시 입력받기 
					}
				}
			}

			for(int q = 0; q < lotte_success.length; q++) {//정답 맞춰보기 
				for(int l=0; l<lotte_my.length; l++) {
					if(lotte_success[q] == lotte_my[l]) {//맞출경우 
						System.out.println(lotte_my[l] + "를 맞췄어요!");
						count++;//맞춘 갯수 카운팅 
					}
					System.out.println(l);
				}
			}

			System.out.println("총 "+ count + "개를 맞췃어요.");			
			System.out.println();
			if(count == 0) {
				System.out.println("꽝");   

			}else if(count == 6) {
				System.out.println("1등");

			}else if(count == 5) {
				System.out.println("2등");   

			}else if(count == 4) {
				System.out.println("3등");   

			}else if(count == 3){	        	  
				System.out.println("4등");

			}else {
				System.out.println("5등");
			}




		}catch(Exception e) {
			//			System.out.println("문자 입력으로 종료합니다. ");
			System.out.println(e.getMessage());
		}


	}

}
