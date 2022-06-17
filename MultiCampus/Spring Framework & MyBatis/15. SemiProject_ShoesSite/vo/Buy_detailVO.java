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
public class Buy_detailVO {
	private int id;
	private int oid;
	private int pid;
	private int size;
	private int cnt;
	public Buy_detailVO(int oid, int pid, int size, int cnt) {
		this.oid = oid;
		this.pid = pid;
		this.size = size;
		this.cnt = cnt;
	}

}
