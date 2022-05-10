package day08;

import java.util.Scanner;

public class Company {
//	1. 사원 별 객체를 생성 하고 출력 하시오
//	2. 모든 사원의 연봉 정보를 출력 하시오
//	3. 모든 사원에게 인센티브를 지급 하고자 한다.
//	지급 시 각 사원 별 지급 금액이 다르다.
//	ex) 인센티브 100만원 지급 시 
//	일반사원은 50%지급
//	비서는 60% 지급
//	영업은 영업 목표 대비 달성 목표에 따라 인센티브 금액 결정
//	관리자는 100% 지급

	public static void main(String[] args) {
		Employee arr[] = new Employee[4];
		Scanner input = new Scanner(System.in);
		double incen = 0;
		double business_success=0;//영업사원의 달성금액을 저장 
		double year_salary = 0;// 인센티브와 모든 추가금을 더한 연봉을 저장 
		
		arr[0] = new Employee("1","nomal",30000);
		arr[1]= new Secretary("2","secretary",40000,"got");
		arr[2] = new Business("3","business",50000,"Seoul", 20000);
		arr[3] = new Admin("0","admin",60000);
		
		System.out.println("인센티브를 입력해주세요.");
		incen = input.nextDouble();
		
		for(int i=0; i< arr.length; i++) {
			
			if(arr[i] instanceof Business) {
				try {
					Business b = (Business) arr[i];
					System.out.println("영업사원의 달성 금액을 입력해 주세요.");
					business_success = input.nextDouble();	
					
					
					year_salary = arr[i].year_salary() + arr[i].incen(business_success);
					
				}catch(Exception e) {
					System.out.println("숫자만 입력해주세요. ");
				}
				
			}else {
				
				if(arr[i].incen(incen) >=0) {
					year_salary = arr[i].year_salary() + arr[i].incen(incen);
				}else {
					System.out.println("인센티브 잘못 입력 ");
				}
			}
			
			System.out.println(arr[i].toString());
			System.out.println("<"+arr[i].getName() + "> 의 인센티브 포함 연봉은 " + String.format("%,3d", (int)year_salary) + "원 입니다. \n");
			System.out.println("#######################");

			
			
			
		}
		
		

	}

}
