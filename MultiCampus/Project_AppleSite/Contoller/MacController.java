package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MacController {
	
	@RequestMapping("/Mac")
	public String Mac(Model m) {
		m.addAttribute("center","Mac/center");
		m.addAttribute("headList","Mac/headList");
		m.addAttribute("ad","Mac/ad");
		
		return "main";
		
	}
	

}
