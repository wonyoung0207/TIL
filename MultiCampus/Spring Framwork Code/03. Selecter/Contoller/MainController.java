package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");

		return mv;

	}
	
	@RequestMapping("/main_semantic")
	public ModelAndView main_semantic(ModelAndView mv) {
		mv.setViewName("main_semantic");

		return mv;

	}
	
	@RequestMapping("/ws")
	public ModelAndView ws(ModelAndView mv) {
		mv.setViewName("ws");

		return mv;

	}
	
	@RequestMapping("/ws_buy")
	public ModelAndView ws_buy(ModelAndView mv) {
		mv.setViewName("ws_buy");

		return mv;

	}

	@RequestMapping("/div_span")
	public ModelAndView div_span(ModelAndView mv) {
		mv.setViewName("div_span");

		return mv;

	}
	
	@RequestMapping("/attribute_select")
	public ModelAndView attribute_select(ModelAndView mv) {
		mv.setViewName("attribute_select");

		return mv;

	}
	
	@RequestMapping("/child_select")
	public ModelAndView child_select(ModelAndView mv) {
		mv.setViewName("child_select");

		return mv;

	}
	
	@RequestMapping("/box_select")
	public ModelAndView box_select(ModelAndView mv) {
		mv.setViewName("box_select");

		return mv;

	}
	
	
	@RequestMapping("/button_select")
	public ModelAndView button_select(ModelAndView mv) {
		mv.setViewName("button_select");

		return mv;

	}
	
	@RequestMapping("/position_select")
	public ModelAndView position_select(ModelAndView mv) {
		mv.setViewName("position_select");
		
		return mv;
		
	}
	

}
