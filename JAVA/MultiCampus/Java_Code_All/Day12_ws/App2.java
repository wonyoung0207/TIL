package day12_ws;

import java.util.ArrayList;
import java.util.Scanner;

public class App2 {

	public static void main(String[] args) {
		FruitDAO f = new FruitDAO();//객체 생성 
		VegetableDAO v = new VegetableDAO();//객체 생성 
		
		Scanner input = new Scanner(System.in);
		String n;
		
//		while(true) {
//			System.out.println("어떤 상점을 방문할까요? f(fruit) , v(vegetable)");
//			n = input.next();
//			n = n.toLowerCase();//소문자로 변경 
//			if(n.equals("f")) {
//				FruitDAO dao = new FruitDAO();
//				break;
//				
//			}else if(n.equals("v")) {
//				VegetableDAO dao = new VegetableDAO();
//				break;
//			}else {
//				System.out.println("없는 상점입니다. ");
//			}
//		}
		
		while(true) {
			
			System.out.println("어떤 일을 진행하시겠습니까? C(create), R(read), U(Update), D(Delete), AllR(alll select) ");
			n = input.next();
			n = n.toLowerCase();//소문자로 변경 
			if(n.equals("c")) {

				try {
					System.out.println("생성할 과일 정보를 순서대로 입력하세요. 과일이름, 가격, 상태");
					String name = input.next();				
//					double price = Double.parseDouble(input.next());
					String price = input.next();
					String condition = input.next();
					f.create(new fruit(name, price, condition));
					
				}catch(Exception e) {
					System.out.println(e);
				}
				
				
			}else if(n.equals("r")) {
				System.out.println("과일 이름을 입력하면 해당 과일 정보를 가져옵니다. ");
				n = input.next();
				fruit read_f;
				try {
					read_f = f.read(n);
					System.out.println(read_f);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				
			}else if(n.equals("u")) {
				System.out.println("업데이트할 과일의 이름, 가격, 상태를 입력하세요. ");
				String name = input.next();				
//				double price = Double.parseDouble(input.next());
				String price = input.next();
				String condition = input.next();
				
				try {
					f.update(new fruit(name, price, condition));
				} catch(Exception e) {
					System.out.println(e);
				}

				
			}else if(n.equals("d")) {
				System.out.println("삭제할 과일의 이름을 입력하세요. ");
				n = input.next();				
				
				try {
					f.delete(n);
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}

			}else if(n.equals("allr")) {
				System.out.println("현재 존재하는 과일 데이터를 모두 출력합니다. ");
				ArrayList<fruit> list = null;
				try {
					list = f.search();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				for(fruit a : list) {
					System.out.println(a.toString());
				}
			}
		}
	}

}
