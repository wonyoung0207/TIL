package day05;

import java.util.Random;

public class Array_ex3 {

	public static void main(String[] args) {
		int arr[][] = new int[3][4];
		int sum =0;
		int count = 0;
		double avg = 0;
		
		
		Random r = new Random();
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = r.nextInt(9) +1;
				
			}
		}
		
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.printf("%d \t", arr[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				sum += arr[i][j];
				count++;
			}
		}
		
		avg = (double)sum / count;
		System.out.printf("sum : %d, avg : %.2f",sum,avg);
		
		
	}

}
