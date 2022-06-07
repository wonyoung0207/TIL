package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {
	private int id;
	private String name;
	private int price;
	private Date regdate;
	private double rate;
	
	public ProductVO(String name, int price, double rate) {
		this.name = name;
		this.price = price;
		this.rate = rate;
	}
	
}
