package day05;

public class Array_ex2 {
	public static void main(String[] args) {	
		// 배열의 홀수 네번째 값들의 합을 구하시오
		int arr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int sum = 0;
		int count =1;
		int f_num = 0;//4번째 수 몇번 나오는지 카운트
		double avg = 0;
		
		
		for(int i=0; i < arr.length; i++) {
			if(arr[i] % 2 != 0) {//홀수
			//if(arr[i] % 2 == 1) {//홀수
				if(count % 4 == 0) {
					sum += arr[i];//1 3 5 7 9
					f_num++;
				}
				count++;
			}
		}
		
		avg = (double)sum / f_num;
		
		System.out.println("합 : " + sum);
		System.out.println("홀수의 네번쨰는 총 " + f_num + " 번으로, 평균 : " + avg);
	}

}
