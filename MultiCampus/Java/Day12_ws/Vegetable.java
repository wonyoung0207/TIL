package day12_ws;


public class Vegetable {
	private String name;
	private String price;
	private String condition;
	
	
	public Vegetable(String name, String price, String condition) {
		this.name = name;
		this.price = price;
		this.condition = condition;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	@Override
	public String toString() {
		return "fruit [name=" + name + ", price=" + price + ", condition=" + condition + "]";
	}
	

}
