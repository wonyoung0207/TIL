package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class iPhoneController {
	
	@RequestMapping("/iPhone")
	public String iPhone(Model m) {
		m.addAttribute("center","iPhone/center");
		m.addAttribute("headList","iPhone/headList");
		m.addAttribute("ad","iPhone/ad");
		
		return "main";
		
	}
	

}
