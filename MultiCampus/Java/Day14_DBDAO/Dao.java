package day14_DB_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<K,V> {
	String url = "jdbc:mysql://192.168.0.8:3306/shopdb?serverTimezone=Asia/Seoul";
	String mid = "admin1";//root�δ� ������ �ȵȴ�. -> ����ڸ� ���������Ѵ�. 
	String mpwd = "0000";
	
	
	public Dao() {
		//Mysql JDBC Driver Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Mysql JDBC Driver Loading....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
		con = DriverManager.getConnection(url,mid,mpwd);
		
		return con;
		
	}
	
	// close �� �����ε� �Ѵ�. 
	public void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} 
	public void close(PreparedStatement ps) {
		if(ps != null ) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void close(ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// �߻��Լ��� ���׸�<> �� �̿��ؼ� ���� 
	public abstract void insert(V v) throws Exception;//�߻��Լ�- > �ؿ��� ������ �����ؾ��� 
	public abstract void update(V v) throws Exception;//�߻��Լ�- > �ؿ��� ������ �����ؾ��� 
	public abstract void delete(K k) throws Exception;//�߻��Լ�- > �ؿ��� ������ �����ؾ��� 
	public abstract V select(K k) throws Exception;//�߻��Լ�- > �ؿ��� ������ �����ؾ���
	public abstract List<V> selectAll() throws Exception;//�߻��Լ�- > �ؿ��� ������ �����ؾ���
	
	
	
	
	
	
	
}
