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
public class CartVO {
	// 입력해야 하는 필드
	private String uid;
	private int pid;
	private int cnt;// 몇개 상품 구매인지 

	// 자동 입력되는 필드 
	private int id;
	private Date regdate;
	
	//cart에 필요한 정보 
	private String pname;
	private int pprice;
	private String catename;
	
	public CartVO(String uid, int pid, int cnt) {
		this.uid = uid;
		this.pid = pid;
		this.cnt = cnt;
	}
	
}










