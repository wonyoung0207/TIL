package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main(Model m) {
		//resource - templates 파일의 html파일에서 main을 호출한다. 
		m.addAttribute("center","center");
		return "main";

	}
	

	
	@RequestMapping("/jq")
	public String project(Model m) {
		m.addAttribute("center","jq");
		return "main";

	}
	
	@RequestMapping("/ajax")
	public String contact(Model m) {
		m.addAttribute("center","ajax");
		return "main";

	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center","login");
		return "main";

	}


}
