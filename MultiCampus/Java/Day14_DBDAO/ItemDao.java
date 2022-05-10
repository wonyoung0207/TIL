package day14_DB_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends Dao<Integer,ItemVo>{

	@Override
	public void insert(ItemVo v) throws Exception {
		// Connection 준비
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();//con 은 어떤 데이터베이스에 연결할 것인지를 설정.

			ps = con.prepareStatement(Sql.insertItem);// Connection 에 있는 메소드를 이용해서 sql문을 전달. 
			ps.setInt(1, v.getId());
			ps.setString(2, v.getName());
			ps.setDouble(3, v.getPrice());
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
	public void update(ItemVo v) throws Exception {
		// Connection 준비
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();//con 은 어떤 데이터베이스에 연결할 것인지를 설정.

			ps = con.prepareStatement(Sql.updateItem);// Connection 에 있는 메소드를 이용해서 sql문을 전달. 
			ps.setString(1, v.getName());
			ps.setDouble(2, v.getPrice());
			ps.setInt(3, v.getId());
			ps.executeUpdate();//ps를 전달한다.
			System.out.println("Update Success...");


		}catch(Exception e) {
			System.out.println("*** My Update Error ***");

			throw e;
		}finally {
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
			ps = con.prepareStatement(Sql.deleteItem);
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
	public ItemVo select(Integer k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ItemVo item;

		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectItem);
			ps.setInt(1, k);
			rs = ps.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String name = rs.getString("name");
			double price = rs.getDouble("price");

			item = new ItemVo(id, name, price);

			System.out.println("Select Success...");


		}catch(Exception e) {
			System.out.println("*** My select Error ***");
			throw e;
		}finally {
			close(ps);
			close(con);
			close(rs);

		}

		return item;

	}

	@Override
	public List<ItemVo> selectAll() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ItemVo> list = new ArrayList<ItemVo>();
		int id= 0;
		double price =0;
		String name="";

		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectAllCust);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				price = rs.getDouble("price");

				list.add(new ItemVo(id, name, price));
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
