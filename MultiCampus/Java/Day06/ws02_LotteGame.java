package day06;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ws02_LotteGame {
//	1. 1~45±îÁöÀÇ ¼ıÀÚÁß Áßº¹µÇÁö ¾ÊÀº 6°³ÀÇ ¼ıÀÚ¸¦ ¹è¿­¿¡ ÀúÀå Ãâ·ÂÇÑ´Ù.
//	2. ·Î¶Ç ¸ÂÃâ ¼ıÀÚ 6¹ø ÀÔ·Â¹Ş´Â´Ù.
//	2-1. ÀÔ·ÂµÈ °ªÀº Áßº¹½Ã ´Ù½Ã ÀÔ·Â¹Ş´Â´Ù. 
//	2-2¹üÀ§¿¡ ¹ş¾î³­ °ªÀº ´Ù½Ã ÀÔ·Â¹Ş´Â´Ù.
//	3. count º¯¼ö ¼±¾ğ -> ¸î°³ ¸Â­Ÿ´ÂÁö 
//	4. 1µîÀº 6°³, 2µîÀº 5°³, 3µîÀº 4°³, 4µîÀº 3°³ 5µîÀº 2°³ 6µîÀº 1°³ 0°³´Â ²Î


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

			System.out.println("·Î¶Ç Á¤´ä : " + Arrays.toString(lotte_success));


			for(int k = 0; k < lotte_my.length; k++) {
				System.out.print("·Î¶Ç ¼ıÀÚ ÀÔ·Â : ");
				lotte_my[k] = input.nextInt();//¸ÂÃß±â À§ÇÑ ¼ıÀÚ ÀÔ·Â

				if(lotte_my[k] < 0 || lotte_my[k] > 45) {
					System.out.println("¹üÀ§¸¦ ÃÊ°úÇß¾î¿ä. ");
					k--;
				}

				for(int a=0; a < k; a++) {
					if(lotte_my[k] == lotte_my[a]) {// ÀÔ·ÂÇÑ °ªÀÌ Áßº¹ÀÏ °æ¿ì ½ÇÇà
						System.out.println("Áßº¹µÇ´Â °ªÀ» ÀÔ·ÂÇÏ¼Ì¾î¿ä. ´Ù½ÃÀÔ·Â¹Ş½À´Ï´Ù. ");
						k--;//Áßº¹ÀÌ¸é ´Ù½Ã ÀÔ·Â¹Ş±â 
					}
				}
			}

			for(int q = 0; q < lotte_success.length; q++) {//Á¤´ä ¸ÂÃçº¸±â 
				for(int l=0; l<lotte_my.length; l++) {
					if(lotte_success[q] == lotte_my[l]) {//¸ÂÃâ°æ¿ì 
						System.out.println(lotte_my[l] + "¸¦ ¸ÂÃè¾î¿ä!");
						count++;//¸ÂÃá °¹¼ö Ä«¿îÆÃ 
					}
					System.out.println(l);
				}
			}

			System.out.println("ÃÑ "+ count + "°³¸¦ ¸Â­Ÿ¾î¿ä.");			
			System.out.println();
			if(count == 0) {
				System.out.println("²Î");   

			}else if(count == 6) {
				System.out.println("1µî");

			}else if(count == 5) {
				System.out.println("2µî");   

			}else if(count == 4) {
				System.out.println("3µî");   

			}else if(count == 3){	        	  
				System.out.println("4µî");

			}else {
				System.out.println("5µî");
			}




		}catch(Exception e) {
			//			System.out.println("¹®ÀÚ ÀÔ·ÂÀ¸·Î Á¾·áÇÕ´Ï´Ù. ");
			System.out.println(e.getMessage());
		}


	}

}
