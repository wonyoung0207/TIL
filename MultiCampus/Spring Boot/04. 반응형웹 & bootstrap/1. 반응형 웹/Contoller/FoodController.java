package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
	
	@RequestMapping("/food")
	public String food(Model m) {
		m.addAttribute("center", "food");
		
		
		return "main";
	}
	
	@RequestMapping("/food1")
	public String food1(Model m) {
		m.addAttribute("center", "food");
		
		m.addAttribute("scenter", "food/food1");
		
		
		return "main";
	}
	
	@RequestMapping("/food2")
	public String food2(Model m) {
		m.addAttribute("center", "food");
		
		m.addAttribute("scenter", "food/food2");
		
		
		return "main";
	}
}
