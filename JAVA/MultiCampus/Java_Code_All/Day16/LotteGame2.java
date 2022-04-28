package day16;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LotteGame2 extends Lotte {

	

	public LotteGame2() {
		lotte_my = new int[6];
		
	}
	
	
	public void insertArray() throws Exception{//내가 6개의 번호를 찍는 것
		
		
		try {
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
			
			System.out.print("입력된 6개 숫자 : ");
			for(int a : lotte_my) {
				System.out.print(a + ", ");
			}
			System.out.println();
			
		}catch(Exception e) {
			System.out.println("숫자 입력 중 에러가 있습니다. ");
			throw e;
		}

		
	}

	public void compare() {// 내 로또 번호와 당첨 번호 맞춰보는 메소드 
		
		for(int q = 0; q < lotte_correct.length; q++) {//정답 맞춰보기
			for(int l=0; l<lotte_my.length; l++) {
				if(lotte_correct[q] == lotte_my[l]) {//맞출경우 
					System.out.println(lotte_my[l] + "를 맞췄어요!");
					count++;//맞춘 갯수 카운팅 
				}
			}
		}
		System.out.println("총 " + count + "개를 맞췄습니다. ");
		
	}
	
	
	public void rank() {
		if(this.count == 6) {
			System.out.println("1등에 당첨되었습니다."); 
			rank = "1";
			correct_money += 2000000000;//20억

		}else if(this.count == 5) {
			System.out.println("2등에 당첨되었습니다.");
			rank = "2";
			correct_money += 800000000;//8억

		}else if(this.count == 4) {
			System.out.println("3등에 당첨되었습니다.");   
			rank = "3";
			correct_money += 20000000;//2천만

		}else if(this.count == 3) {
			System.out.println("4등에 당첨되었습니다.");   
			rank = "4";
			correct_money += 3000000;//3백만

		}else if(this.count == 2){	        	  
			System.out.println("5등에 당첨되었습니다.");
			rank = "5";
			correct_money += 5000;//5천원
			

		}else {
			rank = "0";

			System.out.println("아쉽게도 낙첨하셨어요.");
		}
	}
	


}
