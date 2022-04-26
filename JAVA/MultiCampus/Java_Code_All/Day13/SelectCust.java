package day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//String url = "jdbc:mysql://localhost:3306/JSPBookDB";
///* ����� jdbc�� diver�� ������ �ּҿ� ��������,�����DB */
//String user = "root";
//String password = "0000";/* mysql ���� �н����� */
//
//Class.forName("com.mysql.jdbc.Driver");
///* JDBC ����̹� �ε�. �̶� DriverManager ����� �� �ְ� �� */
//conn = DriverManager.getConnection(url,user,password);
/* �����ͺ��̽� ���� */

public class SelectCust {
	public static void main(String[] args) {
		//JDBC (Java Database Connectivity) Program
		
		// ��������
		Connection con = null;//� �����ͺ��̽��� ������ ���� 
		PreparedStatement ps = null;// SQL ���� ������ ��� 
		ResultSet rs = null;
		
		String sql = "SELECT * FROM CUST WHERE id=?";//sql ������ �� ���� 
		
		
		// MySQL JDBC Driver Loading
		// ����� ���̺귯�� �������� 
		try {
//			Class.forName("com.mysql.jdbc.Driver");//.jar ���� ���� driver Ŭ������ �ø���. 
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Mysql JDBC Driver Loading....");
		} catch (ClassNotFoundException e) {
			//��ǻ�� ������ ������ �������� ���, �ش� ����̹��� ã�� �� ���⶧����
			e.printStackTrace();
		}
		
		
		// MySQL Connect
		//mysql�� ������ �� �ʿ��� id�� ��й�ȣ 
		// root , 0000
//		String url = "jdbc:mysql://localhost:3306/shopdb?serverTimezone=Asia/Seoul";//"jdbc:mysql://192.168.0.8";
//		String url = "jdbc:mysql://192.168.0.8:3306/shopdb?serverTimezone=Asia/Seoul";
		//127.0.0.1 �� �� ��ǻ���� �������̴�.
		
		String url = "jdbc:mysql://192.168.0.8:3306/shopdb?serverTimezone=Asia/Seoul";
		String mid = "admin1";//root�δ� ������ �ȵȴ�. -> ����ڸ� ���������Ѵ�. 
		String mpwd = "0000";
		
		try {
			con = DriverManager.getConnection(url,mid,mpwd);
			System.out.println("Mysql Server Connected...");
		} catch (SQLException e) {
			e.printStackTrace();
		}//
		
		
		//SQL �� �̿��� ��û	
		try {
			ps = con.prepareStatement(sql);
			//sql ������ ? �� ���������� ����. 
			ps.setString(1, "id05");//0�� �ƴ� 1���� �����Ѵ�. 

			
			//��û ����� Ȯ��
			//int result = ps.executeUpdate();//���̺��� �����ϰ��� �Ҷ���executeUpdate ���
			rs = ps.executeQuery();//���̺��� �������⸸ �� ��� executeQuery()
			rs.next();//��ĭ�� �̵��ϰ� ������ �Ѵ�. 
			
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			
			System.out.println(id+ " " + pwd + " " + name);
			
//			System.out.println(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//������ ����Ǵ� ����
			// MySQL Close
			//����ϰ� ���� �� close�� �ݾ���� �Ѵ�. 
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(ps != null ) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		

	}

}
