package day05;

public class Array_ex2 {
	public static void main(String[] args) {	
		// �迭�� Ȧ�� �׹�° ������ ���� ���Ͻÿ�
		int arr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int sum = 0;
		int count =1;
		int f_num = 0;//4��° �� ��� �������� ī��Ʈ
		double avg = 0;
		
		
		for(int i=0; i < arr.length; i++) {
			if(arr[i] % 2 != 0) {//Ȧ��
			//if(arr[i] % 2 == 1) {//Ȧ��
				if(count % 4 == 0) {
					sum += arr[i];//1 3 5 7 9
					f_num++;
				}
				count++;
			}
		}
		
		avg = (double)sum / f_num;
		
		System.out.println("�� : " + sum);
		System.out.println("Ȧ���� �׹����� �� " + f_num + " ������, ��� : " + avg);
	}

}
