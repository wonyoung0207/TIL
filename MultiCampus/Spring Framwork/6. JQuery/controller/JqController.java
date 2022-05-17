package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JqController {
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m,String id, String pwd) {
		System.out.println(id + " " + pwd);
		if(id.equals("qq") && pwd.equals("11")) {
			m.addAttribute("loginid",id);
			m.addAttribute("loginpwd",pwd);
			m.addAttribute("center","jq/loginok");
		}else {
			m.addAttribute("center","jq/loginfail");
		}
		
//		m.addAttribute("center","jq/center");
		m.addAttribute("left","jq/left");

		return "main";

	}
	
	
	@RequestMapping("/jq")
	public String jq(Model m) {
		m.addAttribute("center","jq/center");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	@RequestMapping("/jq01")
	public String jq01(Model m) {
		m.addAttribute("center","jq/jq01");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
	@RequestMapping("/jq02")
	public String jq02(Model m) {
		m.addAttribute("center","jq/jq02");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
	@RequestMapping("/jq03")
	public String jq03(Model m) {
		m.addAttribute("center","jq/jq03");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	@RequestMapping("/jq04")
	public String jq04(Model m) {
		m.addAttribute("center","jq/jq04");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
	@RequestMapping("/jq05")
	public String jq05(Model m) {
		m.addAttribute("center","jq/jq05");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
	@RequestMapping("/jq06")
	public String jq06(Model m) {
		m.addAttribute("center","jq/jq06");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	@RequestMapping("/jq07")
	public String jq07(Model m) {
		m.addAttribute("center","jq/jq07");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
	@RequestMapping("/jq08")
	public String jq08(Model m) {
		m.addAttribute("center","jq/jq08");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
	@RequestMapping("/jq09")
	public String jq09(Model m) {
		m.addAttribute("center","jq/jq09");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	@RequestMapping("/jq10")
	public String jq10(Model m) {
		m.addAttribute("center","jq/jq10");
		m.addAttribute("left","jq/left");
		return "main";

	}
	
	
}
