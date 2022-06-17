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
	private String recipient_phonenumber;
	private String request;
	private int price;
	private Date regdate;
	
	// INSERT 생성자(id, regdate 제외)
	public BuyVO(String uid, String recipient, String recipient_addr, String recipient_phonenumber, String request,
			int price) {
		this.uid = uid;
		this.recipient = recipient;
		this.recipient_addr = recipient_addr;
		this.recipient_phonenumber = recipient_phonenumber;
		this.request = request;
		this.price = price;
	}

	// UPDATE 생성자(uid, price, regdate 제외)
	public BuyVO(int id, String recipient, String recipient_addr, String recipient_phonenumber, String request) {
		this.id = id;
		this.recipient = recipient;
		this.recipient_addr = recipient_addr;
		this.recipient_phonenumber = recipient_phonenumber;
		this.request = request;
	}
	
	
}
