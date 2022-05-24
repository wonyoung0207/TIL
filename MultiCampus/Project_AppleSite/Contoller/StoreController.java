package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController {
	
	@RequestMapping("/Store")
	public String Store(Model m) {
		m.addAttribute("center","Store/center");
		m.addAttribute("headList","Store/headList");
		m.addAttribute("ad","Store/ad");
		
		return "main";
		
	}
	

}
