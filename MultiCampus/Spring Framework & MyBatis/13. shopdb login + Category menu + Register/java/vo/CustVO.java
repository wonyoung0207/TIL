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
	private String name;
	private String pwd;
	private String addr;
	private Date regdate;
	
	public CustVO(String id, String name, String pwd, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.addr = addr;
	}
	
	
	
	
}






