package day06;

import java.util.Random;

public class MaxMin_find {

	public static void main(String[] args) {
//1. int ������ 5 �迭�� �����.
//2. �� �迭�� �����ϰ� ���� �ִ´�.
//3. �迭�� ���� �� �ִ밪�� ã�´�.
//4. �ִ밪�� ����Ѵ�.
		
		int[] arr = new int[5];
		int max = 0;//�ִ�
		int min = 0;//�ּ�
		Random r = new Random();
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = r.nextInt(10);
			System.out.print(arr[i] + ", ");
		}
		
		max = arr[0];
		min = arr[0];
		for(int j=1; j<arr.length; j++) {
			if(max < arr[j]) {
				max = arr[j];
			}
			if(min > arr[j]) {
				min = arr[j];
			}
			
			
		}
		
		System.out.println();
		System.out.println("�ִ밪: " + max);
		System.out.println("�ּҰ�: " + min);

	}

}
