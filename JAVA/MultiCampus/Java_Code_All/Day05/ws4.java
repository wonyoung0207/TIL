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
		
		int[][] arr = {{100,98,80,70},{98,91,83,72},{89,96,82,75}};//�л� ������ ����� �迭 
		double [] stu_avg = new double[arr.length];//�л��� ��� ���� �����ϴ� �迭
		int sum = 0;
		double avg = 0;
		
		for(int i=0; i<arr.length; i++) {
			double stu_avg_num = 0;//�л��� �� ������ �����ϴ� ���� 
			
			for(int j=0; j<arr[i].length; j++) {
				sum += arr[i][j];
				stu_avg_num += arr[i][j];
			}
			stu_avg[i] = (double)stu_avg_num / arr[i].length;//�л��� ��� ������ ���� 
			
		}
		
		avg = (double) sum / (arr[0].length * arr.length);
		System.out.printf("�� : %d , ��� : %.2f \n",sum, avg);
		
		for(int k=0; k<stu_avg.length; k++	) {
			System.out.println("�л�[" + k + "] �� ��� : " + stu_avg[k]); 
		}
		System.out.println(Arrays.toString(stu_avg));
	}

}
