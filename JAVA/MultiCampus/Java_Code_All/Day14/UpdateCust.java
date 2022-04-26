package day14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateCust {
	public static void main(String[] args) {
		//JDBC (Java Database Connectivity) Program
		
		// ��������
		Connection con = null;//� �����ͺ��̽��� ������ ���� 
		PreparedStatement ps = null;// SQL ���� ������ ��� 
		String sql = "UPDATE item SET price=?, name=? WHERE id=?";//sql ������ �� ���� 
		String sql2 = "UPDATE item SET price=?, name=? WHERE id=?";
		
		
		
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
			ps.setFloat(1, 1000);//0�� �ƴ� 1���� �����Ѵ�. 
			ps.setString(2, "product55");
			ps.setInt(3, 105);
			
			//��û ����� Ȯ��
			int result = ps.executeUpdate();
			System.out.println(result);//��� ������Ʈ �Ǵ��� ��� 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//������ ����Ǵ� ����
			// MySQL Close
			//����ϰ� ���� �� close�� �ݾ���� �Ѵ�. 
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
