package com.vo;

import java.util.Date;

public class ProductVO {
	private int id;
	private String name;
	private int price;
	private Date date;	
	private double rate;
	
	
	public ProductVO() {
		
	}
	public ProductVO(String name, int price, double rate) {
		super();
		this.name = name;
		this.price = price;
		this.rate = rate;
	}
	
	public ProductVO(int id, String name, int price, double rate) {//Update�� �ʿ��� ��ü 
		this.id = id;
		this.name = name;
		this.price = price;
		this.rate = rate;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", rate=" + rate
				+ "]";
	}
	
	
}
