package com.multi.vo;

import java.util.List;

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
public class CartVO  {
	private int id;
	private int count;
	private String uid;
	private int pid;
	private int size;
	private String pname;
	private String pprice;
	private String pimg1;
	private int total;
	private int oid;
	// 추가 필드 서예린 06/23
	private int result;
	private List<CartVO> cartlist;
	
	// cart insert(추가 생성자)
	public CartVO(int count, String uid, int pid, int size) {
		super();
		this.count = count;
		this.uid = uid;
		this.pid = pid;
		this.size = size;
	}

	// cart update
	public CartVO(int id, int count) {
		super();
		this.id = id;
		this.count = count;
	}
	
	
	// cart delete
	public CartVO(int id) {
		this.id = id;
	}

	// cartcheck(추가 생성자 6/23)
	
	public CartVO(String uid, int pid, int size) {
		super();
		this.uid = uid;
		this.pid = pid;
		this.size = size;
	}
	
	
}