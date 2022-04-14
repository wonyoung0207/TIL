package day06;
//2차원 배열 
// 과일 이름과 가격으로 된 배열 구성 => 4개의 야체라고 치면, 행이 4, 열이 2로, 열은 이름과 가격을 나타냄
// 과일 몇개 살건지 입력
// 이 과일는 평균보다 얼마 높습니다.
// 총 얼마가 나왔습니다.

import java.util.Scanner;

public class ws01_fruit {
	public static void main(String[] args) {
		String arr[][] = {
				{"사과", "1000"},
				{"바나나", "2000"},
				{"배", "3000"},
				{"포도", "3000"},
				{"딸기", "2000"}
		};
		
		
		Scanner input = new Scanner(System.in);
		
		int num = 0;//살 갯수 입력 
		int my_money;
		int fruit_find_fail = 0;//0이면 찾은거, 1이면 못찾은거 
		int price = 0;// 과일 가격
		String str;
		String fruit = null;
		
		
		while(true) {
			System.out.println("나의 예산을 입력해 주세요. ");
			my_money = input.nextInt();
			if(my_money > 0) {
				break;
			}else {
				System.out.println("예산은 -가 아닙니다. 다시입력해주세요.");
			}
		}
		

		for(int i=0; i < arr.length; i++) {
			for(int j=0; j< 2; j=j+2) {
				
				int fruit_price = Integer.parseInt(arr[i][1]);//과일 가격을 저장하는 변수 
				
				if( my_money >= fruit_price ) {//현재 금액으로 구매할 수있는 과일 리스트 
					int count =0;
					int a = my_money / fruit_price;//10
					int b = my_money % fruit_price;//10
					System.out.println("나의 예산 "+ my_money + "으로 " + arr[i][0] + "를 " + a + "개 살수 있습니다. ");
				}
			}
		}

		while(true) {
			System.out.println("사고싶은 과일의 이름을 입력하세요. (사과,바나나,배,포도,딸기)");
			str = input.next();
			
			for(int i=0; i< arr.length; i++) {
				if(arr[i][0].equals(str)) {
					fruit = arr[i][0];
					price = Integer.parseInt(arr[i][1]);
					break;
				}else {
					fruit = "fail";
					price = 0;
				}
			}
			
			if((my_money < price ) || (fruit.equals("fail"))) {
				System.out.println("입력한 과일의 가격이 현재 예산보다 비싸거나 과일을 찾지 못했습니다. 종료합니다.");
				return ;
			}else {
				break;
			}
			
		}
		
		System.out.println("입력된 과일 정보 : " + fruit + "," + price);
		System.out.println(fruit + "과일 " + my_money / price + "개를 살수 있습니다. 거스름돈은 " + my_money % price + " 입니다. ");
		
	}
}
