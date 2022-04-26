package day14_DB_DAO;

public class Sql {
	//cust데이터베이스에 연결
	public static String insertCust = "INSERT INTO CUST VALUES (?,?,?)";
	public static String deleteCust = "DELETE FROM CUST WHERE id=?";
	public static String updateCust = "UPDATE CUST SET pwd=?, name=? WHERE id=?";
	public static String selectCust = "SELECT * FROM CUST WHERE id=?";
	public static String selectAllCust = "SELECT * FROM CUST";
	
	
	//Item 데이터베이스에 연결
	public static String insertItem = "INSERT INTO Item VALUES (?,?,?)";
	public static String deleteItem = "DELETE FROM Item WHERE id=?";
	public static String updateItem = "UPDATE Item SET name=?, price=? WHERE id=?";
	public static String selectItem = "SELECT * FROM Item WHERE id=?";
	public static String selectAllItem = "SELECT * FROM Item";
	
	
	// Product
	public static String insertProduct = "INSERT INTO product VALUES(NULL, ?, ?, SYSDATE(), ?)";
	public static String updateProduct = "UPDATE product SET name=?, price=?, rate=? WHERE id=?";
	public static String deleteProduct = "DELETE FROM product WHRER id=?";
	public static String selectProduct = "SELECT * FROM product WHERE id=?";
	public static String selectAllProduct = "SELECT * FROM product";
	
	

}
