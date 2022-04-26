package day14_DB_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustDao extends Dao<String, CustVo> {
	

	@Override
	public void insert(CustVo v) throws Exception {
		// Connection 준비
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();//con 은 어떤 데이터베이스에 연결할 것인지를 설정.
			
			ps = con.prepareStatement(Sql.insertCust);// Connection 에 있는 메소드를 이용해서 sql문을 전달. 
			ps.setString(1, v.getId());
			ps.setString(2, v.getPwd());
			ps.setString(3, v.getName());
			ps.executeUpdate();//ps를 전달한다.
			System.out.println("Insert Success...");
			
			
		}catch(Exception e) {
			System.out.println("*** My insert Error ***");

			throw e;
		}finally {
			close(ps);
			close(con);
			
		}
		
	}

	@Override
	public void update(CustVo v) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(Sql.updateCust);
			ps.setString(1, v.getPwd());
			ps.setString(2, v.getName());
			ps.setString(3, v.getId());
			ps.executeUpdate();
			System.out.println("Update Success...");
			
			
		}catch(Exception e) {
			System.out.println("*** My update Error ***");

			throw e;
		}finally{
			close(ps);
			close(con);
		}

	}

	@Override
	public void delete(String k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.deleteCust);
			ps.setString(1, k);
			ps.executeUpdate();
			
			System.out.println("Delete Success...");
		}catch(Exception e) {
			System.out.println("*** My delete Error ***");

			throw e;
		}finally{
			close(ps);
			close(con);
		}
	}

	@Override
	public CustVo select(String k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CustVo cust;
		
		
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectCust);
			ps.setString(1, k);
			rs = ps.executeQuery();
			rs.next();
			
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			
			cust = new CustVo(id, pwd, name);
			
			System.out.println("Select Success...");
			
			
		}catch(Exception e) {
			System.out.println("*** My select Error ***");
			throw e;
		}finally {
			close(ps);
			close(con);
			close(rs);
			
		}

		return cust;
	}

	@Override
	public List<CustVo> selectAll() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustVo> list = new ArrayList<CustVo>();
		String id="", pwd="", name="";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectAllCust);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getString("id");
				pwd = rs.getString("pwd");
				name = rs.getString("name");
				
				list.add(new CustVo(id, pwd, name));
			}
			
			System.out.println("SelectAll Success...");
			
		}catch(Exception e) {
			System.out.println("*** My selectAll Error ***");
			throw e;
		}finally {
			close(ps);
			close(con);
			close(rs);
			
			
		}
		return list;
	}

}
