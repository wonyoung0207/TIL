package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home(Model m) {
		m.addAttribute("center","home");
		
		return "main";

	}
	
	@RequestMapping("/home1")
	public String home1(Model m) {
		m.addAttribute("center", "home");
		m.addAttribute("scenter", "home/home1");
		
		return "main";
	}

	
	@RequestMapping("/home2")
	public String home2(Model m) {
		m.addAttribute("center", "home");
		m.addAttribute("scenter", "home/home2");
		
		return "main";
		
		
	}
	
	@RequestMapping("/home3")
	public String home3(Model m) {
		m.addAttribute("center", "home");
		m.addAttribute("scenter", "home/home3");
		
		return "main";
		
		
	}


	
}
