package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class iPadController {
	
	@RequestMapping("/iPad")
	public String iPad(Model m) {
		m.addAttribute("center","iPad/center");
		m.addAttribute("headList","iPad/headList");
		m.addAttribute("ad","iPad/ad");
		
		return "main";
		
	}
	

}
