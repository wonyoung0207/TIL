package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WatchController {
	
	@RequestMapping("/watch")
	public String watch(Model m) {
		m.addAttribute("center","watch/center");
		m.addAttribute("headList","watch/headList");
		m.addAttribute("ad","watch/ad");
		
		return "main";
		
	}
	

}
