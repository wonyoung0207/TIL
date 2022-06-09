package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.MainBiz;

@Controller
public class MainController {
	
	@Autowired
	MainBiz mbiz;
	
	
	@RequestMapping("/")
	public String main(Model m) {
		int count = 0;
		
		try {
			count = mbiz.getCustCnt();
//			System.out.println("CustCnt : " + count);
			m.addAttribute("custcnt", count);
			
			count = mbiz.getProductCnt();
//			System.out.println("ProductCnt : " + count);
			m.addAttribute("productcnt", count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "index";
	}
	


}
