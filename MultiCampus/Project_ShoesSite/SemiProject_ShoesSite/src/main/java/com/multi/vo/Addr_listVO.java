package com.multi.vo;

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
public class Addr_listVO {
	private int id;
	private String uid;
	private String addr;
	private String addr_detail;
	private int zip;
	private String cname;
	private String telphone;
	public Addr_listVO(String uid, String addr, String addr_detail, int zip) {
		this.uid = uid;
		this.addr = addr;
		this.addr_detail = addr_detail;
		this.zip = zip;
	}
	public Addr_listVO(int id, String uid, String addr, String addr_detail, int zip) {
		this.id = id;
		this.uid = uid;
		this.addr = addr;
		this.addr_detail = addr_detail;
		this.zip = zip;
	}
}

