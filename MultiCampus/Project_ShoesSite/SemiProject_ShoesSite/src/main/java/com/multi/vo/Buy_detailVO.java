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
public class Buy_detailVO {
	private int id;
	private int oid;
	private int pid;
	private int size;
	private int cnt;
	//06.24 안원영 추가 - buy_detail 페이지 
	private String name;
	private int price;
	private String imgname1;
	private String color;
	private String gender;

	public Buy_detailVO(int oid, int pid, int size, int cnt) {
		this.oid = oid;
		this.pid = pid;
		this.size = size;
		this.cnt = cnt;
	}
	// 06.24 안원영 추가 - 기본생성자 
	public Buy_detailVO(int id, int oid, int pid, int size, int cnt) {
		super();
		this.id = id;
		this.oid = oid;
		this.pid = pid;
		this.size = size;
		this.cnt = cnt;
	}

	//		// 06.24  팀원 다른 필드 추가시 삽입할 것 
	//		public Buy_detailVO(int id, int oid, int pid, int size, int cnt, String name, int price, String imgname1,
	//				String color, String gender) {
	//			super();
	//			this.id = id;
	//			this.oid = oid;
	//			this.pid = pid;
	//			this.size = size;
	//			this.cnt = cnt;
	//			this.name = name;
	//			this.price = price;
	//			this.imgname1 = imgname1;
	//			this.color = color;
	//			this.gender = gender;
	//		}
	//	


}
