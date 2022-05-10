package day07;

import java.util.Scanner;

public class Car_main {

	public static void main(String[] args) {
		Car car;
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("input cmd (q,c,d) ");
			String cmd = input.next();
			if(cmd.equals("q")) {
				System.out.println("Á¾·á");
				break;
				
			}else if(cmd.equals("c")) {
				System.out.println("input name..");
				String name = input.next();
				
				System.out.println("input fsize..");
				double fsize = Double.parseDouble(input.next());
				
				System.out.println("input fe..");
				double fe = Double.parseDouble(input.next());
				
				car = new Car(name, fsize, fe);
				System.out.println(car);
				
			}else if(cmd.equals("d")) {
				
			}
		}
		

	}

}
