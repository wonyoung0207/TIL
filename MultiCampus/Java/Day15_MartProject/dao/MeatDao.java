package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import frame.Dao;
import frame.Sql;
import vo.MeatVo;

public class MeatDao extends Dao<String, MeatVo>{
	

	@Override
	public void insert(MeatVo v) throws Exception {
		// Connection 준비
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();//con 은 어떤 데이터베이스에 연결할 것인지를 설정.
			
			ps = con.prepareStatement(Sql.insertMeat);// Connection 에 있는 메소드를 이용해서 sql문을 전달. 
			ps.setString(1, v.getName());
			ps.setDouble(2, v.getPrice());
			ps.setString(3, v.getStatus());
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
	public void update(MeatVo v) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			
			ps = con.prepareStatement(Sql.updateMeat);
			ps.setDouble(1, v.getPrice());
			ps.setString(2, v.getStatus());
			ps.setString(3, v.getName());
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
			ps = con.prepareStatement(Sql.deleteMeat);
			ps.setString(1, k);
			int result = ps.executeUpdate();
			if(result != 1) {//delete가 일어나지 않았을 경우
				throw new Exception("입력한 값이 존재하지 않습니다. ");
			}
			
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
	public MeatVo select(String k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MeatVo meat;
		
		
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectMeat);
			ps.setString(1, k);
			rs = ps.executeQuery();
			rs.next();
			
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			String status = rs.getString("status");
			Date date = rs.getDate("date");
			
			
			
			meat = new MeatVo(name, price, status, date);
			
			System.out.println("Select Success...");
			
			
		}catch(Exception e) {
			System.out.println("*** My select Error ***");
			throw e;
		}finally {
			close(ps);
			close(con);
			close(rs);
			
		}

		return meat;
	}

	@Override
	public List<MeatVo> selectAll() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MeatVo> list = new ArrayList<MeatVo>();
		String status="", name="";
		double price = 0;
		Date date = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectAllMeat);
			rs = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				status = rs.getString("status");
				price = rs.getDouble("price");
				date = rs.getDate("date");
				
				list.add(new MeatVo(name, price, status,date));
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
