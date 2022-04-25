package day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//String url = "jdbc:mysql://localhost:3306/JSPBookDB";
///* 사용할 jdbc의 diver를 가져올 주소와 프로토콜,사용할DB */
//String user = "root";
//String password = "0000";/* mysql 접속 패스워드 */
//
//Class.forName("com.mysql.jdbc.Driver");
///* JDBC 드라이버 로드. 이때 DriverManager 사용할 수 있게 됨 */
//conn = DriverManager.getConnection(url,user,password);
/* 데이터베이스 연결 */

public class UpdateCust {
	public static void main(String[] args) {
		//JDBC (Java Database Connectivity) Program
		
		// 변수선언
		Connection con = null;//어떤 데이터베이스에 접속할 건지 
		PreparedStatement ps = null;// SQL 문을 날릴때 사용 
		String sql = "UPDATE CUST SET pwd=?, name=? WHERE id=?";//sql 문장이 들어갈 변수 
		String sql2 = "UPDATE CUST SET pwd=?, name=? WHERE id=?";
		
		
		
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
		
		
		// MySQL Connect
		//mysql에 접속할 때 필요한 id와 비밀번호 
		// root , 0000
//		String url = "jdbc:mysql://localhost:3306/shopdb?serverTimezone=Asia/Seoul";//"jdbc:mysql://192.168.0.8";
//		String url = "jdbc:mysql://192.168.0.8:3306/shopdb?serverTimezone=Asia/Seoul";
		//127.0.0.1 이 내 컴퓨터의 아이피이다.
		
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
			ps.setString(1, "1111");//0이 아닌 1부터 시작한다. 
			ps.setString(2, "정말숙");
			ps.setString(3, "id11");
			
			//요청 결과를 확인
			int result = ps.executeUpdate();
			System.out.println(result);//몇개가 업데이트 되는지 출력 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//무조건 실행되는 구간
			// MySQL Close
			//사용하고 나면 꼭 close로 닫아줘야 한다. 
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
