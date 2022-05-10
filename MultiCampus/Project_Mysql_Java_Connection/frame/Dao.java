package frame;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao <K,V> {


	//MySQL Connect 각자 자기 꺼로 고치기
	String url= "jdbc:mysql://192.168.0.8:3306/martdb?serverTimezone=Asia/Seoul";
	String mid="admin1";
	String mpwd="0000";

	public Dao() {
		// MySQL JDBC Driver Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection(url,mid,mpwd);	
		return con;
	}
	public void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		}
	}
	public void close(PreparedStatement ps) {
		if(ps !=null) {
			try {
				ps.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
	}	
	public void close(ResultSet rset) {
		if(rset !=null) {
			try {
				rset.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
	}

	public abstract void insert(V v) throws Exception;
	public abstract void delete(K k) throws Exception;
	public abstract void update(V v) throws Exception;
	public abstract V select(K k) throws Exception;
	public abstract List<V> selectAll()throws Exception;

}
