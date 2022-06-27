package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustVO {
	private String id;
	private String pwd;
	private String name;
	private Date regdate;
	private String telphone;
	public CustVO(String id, String pwd, String name, String telphone) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.telphone = telphone;
	}
	
}
