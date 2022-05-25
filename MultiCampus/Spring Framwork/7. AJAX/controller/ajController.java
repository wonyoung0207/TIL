package com.multi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ajController {
	
	@RequestMapping("/ajax")
	public String aj(Model m) {
		m.addAttribute("center","ajax/center");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/register_formimpl")
	public String register_formimpl(Model m,String id,String pwd) {
		String result = "";
		System.out.println(id + " " + pwd);
		
		m.addAttribute("center","ajax/registerOK");
		m.addAttribute("left","ajax/left");
		result = id + " 님 로그인에 성공하셨습니다. ";
		
		m.addAttribute("user",result);
		
		return "main";
	}

	
	@RequestMapping("/aj01")
	public String aj01(Model m) {
		m.addAttribute("center","ajax/aj01");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	

	
	@RequestMapping("/aj02")
	public String aj02(Model m) {
		m.addAttribute("center","ajax/aj02");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	
	@RequestMapping("/aj03")
	public String aj03(Model m) {
		m.addAttribute("center","ajax/aj03");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/naver_loginOK")
	public String naver_loginOK(Model m,
			String contry, String telphone,String id,String pwd,String name,
			String email, String gender,String birthday) {
		//매개변수 순서 상관없고, 보내준 변수 이름을 맞춰줘야함 
		String result = "";
		String phoneNum = contry+telphone;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("오늘은 yyyy-MM-dd  hh:mm:ss");
		//sdf.format(birthday)
		
		User user = new User(id, pwd, name, birthday , gender, email, phoneNum);
		
		m.addAttribute("center","ajax/registerLogin");
		m.addAttribute("left","ajax/left");
		m.addAttribute("user",user);
		
		return "main";
	}
	
	@RequestMapping("/aj04")
	public String aj04(Model m) {
		m.addAttribute("center","ajax/aj04");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	
	@RequestMapping("/aj05")
	public String aj05(Model m) {
		m.addAttribute("center","ajax/aj05");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	
	@RequestMapping("/aj06")
	public String aj06(Model m) {
		m.addAttribute("center","ajax/aj06");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/aj07")
	public String aj07(Model m) {
		m.addAttribute("center","ajax/aj07");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	
	@RequestMapping("/aj08")
	public String aj08(Model m) {
		m.addAttribute("center","ajax/aj08");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	
	@RequestMapping("/aj09")
	public String aj09(Model m) {
		m.addAttribute("center","ajax/aj09");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/aj10")
	public String aj10(Model m) {
		m.addAttribute("center","ajax/aj10");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/aj11")
	public String aj11(Model m) {
		m.addAttribute("center","ajax/aj11");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/aj12")
	public String aj12(Model m) {
		m.addAttribute("center","ajax/aj12");
		m.addAttribute("left","ajax/left");
		return "main";

	}
	
	@RequestMapping("/aj13")
	public String aj13(Model m) {
		m.addAttribute("center","ajax/aj13");
		m.addAttribute("left","ajax/left");
		return "main";

	}

	
	
}
