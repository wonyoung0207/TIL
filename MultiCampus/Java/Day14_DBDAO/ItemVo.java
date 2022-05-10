package day14_DB_DAO;
//item 데이터베이스 저장 객체
// id, name, products
public class ItemVo {
	private int id;
	private String name;
	private double price;
	
	public ItemVo() {
		
	}
	
	public ItemVo(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ItemVo [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}
