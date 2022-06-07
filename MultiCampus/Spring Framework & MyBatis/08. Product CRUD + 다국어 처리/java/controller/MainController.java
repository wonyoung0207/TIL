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
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		
		m.addAttribute("center", "login");
		
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();//서버에서 session을 제거한다. 
		}
		
		return "main";
	}
	
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		String next = "";
		CustVO cust = null;
		
		try {
			//데이터 베이스에 있는 정보와 비교 
			// id 값이 없으면 null 이 출력된다. 
			cust = cbiz.get(id);
//			System.out.println(cust);
			if(cust != null) {
				if(cust.getPwd().equals(pwd)) {
					session.setAttribute("cust", cust);
					next = "loginok";
					m.addAttribute("cust",cust);
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
//			e.printStackTrace();
			next="loginfail";
		}

		m.addAttribute("center", next);
		
		return "main";
	}
	
	

}
