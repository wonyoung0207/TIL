package day14_DB_DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ProductDao extends Dao<Integer, ProductVo>{

	@Override
	public void insert(ProductVo v) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();//con 은 어떤 데이터베이스에 연결할 것인지를 설정.
			
			ps = con.prepareStatement(Sql.insertProduct);// Connection 에 있는 메소드를 이용해서 sql문을 전달. 
			ps.setString(1, v.getName());
			ps.setInt(2, v.getPrice());
			ps.setDouble(3, v.getRate());
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
	public void update(ProductVo v) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(Sql.updateCust);
			ps.setString(1, v.getName());
			ps.setInt(2, v.getPrice());
			ps.setDouble(3, v.getRate());
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
	public void delete(Integer k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.deleteCust);
			ps.setInt(1, k);
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
	public ProductVo select(Integer k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductVo p;
		
		
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectProduct);
			ps.setInt(1, k);
			rs = ps.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int price = rs.getInt("pricee");
			Date date = rs.getDate("regdate");
			double rate = rs.getDouble("rate");
			
			p = new ProductVo(id, name, price, date, rate);
			
			System.out.println("Select Success...");
			
			
		}catch(Exception e) {
			System.out.println("*** My select Error ***");
			throw e;
		}finally {
			close(ps);
			close(con);
			close(rs);
			
		}

		return p;
	}

	@Override
	public List<ProductVo> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
