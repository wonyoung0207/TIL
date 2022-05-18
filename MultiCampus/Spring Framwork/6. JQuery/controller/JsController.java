package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JsController {
	
	@RequestMapping("/js")
	public String js(Model m) {
		m.addAttribute("center","js/center");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	@RequestMapping("/js01")
	public String js01(Model m) {
		m.addAttribute("center","js/js01");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
	@RequestMapping("/js02")
	public String js02(Model m) {
		m.addAttribute("center","js/js02");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
	@RequestMapping("/js03")
	public String js03(Model m) {
		m.addAttribute("center","js/js03");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	@RequestMapping("/js04")
	public String js04(Model m) {
		m.addAttribute("center","js/js04");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
	@RequestMapping("/js05")
	public String js05(Model m) {
		m.addAttribute("center","js/js05");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
	@RequestMapping("/js06")
	public String js06(Model m) {
		m.addAttribute("center","js/js06");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	@RequestMapping("/js07")
	public String js07(Model m) {
		m.addAttribute("center","js/js07");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
	@RequestMapping("/js08")
	public String js08(Model m) {
		m.addAttribute("center","js/js08");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
	@RequestMapping("/js09")
	public String js09(Model m) {
		m.addAttribute("center","js/js09");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	@RequestMapping("/js10")
	public String js10(Model m) {
		m.addAttribute("center","js/js10");
		m.addAttribute("left","js/left");
		return "main";

	}
	
	
}
