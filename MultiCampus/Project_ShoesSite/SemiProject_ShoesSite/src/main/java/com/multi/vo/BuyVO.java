package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyVO {
	
	private int id;
	private String uid;
	private String recipient;
	private String recipient_addr;
	private String recipient_addr_detail;
	private String recipient_phonenumber;
	private String request;
	private int price;
	private Date regdate;
	//안원영 추가 
	private int pid;
	private int size;
	private int cnt;
	private int price_one;
	private String name;
	private String imgname1;
		
	// INSERT 생성자(id, regdate 제외)
	public BuyVO(String uid, String recipient, String recipient_addr,String recipient_addr_detail, String recipient_phonenumber, String request,
			int price) {
		this.uid = uid;
		this.recipient = recipient;
		this.recipient_addr = recipient_addr;
		this.recipient_phonenumber = recipient_phonenumber;
		this.request = request;
		this.price = price;
	}

	// UPDATE 생성자(uid, price, regdate 제외)
	public BuyVO(int id, String recipient, String recipient_addr,String recipient_addr_detail, String recipient_phonenumber, String request) {
		this.id = id;
		this.recipient = recipient;
		this.recipient_addr = recipient_addr;
		this.recipient_phonenumber = recipient_phonenumber;
		this.request = request;
	}
	
	// 안원영 추가 -> 추가된 필드 이전에 사용될 생성자. 
	public BuyVO(int id, String uid, String recipient, String recipient_addr, String recipient_addr_detail ,String recipient_phonenumber,
			String request, int price, Date regdate) {
		super();
		this.id = id;
		this.uid = uid;
		this.recipient = recipient;
		this.recipient_addr = recipient_addr;
		this.recipient_addr_detail = recipient_addr_detail;
		this.recipient_phonenumber = recipient_phonenumber;
		this.request = request;
		this.price = price;
		this.regdate = regdate;
	}

	
//	 //만약 팀원중 필드 추가한게 있다면 다음 생성자 넣어줘야 함 => Buymapper.xml 의 selectUserBuy 에서 사용할 생성자 
	
//	public BuyVO(int id, String uid, String recipient, String recipient_addr, String recipient_addr_detail,
//			String recipient_phonenumber, String request, int price, Date regdate, int pid, int size, int cnt,
//			int price_one, String name, String imgname1) {
//		super();
//		this.id = id;
//		this.uid = uid;
//		this.recipient = recipient;
//		this.recipient_addr = recipient_addr;
//		this.recipient_addr_detail = recipient_addr_detail;
//		this.recipient_phonenumber = recipient_phonenumber;
//		this.request = request;
//		this.price = price;
//		this.regdate = regdate;
//		this.pid = pid;
//		this.size = size;
//		this.cnt = cnt;
//		this.price_one = price_one;
//		this.name = name;
//		this.imgname1 = imgname1;
//	}



	
}
