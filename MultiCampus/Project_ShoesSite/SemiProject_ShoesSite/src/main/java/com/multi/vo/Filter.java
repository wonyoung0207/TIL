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
public class Filter {
//필드

	// 필터 타입(성별 G, 카테고리 C, 가격 P, 색 O, 사이즈 S)
	private String type;
	
	// 필터 종류
	private String gender;
	private String cid;
		// param = price low high
	private int param1;
	private int param2;
	private String color;
	private String size;

	// split 사용을 위해 변수 타입은 String(price는 배열로 받지 않으므로 int)

	private int sortby; // 0 : 신상품순 / 1 : 높은가격순 / 2 : 낮은가격순 / 3 : 별점순
	
	
// 필드 끝
	

	// 필터 타입 데이터를 아이디 변환한 뒤 배열에 넣음
	public String[] getTypeArr() {
		return type == null ? new String[]{} : type.split("");
	}
	
	public String[] getCidArr() {
		return cid == null ? new String[]{} : cid.split(",");
	}
	
	public String[] getColorArr() {
		return color == null ? new String[]{} : color.split(",");
	}
	
	public String[] getSizeArr() {
		return size == null ? new String[]{} : size.split(",");
	}
	
}
