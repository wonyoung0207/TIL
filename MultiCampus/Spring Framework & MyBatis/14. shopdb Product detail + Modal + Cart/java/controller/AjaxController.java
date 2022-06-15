package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.CartBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CartVO;
import com.multi.vo.CustVO;

@RestController
public class AjaxController {

	@Autowired
	CustBiz cbiz;
	
	@Autowired
	CartBiz cabiz;
	
	@RequestMapping("/checkid")
	public String checkid(String id) {
		String result = "";
		CustVO cust = null;
		
		try {
			cust = cbiz.get(id);
			if(cust == null) {//id가 존재하지 않으면 0
				result = "0";
			}else {// id가 존재하면 1
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@RequestMapping("/addcart")
	public void addcart(String uid, int pid, int cnt) {
		try {
			cabiz.register(new CartVO(uid, pid, cnt));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
