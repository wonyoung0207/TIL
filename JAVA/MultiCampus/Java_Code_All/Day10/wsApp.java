package day10;

import java.util.Scanner;

public class wsApp {

	public static void main(String[] args) {
		Ifruit f = new FruitDAO();//��ü ���� 
		Scanner input = new Scanner(System.in);
		String n;
		
		while(true) {
			System.out.println("� ���� �����Ͻðڽ��ϱ�? C(create), R(read) U(Update) D(Delete) ");
			n = input.next();
			n = n.toLowerCase();//�ҹ��ڷ� ���� 
			if(n.equals("c")) {
				System.out.println("������ ���� ������ ������� �Է��ϼ���. �����̸�, ����, ����");
				String name = input.next();				
				double price = Double.parseDouble(input.next());
				String condition = input.next();
				f.create(new fruit(name, price, condition));
				
				
			}else if(n.equals("r")) {
				System.out.println("���� �̸��� �Է��ϸ� �ش� ���� ������ �����ɴϴ�. ");
				n = input.next();
				fruit read_f = f.read(n);
				if(read_f != null) {
					System.out.println(read_f);
				}
				
			}else if(n.equals("u")) {
				System.out.println("������Ʈ�� ������ �̸�, ����, ���¸� �Է��ϼ���. ");
				String name = input.next();				
				double price = Double.parseDouble(input.next());
				String condition = input.next();
				
				int check_correct = f.update(new fruit(name, price, condition));
				if(check_correct == 1) {
					System.out.println("������Ʈ ����!");
				}else if(check_correct == 0){
					System.out.println("������Ʈ ���� ");
				}
			}else if(n.equals("d")) {
				System.out.println("������ ������ �̸��� �Է��ϼ���. ");
				n = input.next();				
				
				int check_correct = f.delete(n);
				if(check_correct == 1) {
					System.out.println("���� ����!");
				}else if(check_correct == 0){
					System.out.println("���� ���� ");
				}
			}
			
			
		}
		

	}

}
