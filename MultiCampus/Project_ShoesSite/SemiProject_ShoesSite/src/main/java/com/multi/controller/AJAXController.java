package com.multi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.CartBiz;
import com.multi.biz.CustBiz;
import com.multi.biz.ProductBiz;
import com.multi.biz.Shoes_cntBiz;
import com.multi.vo.CartVO;
import com.multi.vo.CustVO;
import com.multi.vo.Shoes_cntVO;

@RestController
public class AJAXController {

	@Autowired
	CustBiz cbiz;
	
	@Autowired
	CartBiz ctbiz;
	
	@Autowired
	Shoes_cntBiz sbiz;

	@Autowired
	ProductBiz pbiz;
	
	
	@RequestMapping("gettotal")
	public int gettotal(
			//@RequestParam(value="price[]") List<Integer> price,
			//@RequestParam(value="count[]") List<Integer> count){
			int price, int count) {
		int result;	
		result = price *count;

		return result;
	}

	
	@RequestMapping("/checkid")
	public String checkid(String id) {
		CustVO cust = null;
		List<CustVO> list = null;
		String result = "0";
		
		try {
			list = cbiz.get();
			for(CustVO c : list) {
				if(c.getId().equals(id) || id.equals("")) {//id 같은것이 있다면 1을 리턴 -> 1이면 사용불가 
					result = "1";
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	@RequestMapping("/addcart")
	public String addcart(String uid, int pid, Integer size, int count){
		
		// 폼에서 받은 수량과 재고 비교하기
		Shoes_cntVO checksize = new Shoes_cntVO(pid, size);
		int checksizecnt = 0;
		String msg = null;
		
		try {
			checksizecnt = sbiz.checkcnt(checksize);
			if (checksizecnt<count) { // 재고보다 폼에서 받은 수량이 클때
				throw new Exception();  // 예외 발생
			}else {  // 수량이 재고보다 작거나 같을 때
				// 카트에 이미 상품이 있는지 확인 
				CartVO check = new CartVO(uid, pid, size);
				int result = 0;
				result = ctbiz.checkcart(check);
				
				// 상품이 없으면 insert
				if(result==0) {
					CartVO c = new CartVO(count, uid, pid, size);
					ctbiz.register(c);
					msg = "장바구니에 넣었습니다. 장바구니를 확인하시겠습니까?";
				// 상품이 있으면 장바구니 페이지로 이동
				}else {
					msg = "상품이 이미 장바구니에 있습니다. 장바구니를 확인하시겠습니까?";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "상품의 재고가 선택한 수량보다 많습니다.";
		}
		return msg;
	}
	// 안원영 추가 
	@RequestMapping("/checkpwd")//mypage -> 비밀번호 수정 에서 사용 
	public String checkpwd(String write_pwd,HttpSession session) {
		CustVO cust = null;
		List<CustVO> list = null;
		cust = (CustVO) session.getAttribute("user");//session에 저장되어있는 로그인된 아이디 
		String pwd = cust.getPwd();//session에 있는 pwd 가져옴
		String result = "0";
		
		try {
			if(pwd.equals(write_pwd)) {//입력된 pwd와 같은지 비교 - > 같다면 실행 
				result="1";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	

}
