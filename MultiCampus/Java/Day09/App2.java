package day10;

import java.util.ArrayList;
import java.util.Scanner;

public class App2 {

	public static void main(String[] args) {
		DAO dao = new OracleDAO2();
//		DAO maria_dao = new MariadbDAO();
		
		
		System.out.println("Start... ");
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("input cmd(i,s,d,a,u,q)");
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
				System.out.println("Input Delete Customer id : ");
				String id = input.next();
				dao.delete("id01");
				
			}else if(cmd.equals("s")) {
				System.out.println("Input Customer id :");
				String id = input.next();
				CustomerVO select_c = dao.select(id);
				System.out.println(select_c);
				
			}else if(cmd.equals("a")){
				ArrayList<CustomerVO> list = dao.select();
				for(CustomerVO c : list) {
					System.out.println(c);
				}
			}else if(cmd.equals("u")){
				System.out.println("Input Customer info...(id, pwd, name) ");
				String id = input.next();//데이터베이스에 있는 id 를 입력해야 한다. 
				String pwd = input.next();
				String name = input.next();
				
				CustomerVO c = new CustomerVO(id,pwd,name);
				dao.update(c);
			}
			
		}

	}

}
