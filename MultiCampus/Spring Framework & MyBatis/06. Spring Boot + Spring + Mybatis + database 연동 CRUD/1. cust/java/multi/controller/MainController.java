package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
public class MainController {
	@Autowired
	CustBiz biz;
	
	@RequestMapping("/")
	public String main(Model m) {
		m.addAttribute("ccenter", "center");
		return "main";
	}
	
	@RequestMapping("/custadd")
	public String custadd(Model m) {
		
		m.addAttribute("ccenter", "custadd");
		
		return "main";
	}
	
	@RequestMapping("/custaddimpl")//Create
	public ModelAndView custaddimpl(ModelAndView mv,CustVO cust) {
		//자동으로 id, pwd, name이 CustVO 클래스에 매칭되어 저장된다. 
		
		String page = "custaddok";
		System.out.println(cust);
		
		try {
			biz.register(cust);
			mv.addObject("rcust",cust);
		} catch (Exception e) {
			page = "custaddfail";
			e.printStackTrace();
		}
		
		mv.setViewName(page);
		
		return mv;
	}
	
	@RequestMapping("/custselect")//ReadAll
	public ModelAndView custselect(ModelAndView mv) {
		List<CustVO> list = null;
		
		try {
			list = biz.getAll();
			mv.addObject("allcust",list);
		} catch (Exception e) {
			
		}
		
		mv.setViewName("custselect");
		return mv;
	}
	
	
	@RequestMapping("/custdetail")//Read
	public ModelAndView custdetail(ModelAndView mv, String id) {
		//thymeleaf의 href 를 통해 날아온 id를 매개변수가 자동으로 저장한다. 
		CustVO cust = null;
		
		try {
			cust = biz.get(id);
			mv.addObject("dcust",cust);
		} catch (Exception e) {
			
		}
		
		mv.setViewName("custdetail");
		return mv;
	}
	
	
	@RequestMapping("/custDelete")//Delete
	public String custdelete(String id) {
		try {
			biz.remove(id);
		} catch (Exception e) {
		}
		
		return "redirect:custselect";//custselect 를 새롭게 다시 요청한다. 
	}
	
	@RequestMapping("/custUpdate")//Read
	public ModelAndView custUpdate(ModelAndView mv,String id) {
		CustVO cust = null;
		try {
			cust = biz.get(id);
			mv.addObject("ucust",cust);
		} catch (Exception e) {
		}
		mv.setViewName("custUpdate");
		
		return mv; 
	}
	
	@RequestMapping("/custUpdateimpl")//Update
	public String custUpdateimpl(CustVO cust) {
		try {
			biz.modify(cust);
			
		} catch (Exception e) {
		}
		
		return "redirect:custdetail?id=" + cust.getId(); 
	}
	
}
