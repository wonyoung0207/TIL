package com.multi.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;


@Controller
@RequestMapping("/login")
public class loginController {

	@Autowired
	CustBiz cbiz;
	
	@RequestMapping("")
	public String login(Model m) {
		
		m.addAttribute("center", "login/login");
		
		return "index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m,String id,String pwd, HttpSession session) {
		CustVO cust = null;
		String next = "";
		
		try {
			cust = cbiz.get(id);
			
			//입력된 user 정보 저장 
			m.addAttribute("userid", id);
			m.addAttribute("userpwd", pwd);
			
			if(cust == null) {//null 일경우 해당 ID가 없는것임 
				m.addAttribute("msg", "존재하지 않는 ID 입니다.");
				throw new Exception();
			}
			
			//해당 id 존재시 실행 
			// pwd가 다를경우 실행 
			if(!cust.getPwd().equals(pwd)) {
				m.addAttribute("msg", "Password 가 다릅니다. ");
				throw new Exception();
			}
			
			//로그인 성공시 실행
			session.setAttribute("user", cust);
			m.addAttribute("user", cust);
			next = "login/loginok";
			
		} catch (Exception e) {
			next = "login/login";
			e.printStackTrace();
		}
		
		m.addAttribute("center", next);
		
		return "index";
	}
	


}
