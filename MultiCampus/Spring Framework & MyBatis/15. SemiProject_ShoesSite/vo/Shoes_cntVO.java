package com.multi.vo;

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
public class Shoes_cntVO {
	
	//컬럼명
	private int id;
	private int pid;
	private int size;
	private int cnt;
	
	//조인했을때 추가되는 컬럼명
	private String productname;

	//INSERT 생성자
	public Shoes_cntVO(int pid, int size, int cnt) {
		this.pid = pid;
		this.size = size;
		this.cnt = cnt;
	}

	// UPDATE 생성자
	public Shoes_cntVO(int id, int pid, int size, int cnt) {
		this.id = id;
		this.pid = pid;
		this.size = size;
		this.cnt = cnt;
	}
	
	
}
