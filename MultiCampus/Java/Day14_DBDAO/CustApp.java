package day14_DB_DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustApp {

	public static void main(String[] args) {
		Dao<String, CustVo> dao = new CustDao();
		List<CustVo> list = null;
		Scanner input = new Scanner(System.in);
		String str;
		String id="";
		String pwd="";
		String name="";
		CustVo cust = null;
		
		
		
		while(true) {
			System.out.println("데이터 베이스의 어떤 작업을 하시겠어요? q(종료), i(insert), u(update), d(delete), s(select), sall(selectAll) ");
			str = input.next();
			if(str.equals("q")) {
				System.out.println("종료합니다. ");
				break;
			}
			else if(str.equals("i")) {
				System.out.println("새로 생성할 id, pwd, name 을 순서대로 입력해주세요.");
				id = input.next();
				pwd = input.next();
				name = input.next();
				cust = new CustVo(id, pwd, name);
				try {
					dao.insert(cust);
				} catch (Exception e) {
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
				
			}else if(str.equals("u")) {
				System.out.println("업데이트할 id, pwd, name을 순서대로 입력해주세요.");
				id = input.next();
				pwd = input.next();
				name = input.next();
				cust = new CustVo(id, pwd, name);
				try {
					dao.update(cust);
				} catch (Exception e) {
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
			}else if(str.equals("d")) {
				System.out.println("삭제할 id 값을 입력해 주세요.");
				id = input.next();
				try {
					dao.delete(id);
				} catch (Exception e) {
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
				
			}else if(str.equals("s")) {
				System.out.println("출력하고자 하는 id의 id 값을 입력해 주세요");
				id = input.next();
				try {
					cust = dao.select(id);
					System.out.println(id + "의 정보 : " + cust.toString());
				} catch (Exception e) {
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");

				}
				
			}else if(str.equals("sall")) {
				System.out.println("데이터 베이스의 모든 정보를 출력합니다. ");
				try {
					list = dao.selectAll();
					for(CustVo a : list) {
						System.out.println(a.toString());
					}
					
				} catch (Exception e) {
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
				
			}else {
				System.out.println("잘못된 작업을 선택하였습니다. 다시 선택해 주세요.");
			}
			
			
		}
	}
		
}
