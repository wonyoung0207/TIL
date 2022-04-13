package day05;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ws4 {

	public static void main(String[] args) {
//		int ar[][] = new int[3][];
//		ar [0] = new int[4];
//		ar [1] = new int[4];
//		ar [2] = new int[4];
//		
//		int ar1[] = {100,99,80,70};
//		int ar2[] = {98,91,83,72};
//		int ar3[] = {89,96,82,75};
		
		//int arr[][] = new int[3][];
		
		int[][] arr = {{100,98,80,70},{98,91,83,72},{89,96,82,75}};//학생 점수가 저장된 배열 
		double [] stu_avg = new double[arr.length];//학생별 평균 점수 저장하는 배열
		int sum = 0;
		double avg = 0;
		
		for(int i=0; i<arr.length; i++) {
			double stu_avg_num = 0;//학생별 총 점수를 저장하는 변수 
			
			for(int j=0; j<arr[i].length; j++) {
				sum += arr[i][j];
				stu_avg_num += arr[i][j];
			}
			stu_avg[i] = (double)stu_avg_num / arr[i].length;//학생별 평균 점수를 저장 
			
		}
		
		avg = (double) sum / (arr[0].length * arr.length);
		System.out.printf("합 : %d , 평균 : %.2f \n",sum, avg);
		
		for(int k=0; k<stu_avg.length; k++	) {
			System.out.println("학생[" + k + "] 의 평균 : " + stu_avg[k]); 
		}
		System.out.println(Arrays.toString(stu_avg));
	}

}
