package day14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SelectCust {
	public static void main(String[] args) {
		//JDBC (Java Database Connectivity) Program
		
		// 변수선언
		Connection con = null;//어떤 데이터베이스에 접속할 건지 
		PreparedStatement ps = null;// SQL 문을 날릴때 사용 
		ResultSet rs = null;
		
		String sql = "SELECT * FROM item WHERE id=?";//sql 문장이 들어갈 변수 
		
		
		// MySQL JDBC Driver Loading
		// 사용할 라이브러리 가져오기 
		try {
//			Class.forName("com.mysql.jdbc.Driver");//.jar 파일 안의 driver 클래스를 올린다. 
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Mysql JDBC Driver Loading....");
		} catch (ClassNotFoundException e) {
			//컴퓨터 내에서 파일을 삭제했을 경우, 해당 드라이버를 찾을 수 없기때문에
			e.printStackTrace();
		}
		
		
		String url = "jdbc:mysql://192.168.0.8:3306/shopdb?serverTimezone=Asia/Seoul";
		String mid = "admin1";//root로는 접속이 안된다. -> 사용자를 만들어줘야한다. 
		String mpwd = "0000";
		
		try {
			con = DriverManager.getConnection(url,mid,mpwd);
			System.out.println("Mysql Server Connected...");
		} catch (SQLException e) {
			e.printStackTrace();
		}//
		
		
		//SQL 을 이용한 요청	
		try {
			ps = con.prepareStatement(sql);
			//sql 변수의 ? 에 순차적으로 들어간다. 
			ps.setInt(1, 105);//0이 아닌 1부터 시작한다. 

			
			//요청 결과를 확인
			//int result = ps.executeUpdate();//테이블을 변경하고자 할때는executeUpdate 사용
			rs = ps.executeQuery();//테이블을 가져오기만 할 경우 executeQuery()
			rs.next();//한칸을 이동하고 꺼내야 한다. 
			//next 를 통해 행을 바꿀 수 있다. 바로 사용할 경우, 아무것도 가르키지 않기 때문에 next를 해서 행을 이동시켜야 한다. 
			
			
			String id = rs.getString("id");
			String price = rs.getString("price");
			String name = rs.getString("name");
			
			System.out.println(id+ " " + price + " " + name);
			
//			System.out.println(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//무조건 실행되는 구간
			// MySQL Close
			//사용하고 나면 꼭 close로 닫아줘야 한다. 
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
