package com.multi.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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
public class ReviewVO {
	private int id;
	private int pid;
	private int star;
	private String text;
	private Date regdate;
	private String filename;
	private String uid;
	
	private MultipartFile mf;
	
	//star 평균
	private int staravg;
	
	// review 카운트
	private int count;
	
	// 안원영 추가 mypage ( 나의 상품 리뷰 쓰기 ) 
	private String name;//product name
	private int price;// product price
	private String imgname1; //product imgname1
	private String color; //product color
		
		
	//insert
	public ReviewVO(int id, int pid, int star, String text, Date regdate, String uid) {
		this.id = id;
		this.pid = pid;
		this.star = star;
		this.text = text;
		this.regdate = regdate;
		this.uid = uid;
	}
	//delete
	public ReviewVO(int id) {
		this.id = id;
	}
	//update
	public ReviewVO(int id, int star, String text) {
		this.id = id;
		this.star = star;
		this.text = text;
	}
	//안원영 추가 -> review insert 시 사용 
	public ReviewVO(int pid, int star, String text, String filename, String uid) {
		super();
		this.pid = pid;
		this.star = star;
		this.text = text;
		this.filename = filename;
		this.uid = uid;
	}
	
	
	// 안원영 추가 => mypage ( 나의 상품 리뷰 쓰기 ) 
	public ReviewVO(int id, int pid, int star, String text, Date regdate, String filename, String uid, String name,
			int price, String imgname1, String color) {//매개변수 11개 
		super();
		this.id = id;
		this.pid = pid;
		this.star = star;
		this.text = text;
		this.regdate = regdate;
		this.filename = filename;
		this.uid = uid;
		this.name = name;
		this.price = price;
		this.imgname1 = imgname1;
		this.color = color;
	}

		

	

}