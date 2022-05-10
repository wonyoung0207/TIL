package day06;

import java.util.Random;

public class MaxMin_find {

	public static void main(String[] args) {
//1. int 사이즈 5 배열을 만든다.
//2. 그 배열에 램덤하게 값을 넣는다.
//3. 배열의 값들 중 최대값을 찾는다.
//4. 최대값을 출력한다.
		
		int[] arr = new int[5];
		int max = 0;//최대
		int min = 0;//최소
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
		System.out.println("최대값: " + max);
		System.out.println("최소값: " + min);

	}

}
