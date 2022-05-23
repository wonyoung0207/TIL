package com.multi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

	
	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("center","login/register");
		m.addAttribute("ad","login/ad");

		return "main";
		
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center","login/login");
		m.addAttribute("ad","login/ad");

		return "main";
		
	}
	
	@RequestMapping("/naver_loginOK")//회원가입 성공시 출력 
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
		
		m.addAttribute("center","login/registerLogin");
		m.addAttribute("ad","login/ad");
		m.addAttribute("user",user);
		
		return "main";
	}


	
	
}
