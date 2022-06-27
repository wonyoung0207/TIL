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
public class CateVO {
	private int id;
	private String name;
	private int tid;
	// CATE INSERT ,UPDATE
	public CateVO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	// CATE DELETE
	public CateVO(int id) {
		this.id = id;
	}
	
	
}
