package com.multi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@Controller
public class MainController {
	
	@Autowired
	CustBiz cbiz;
	
	@Autowired
	CateBiz cabiz;
	
	@RequestMapping("/")
	public String main(Model m) {
		List<CateVO> list = null;
		
		try {
			list = cabiz.getmain();
			m.addAttribute("catelist", list);
			m.addAttribute("leftcatelist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center", "/login");
		
		return "main";
	}
	
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		CustVO cust = null;
		String msg = "";
		
		try {
			cust = cbiz.get(id);
			if(cust == null) {// id가 없을 경우
				msg = "ID is not exist";
				throw new Exception();
			}
			
			if(!cust.getPwd().equals(pwd)) {// 비밀번호가 다를 경우
				msg = "Password is not Same";
				throw new Exception();
			}
			
			session.setAttribute("logincust", cust);
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("msg : "+ msg);
			return "redirect:/login?msg=" + msg;
		}
		
		m.addAttribute("cust",cust);
		m.addAttribute("center", "/loginok");
		
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		
		if(session != null) {
			session.invalidate();//서버에서 session을 제거한다. 
		}
		
		m.addAttribute("center", "/login");
		
		return "main";
	}
	
	
	
	
	@RequestMapping("/register")
	public String register(Model m) {
		
		m.addAttribute("center", "/register");
		
		
		return "main";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model m, CustVO obj,HttpSession session) {
		CustVO cust = null;
		
		try {
			cust = cbiz.get(obj.getId());
			if(cust != null) {//오류 -> id 중복 
				m.addAttribute("msg","ID 중복 오류 입니다. ");
				m.addAttribute("center", "/register");
			}
			cbiz.register(obj);
			session.setAttribute("logincust", obj);
			System.out.println("가입완료 : " + obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@ModelAttribute("catelist")
	public List<CateVO> makemenu(){
		List<CateVO> catelist = null;
		
		try {
			catelist = cabiz.getmain();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return catelist;
		
	}
	
	@RequestMapping("/getcate")
	public String getcate(Model m,String id) {
		List<CateVO> list = null;
		
		try {
			list = cabiz.getsubcate(id);
			m.addAttribute("leftcatelist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "main";
	}
	
	
}
