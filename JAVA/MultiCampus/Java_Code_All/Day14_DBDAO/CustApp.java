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
			System.out.println("������ ���̽��� � �۾��� �Ͻðھ��? q(����), i(insert), u(update), d(delete), s(select), sall(selectAll) ");
			str = input.next();
			if(str.equals("q")) {
				System.out.println("�����մϴ�. ");
				break;
			}
			else if(str.equals("i")) {
				System.out.println("���� ������ id, pwd, name �� ������� �Է����ּ���.");
				id = input.next();
				pwd = input.next();
				name = input.next();
				cust = new CustVo(id, pwd, name);
				try {
					dao.insert(cust);
				} catch (Exception e) {
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
				
			}else if(str.equals("u")) {
				System.out.println("������Ʈ�� id, pwd, name�� ������� �Է����ּ���.");
				id = input.next();
				pwd = input.next();
				name = input.next();
				cust = new CustVo(id, pwd, name);
				try {
					dao.update(cust);
				} catch (Exception e) {
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
			}else if(str.equals("d")) {
				System.out.println("������ id ���� �Է��� �ּ���.");
				id = input.next();
				try {
					dao.delete(id);
				} catch (Exception e) {
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
				
			}else if(str.equals("s")) {
				System.out.println("����ϰ��� �ϴ� id�� id ���� �Է��� �ּ���");
				id = input.next();
				try {
					cust = dao.select(id);
					System.out.println(id + "�� ���� : " + cust.toString());
				} catch (Exception e) {
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");

				}
				
			}else if(str.equals("sall")) {
				System.out.println("������ ���̽��� ��� ������ ����մϴ�. ");
				try {
					list = dao.selectAll();
					for(CustVo a : list) {
						System.out.println(a.toString());
					}
					
				} catch (Exception e) {
					System.out.println("������ �̻��� �ֽ��ϴ�. ó������ ���ư��ϴ�. ");
				}
				
			}else {
				System.out.println("�߸��� �۾��� �����Ͽ����ϴ�. �ٽ� ������ �ּ���.");
			}
			
			
		}
	}
		
}
