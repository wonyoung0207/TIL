package app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.MeatDao;
import frame.Dao;
import vo.MeatVo;

public class App_Meat {

	public static void main(String[] args) {
		Dao<String, MeatVo> dao = new MeatDao();
		List<MeatVo> list = null;
		Scanner input = new Scanner(System.in);
		String str;
		String name="";
		String status="";
		double price=0;
		Date date = null;
		MeatVo meat = null;
		
		
		
		while(true) {
			System.out.println("mart 데이터 베이스의 어떤 작업을 하시겠어요? q(종료), i(insert), u(update), d(delete), s(select), sall(selectAll) ");
			str = input.next();
			str = str.toLowerCase();
			
			if(str.equals("q")) {
				System.out.println("종료합니다. ");
				break;
			}
			else if(str.equals("i")) {
				System.out.println("새로 생성할 고기 정보 입력: 이름, 가격, 상태 를 순서대로 입력해주세요.");
				name = input.next();
				price = input.nextDouble();
				status = input.next();
				meat = new MeatVo(name, price, status);
				try {
					dao.insert(meat);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
				
			}else if(str.equals("u")) {
				System.out.println("업데이트할 고기 정보 입력 : 이름, 가격, 상태 를 순서대로 입력해주세요.");
				name = input.next();
				price = input.nextDouble();
				status = input.next();
				meat = new MeatVo(name,price, status);
				try {
					dao.update(meat);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
			}else if(str.equals("d")) {
				System.out.println("삭제할 고기 이름을 입력해 주세요.");
				name = input.next();
				try {
					dao.delete(name);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
				
			}else if(str.equals("s")) {
				System.out.println("출력하고자 하는 고기 이름을 입력해 주세요.");
				name = input.next();
				try {
					meat = dao.select(name);
					System.out.println(name + "의 정보 : " + meat.toString());
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");

				}
				
			}else if(str.equals("sall")) {
				System.out.println("데이터 베이스의 모든 정보를 출력합니다. ");
				try {
					list = dao.selectAll();
					for(MeatVo m : list) {
						System.out.println(m.toString());
					}
					
				} catch (Exception e) {
					System.out.println("정보에 이상이 있습니다. 처음으로 돌아갑니다. ");
				}
				
			}
			else {
				System.out.println("잘못된 작업을 선택하였습니다. 다시 선택해 주세요.");
			}
			
			
		}
		

	}

}
