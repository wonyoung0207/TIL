package com.multi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController//AJAX 통신에 적합한 애너테이션 
public class AJAXController {


	public JSONArray makeUser() {
		JSONArray ja = new JSONArray();
		//메개변수로 들어온 값을 저장되어있는 id와 pwd 를 비교 
		for(int i=1; i <= 3; i++) {
			JSONObject user = new JSONObject();//user
			user.put("id", "user" + i);
			user.put("pwd", "1234");
			
			ja.add(user);
		}
		return ja;
	}
	
	//로그인 할 수 있는지 확인 하는 ajax 
	@RequestMapping("/loginCheck")
	public Object loginCheck(String id, String pwd) {//데이터를 JSON 형식으로 내려준다. 
		System.out.println("login check 함수 시작 ");
		JSONArray ja = makeUser();
		JSONObject result = new JSONObject();//결과 전송할 변수 
		JSONObject check_user = new JSONObject();
		check_user.put("id", id);
		check_user.put("pwd", pwd);
		System.out.println("check_user 생성 ");
		
		
		for(int i=0; i<ja.size(); i++) {
			
			JSONObject user = (JSONObject) ja.get(i);
			if(check_user.get("id").equals(user.get("id"))) {
				if(check_user.get("pwd").equals(user.get("pwd"))) {
					System.out.println("로그인 성공");
					result.put("ok", "1");
					result.put("message", "로그인 성공");
					
					return result;//아이디가 존재. 로그인 성공 
				}
				result.put("ok", "0");
				result.put("message", "비밀번호가 다릅니다. ");
			
				System.out.println("비밀번호가 다릅니다. ");
				return result;
			}
		}
		
		result.put("ok", "0");
		result.put("message", "아이디가 존재하지 않습니다. ");
		System.out.println("아이디 존재 안함 ");
		return result;
		
	}
	
	@RequestMapping("/getnumber")
	public Object getdata() {//데이터를 JSON 형식으로 내려준다. 
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Random r = new Random();
		int random= 0;
		String txt = "";
		
		//JSON : [{},{},{}] 의 형태
		for(int i=0; i<8; i++) {
			txt += r.nextInt(10);
		}
		
		//현재 시간 
		String date = sdf.format(d);
		
		// JSNO Array에 객체 추가 
		ja.add(0, date);
		ja.add(1, txt);
		
//		ja.add(date);
//		ja.add(txt);
//		
//		System.out.println(ja.get(0));
//		System.out.println(ja.get(1));
		
		return ja;
	}
	
	@RequestMapping("/checkID")// 회원가입시 아이디 확인 
	public Object checkID(String id) {
		String result = "";
		if(id.equals("user1") || id.equals("user2") || id.equals("user3")) {
			//데이터 베이스에 아이디가 중복으로 있는 경우 
			result = "0";
		}else {
			result = "1";
		}
		return result;
	}
	

}
