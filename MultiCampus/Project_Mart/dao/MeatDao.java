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
		// Connection �غ�
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();//con �� � �����ͺ��̽��� ������ �������� ����.
			
			ps = con.prepareStatement(Sql.insertMeat);// Connection �� �ִ� �޼ҵ带 �̿��ؼ� sql���� ����. 
			ps.setString(1, v.getName());
			ps.setDouble(2, v.getPrice());
			ps.setString(3, v.getStatus());
			ps.executeUpdate();//ps�� �����Ѵ�.
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
			if(result != 1) {//delete�� �Ͼ�� �ʾ��� ���
				throw new Exception("�Է��� ���� �������� �ʽ��ϴ�. ");
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
