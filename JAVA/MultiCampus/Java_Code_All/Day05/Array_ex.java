package day05;

import java.util.Arrays;
import java.util.Random;

public class Array_ex {

	public static void main(String[] args) {		
		int arr[] = new int[4];
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 3;
		// int arr = { 0, 1, 2, 3};  // 배열을 숫자로 초기화 하는 방법 
		int sum = 0;
		double avg = 0;
		
		
		System.out.println(arr);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] +  ", "); 
		}
		System.out.println();
		
		int arr1[] = new int[10];
		Random r = new Random();
		for(int i=0; i < arr1.length; i++) {
			//arr1[i] = (int)(Math.random() * 9) + 1;
			arr1[i] = r.nextInt(9) + 1;
			sum += arr1[i];
			
			System.out.print(arr1[i] + ",");
		}
		
		System.out.println();
		avg = sum / arr1.length;
		System.out.printf("합 : %d , 평균 : %.2f ",sum, avg);
		
		//System.out.println(Arrays.toString(arr1));//배열의 내용을 출력할 수 있다. 
		
		
	}

}
