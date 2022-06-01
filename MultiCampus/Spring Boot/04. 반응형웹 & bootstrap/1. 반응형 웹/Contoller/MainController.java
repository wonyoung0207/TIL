package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
//	@RequestMapping("/")
//	public ModelAndView main(ModelAndView mv) {
//		mv.setViewName("main");
//		mv.addObject("center", "center");
//
//		return mv;
//
//	}
//	
//	
//	@RequestMapping("/media")
//	public ModelAndView media(ModelAndView mv) {
//		mv.setViewName("media");
//
//		return mv;
//
//	}
//	
//	
//	@RequestMapping("/html5")
//	public ModelAndView html5(ModelAndView mv) {
//		mv.setViewName("main");
//		mv.addObject("center", "html5");
//
//		return mv;
//
//	}
//	
//	@RequestMapping("/js")
//	public ModelAndView js(ModelAndView mv) {
//		mv.setViewName("main");
//		mv.addObject("center", "js");
//
//		return mv;
//
//	}
//	
//	
//	@RequestMapping("/css3")
//	public ModelAndView css3(ModelAndView mv) {
//		mv.setViewName("main");
//		mv.addObject("center", "css3");
//
//		return mv;
//
//	}
	
	
	// ******************* thymeleaf **********************
	// 앞에서 한것과 다른점은 jsp파일을 사용하지 않는다는 것이다. 
	
	@RequestMapping("/")
	public String main(Model m) {
		//resource - templates 파일의 html파일에서 main을 호출한다. 
		m.addAttribute("center","center");
		return "main";

	}

}
