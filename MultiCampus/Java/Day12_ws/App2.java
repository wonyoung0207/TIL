package day12_ws;

import java.util.ArrayList;
import java.util.Scanner;

public class App2 {

	public static void main(String[] args) {
		FruitDAO f = new FruitDAO();//��ü ���� 
		VegetableDAO v = new VegetableDAO();//��ü ���� 
		
		Scanner input = new Scanner(System.in);
		String n;
		
//		while(true) {
//			System.out.println("� ������ �湮�ұ��? f(fruit) , v(vegetable)");
//			n = input.next();
//			n = n.toLowerCase();//�ҹ��ڷ� ���� 
//			if(n.equals("f")) {
//				FruitDAO dao = new FruitDAO();
//				break;
//				
//			}else if(n.equals("v")) {
//				VegetableDAO dao = new VegetableDAO();
//				break;
//			}else {
//				System.out.println("���� �����Դϴ�. ");
//			}
//		}
		
		while(true) {
			
			System.out.println("� ���� �����Ͻðڽ��ϱ�? C(create), R(read), U(Update), D(Delete), AllR(alll select) ");
			n = input.next();
			n = n.toLowerCase();//�ҹ��ڷ� ���� 
			if(n.equals("c")) {

				try {
					System.out.println("������ ���� ������ ������� �Է��ϼ���. �����̸�, ����, ����");
					String name = input.next();				
//					double price = Double.parseDouble(input.next());
					String price = input.next();
					String condition = input.next();
					f.create(new fruit(name, price, condition));
					
				}catch(Exception e) {
					System.out.println(e);
				}
				
				
			}else if(n.equals("r")) {
				System.out.println("���� �̸��� �Է��ϸ� �ش� ���� ������ �����ɴϴ�. ");
				n = input.next();
				fruit read_f;
				try {
					read_f = f.read(n);
					System.out.println(read_f);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				
			}else if(n.equals("u")) {
				System.out.println("������Ʈ�� ������ �̸�, ����, ���¸� �Է��ϼ���. ");
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
				System.out.println("������ ������ �̸��� �Է��ϼ���. ");
				n = input.next();				
				
				try {
					f.delete(n);
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}

			}else if(n.equals("allr")) {
				System.out.println("���� �����ϴ� ���� �����͸� ��� ����մϴ�. ");
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
