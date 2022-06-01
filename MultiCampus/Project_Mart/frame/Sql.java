package frame;

public class Sql {
	public static String insertFruit = "INSERT INTO Fruit VALUES (?,?,?,SYSDATE())";
	public static String deleteFruit = "DELETE FROM Fruit WHERE name=?";
	public static String updateFruit = "UPDATE Fruit SET price=?, status=? WHERE name=?";  
	public static String selectFruit = 	"SELECT * FROM Fruit WHERE name = ?";
	public static String selectAllFruit = "SELECT * FROM Fruit";
	
	public static String insertFish = "INSERT INTO Fish VALUES (?,?,?,SYSDATE())";
	public static String deleteFish = "DELETE FROM Fish WHERE name=?";
	public static String updateFish = "UPDATE Fish SET price=?, status=? WHERE name=?";  
	public static String selectFish = 	"SELECT * FROM Fish WHERE name = ?";
	public static String selectAllFish = "SELECT * FROM Fish";
	
	public static String insertMeat = "INSERT INTO Meat VALUES (?,?,?,SYSDATE())";
	public static String deleteMeat = "DELETE FROM Meat WHERE name=?";
	public static String updateMeat = "UPDATE Meat SET price=?, status=? WHERE name=?";  
	public static String selectMeat = 	"SELECT * FROM Meat WHERE name = ?";
	public static String selectAllMeat = "SELECT * FROM Meat";
	
	public static String insertVegetable = "INSERT INTO Vegetable VALUES (?,?,?,SYSDATE())";
	public static String deleteVegetable = "DELETE FROM Vegetable WHERE name=?";
	public static String updateVegetable = "UPDATE Vegetable SET price=?, status=? WHERE name=?";
	public static String selectVegetable = 	"SELECT * FROM Vegetable WHERE name = ?";
	public static String selectAllVegetable = "SELECT * FROM Vegetable";
	
}
