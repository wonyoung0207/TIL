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
//			//내가 입력하는 코드 
//			int i=0;
//			while(i < arr.length) {
//				System.out.println("1~6까지의 값을 입력하세요. 단 중복되지 않아야 합니다. ");
//				arr[i] = input.nextInt();
//				
//				for(int j=0; j<i; j++) {//같은 값 있는지 확인
//					if(arr[i] == arr[j]) {//같은값이 있으면 다시입력
//						System.out.println("같은값이 입력되었습니다. 다시 입력하세요.");
//						i--;
//					}
//				}
//				
//				i++;
//				
//			}	
			
			//랜덤한 값을 받아와 저장하는 코드 
			int i=0;
			while(i < arr.length) {
				arr[i] = r.nextInt(6)+1;
				
				for(int j=0; j<i; j++) {//같은 값 있는지 확인
					if(arr[i] == arr[j]) {//같은값이 있으면 다시입력
						System.out.println("같은값" + arr[i] +  "이 입력되었습니다. 다시 입력받습니다. ");
						i--;
					}
				}
				i++;
			}	
		}catch(Exception e) {
			System.out.println("숫자만 입력해주세요.");
		}
		
		
		System.out.println(Arrays.toString(arr));
		
	}

}
