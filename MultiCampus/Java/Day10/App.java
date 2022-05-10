package day10;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		OracleDAO dao = new OracleDAO();
		
		
		System.out.println("Start... ");
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("input cmd(i,s,d,a,q)");
			String cmd = input.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				return ;
			}
			else if(cmd.equals("i")) {
				System.out.println("Input Customer info...(id, pwd, name) ");
				String id = input.next();
				String pwd = input.next();
				String name = input.next();
				
				CustomerVO c = new CustomerVO(id,pwd,name);
				dao.insert(c);
				
			}else if(cmd.equals("d")){
				System.out.println("Input Customer id : ");
				String id = input.next();
				dao.delete(id);
				
			}else if(cmd.equals("s")) {
				System.out.println("Input Customer id :");
				String id = input.next();
				CustomerVO select_c = dao.selet(id);
				System.out.println(select_c);
			}else if(cmd.equals("a")){
				ArrayList<CustomerVO> list = dao.select();
				for(CustomerVO c : list) {
					System.out.println(c);
				}
			}
			
		}

	}

}
