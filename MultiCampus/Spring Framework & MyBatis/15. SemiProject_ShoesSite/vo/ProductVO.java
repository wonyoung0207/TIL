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
	private String imgname1;
	private String imgname2;
	private int cid;
	private String color;
	private String gender;
	
	// Join해서 가져올 데이터
	private int cnt;
	private int size;
	private String review_uid;
	private String review_text;
	
	
	public ProductVO(String name, int price ,String imgname1, String imgname2, int cid, String color, String gender) {
		super();
		this.name = name;
		this.price = price;
		this.imgname1 = imgname1;
		this.imgname2 = imgname2;
		this.cid = cid;
		this.color = color;
		this.gender = gender;
	}


	public ProductVO(int id, String name, int price, Date regdate, String imgname1, String imgname2, int cid,
			String color, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.regdate = regdate;
		this.imgname1 = imgname1;
		this.imgname2 = imgname2;
		this.cid = cid;
		this.color = color;
		this.gender = gender;
	}


	public ProductVO(int id, String name, int price, String imgname1, String imgname2, int cid, String color,
			String gender) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgname1 = imgname1;
		this.imgname2 = imgname2;
		this.cid = cid;
		this.color = color;
		this.gender = gender;
	}
	
	
	
	

}
