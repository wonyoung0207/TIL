package com.multi.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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
public class ProductVO {
	// insert에 필요한 필드
	private String name;
	private int price;
	private int cid;
	private String imgname;

	//조회에 필요한 필드 
	private int id;
	private Date regdate;
	private String catename;
	private String catemainname;
	private MultipartFile mf;//화면에서 서버로 파일을 보낼때 사용되는 변수 
	
	public ProductVO(String name, int price, int cid, String imgname) {
		super();
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.imgname = imgname;
	}

	public ProductVO(String name, int price, int cid, MultipartFile mf) {
		super();
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.mf = mf;
	}
	
	
	
	
}






