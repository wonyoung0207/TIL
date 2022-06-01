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
			System.out.println("mart ������ ���̽��� � �۾��� �Ͻðھ��? q(����), i(insert), u(update), d(delete), s(select), sall(selectAll) ");
			str = input.next();
			str = str.toLowerCase();
			
			if(str.equals("q")) {
				System.out.println("�����մϴ�. ");
				break;
			}
			else if(str.equals("i")) {
				System.out.println("���� ������ ��� ���� �Է�: �̸�, ����, ���� �� ������� �Է����ּ���.");
				name = input.next();
				price = input.nextDouble();
				status = input.next();
				meat = new MeatVo(name, price, status);
				try {
					dao.insert(meat);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
				
			}else if(str.equals("u")) {
				System.out.println("������Ʈ�� ��� ���� �Է� : �̸�, ����, ���� �� ������� �Է����ּ���.");
				name = input.next();
				price = input.nextDouble();
				status = input.next();
				meat = new MeatVo(name,price, status);
				try {
					dao.update(meat);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
			}else if(str.equals("d")) {
				System.out.println("������ ��� �̸��� �Է��� �ּ���.");
				name = input.next();
				try {
					dao.delete(name);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
				
			}else if(str.equals("s")) {
				System.out.println("����ϰ��� �ϴ� ��� �̸��� �Է��� �ּ���.");
				name = input.next();
				try {
					meat = dao.select(name);
					System.out.println(name + "�� ���� : " + meat.toString());
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");

				}
				
			}else if(str.equals("sall")) {
				System.out.println("������ ���̽��� ��� ������ ����մϴ�. ");
				try {
					list = dao.selectAll();
					for(MeatVo m : list) {
						System.out.println(m.toString());
					}
					
				} catch (Exception e) {
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
				
			}
			else {
				System.out.println("�߸��� �۾��� �����Ͽ����ϴ�. �ٽ� ������ �ּ���.");
			}
			
			
		}
		

	}

}
