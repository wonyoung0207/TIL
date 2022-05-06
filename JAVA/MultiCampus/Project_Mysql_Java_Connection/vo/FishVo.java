package vo;

import java.util.Date;

public class FishVo {
	private String name;
	private double price;
	private String status;
	private Date date;

	
	public FishVo() {
	}
	public FishVo(String name, double price, String status) {
		this.name = name;
		this.price = price;
		this.status = status;
	}
	public FishVo(String name, double price, String status, Date date) {
		this.name = name;
		this.price = price;
		this.status = status;
		this.date = date;
		
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "FishVo [name=" + name + ", price=" + price + ", status=" + status + ", date=" + date + "]";
	}
	
}
