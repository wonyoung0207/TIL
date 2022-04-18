package day08;

import java.util.Scanner;

public class Company {
//	1. ��� �� ��ü�� ���� �ϰ� ��� �Ͻÿ�
//	2. ��� ����� ���� ������ ��� �Ͻÿ�
//	3. ��� ������� �μ�Ƽ�긦 ���� �ϰ��� �Ѵ�.
//	���� �� �� ��� �� ���� �ݾ��� �ٸ���.
//	ex) �μ�Ƽ�� 100���� ���� �� 
//	�Ϲݻ���� 50%����
//	�񼭴� 60% ����
//	������ ���� ��ǥ ��� �޼� ��ǥ�� ���� �μ�Ƽ�� �ݾ� ����
//	�����ڴ� 100% ����

	public static void main(String[] args) {
		Employee arr[] = new Employee[4];
		Scanner input = new Scanner(System.in);
		double incen = 0;
		double business_success=0;//��������� �޼��ݾ��� ���� 
		double year_salary = 0;// �μ�Ƽ��� ��� �߰����� ���� ������ ���� 
		
		arr[0] = new Employee("1","nomal",30000);
		arr[1]= new Secretary("2","secretary",40000,"got");
		arr[2] = new Business("3","business",50000,"Seoul", 20000);
		arr[3] = new Admin("0","admin",60000);
		
		System.out.println("�μ�Ƽ�긦 �Է����ּ���.");
		incen = input.nextDouble();
		
		for(int i=0; i< arr.length; i++) {
			
			if(arr[i] instanceof Business) {
				try {
					Business b = (Business) arr[i];
					System.out.println("��������� �޼� �ݾ��� �Է��� �ּ���.");
					business_success = input.nextDouble();	
					
					
					year_salary = arr[i].year_salary() + arr[i].incen(business_success);
					
				}catch(Exception e) {
					System.out.println("���ڸ� �Է����ּ���. ");
				}
				
			}else {
				
				if(arr[i].incen(incen) >=0) {
					year_salary = arr[i].year_salary() + arr[i].incen(incen);
				}else {
					System.out.println("�μ�Ƽ�� �߸� �Է� ");
				}
			}
			
			System.out.println(arr[i].toString());
			System.out.println("<"+arr[i].getName() + "> �� �μ�Ƽ�� ���� ������ " + String.format("%,3d", (int)year_salary) + "�� �Դϴ�. \n");
			System.out.println("#######################");

			
			
			
		}
		
		

	}

}
