package day05;

import java.util.Arrays;
import java.util.Random;

public class ws01 {

	public static void main(String[] args) {
		double arr[] = new double[5];
		Random r = new Random();
		
		
//		for(int i=0; i< arr.length; i++) {
//			arr[i] = r.nextInt(11); 
//		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextDouble()*10;
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%.1f\t", arr[i]);
		}
		
//		System.out.println(Arrays.toString(arr));

	}

}
