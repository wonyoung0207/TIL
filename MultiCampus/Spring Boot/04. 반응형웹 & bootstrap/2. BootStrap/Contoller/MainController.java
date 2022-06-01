package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	// ******************* thymeleaf **********************
	// 앞에서 한것과 다른점은 jsp파일을 사용하지 않는다는 것이다. 
	
	@RequestMapping("/")
	public String main(Model m) {
		//resource - templates 파일의 html파일에서 main을 호출한다. 
		m.addAttribute("center","center");
		return "main";

	}
	
	
	@RequestMapping("/about")
	public String about(Model m) {
		m.addAttribute("center","about");
		return "main";

	}
	
	@RequestMapping("project")
	public String project(Model m) {
		m.addAttribute("center","project");
		return "main";

	}
	
	@RequestMapping("/contact")
	public String contact(Model m) {
		m.addAttribute("center","contact");
		return "main";

	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center","login");
		return "main";

	}

}
