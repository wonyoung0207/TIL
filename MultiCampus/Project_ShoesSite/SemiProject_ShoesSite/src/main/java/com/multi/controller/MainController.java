package com.multi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;


@Controller
public class MainController {
	
	@Autowired
	CustBiz cbiz;
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("center", "/register");
		
		
		return "index";
	}
	
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model m, CustVO cust, HttpSession session) {
		System.out.println("cust : " + cust);
		
		try {
			cbiz.register(cust);
			CustVO c = cbiz.get(cust.getId());
			session.setAttribute("user", c);
			System.out.println("가입완료 : " + c);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	
	@RequestMapping("/logout")
	public String logout(Model m,HttpSession session) {
		
		session.removeAttribute("user");
		
		return "index";
	}
	@RequestMapping("/about_us")
	public String about_us(Model m) {
		m.addAttribute("center","about_us");
		m.addAttribute("footer","footer");
		m.addAttribute("header","header");
		return "index";
	}

	@RequestMapping("/contact_us")
	public String contact(Model m) {
		m.addAttribute("center","contact_us");
		m.addAttribute("footer","footer");
		m.addAttribute("header","header");
		return "index";
	}

}