package day10;

import java.util.Scanner;

public class wsApp {

	public static void main(String[] args) {
		Ifruit f = new FruitDAO();//객체 생성 
		Scanner input = new Scanner(System.in);
		String n;
		
		while(true) {
			System.out.println("어떤 일을 진행하시겠습니까? C(create), R(read) U(Update) D(Delete) ");
			n = input.next();
			n = n.toLowerCase();//소문자로 변경 
			if(n.equals("c")) {
				System.out.println("생성할 과일 정보를 순서대로 입력하세요. 과일이름, 가격, 상태");
				String name = input.next();				
				double price = Double.parseDouble(input.next());
				String condition = input.next();
				f.create(new fruit(name, price, condition));
				
				
			}else if(n.equals("r")) {
				System.out.println("과일 이름을 입력하면 해당 과일 정보를 가져옵니다. ");
				n = input.next();
				fruit read_f = f.read(n);
				if(read_f != null) {
					System.out.println(read_f);
				}
				
			}else if(n.equals("u")) {
				System.out.println("업데이트할 과일의 이름, 가격, 상태를 입력하세요. ");
				String name = input.next();				
				double price = Double.parseDouble(input.next());
				String condition = input.next();
				
				int check_correct = f.update(new fruit(name, price, condition));
				if(check_correct == 1) {
					System.out.println("업데이트 성공!");
				}else if(check_correct == 0){
					System.out.println("업데이트 실패 ");
				}
			}else if(n.equals("d")) {
				System.out.println("삭제할 과일의 이름을 입력하세요. ");
				n = input.next();				
				
				int check_correct = f.delete(n);
				if(check_correct == 1) {
					System.out.println("삭제 성공!");
				}else if(check_correct == 0){
					System.out.println("삭제 실패 ");
				}
			}
			
			
		}
		

	}

}
