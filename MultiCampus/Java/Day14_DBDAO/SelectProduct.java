package day14_DB_DAO;

public class SelectProduct {

	public static void main(String[] args) {
		
		Dao<Integer, ProductVo> dao = new ProductDao();
		ProductVo p = null;
		
		try {
			p = dao.select(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
