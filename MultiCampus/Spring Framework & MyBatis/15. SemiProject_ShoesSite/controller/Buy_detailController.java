package com.multi.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("buy_detail")
public class Buy_detailController {
	
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	


}
