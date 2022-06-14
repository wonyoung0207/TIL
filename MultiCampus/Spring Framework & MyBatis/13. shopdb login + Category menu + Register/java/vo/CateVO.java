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
public class CateVO {
	private int id;
	private String name;
	private int pid;//상위 id를 가리킨다. -> self 조인 
	
	
	public CateVO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	
}





