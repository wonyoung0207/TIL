package com.multi.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController//AJAX 통신에 적합한 애너테이션 
public class AJAXController {
	
	
	@RequestMapping("/search")
	public Object search(String s) {//aj02에서data 변수 s넘겨준 데이터를 받는다. 
		String data ="";
		if(s.equals("a")) {
			data = "a 데이터가 들어왔어요";
			
		}else if(s.equals("b")) {
			data = "b 데이터가 들어왔어요";
		}else {
			data = "a,b 가 아닌 데이터가 들어왔어요. ";
		}
		
		return data;
		
	}
	
//	@RequestMapping("/gettime1")
//	//@ResponseBody//template에서 찾지않고 바로 ajax로 요청 
//	public void gettime1(HttpServletResponse rep) {//매개변수를 통해서 ajax에 데이터를 전달한다. 
//		PrintWriter out = null;
//		try {
//			rep.setCharacterEncoding("UTF-8");//서버 한글 나올수 있도록 설정하는 것 
//			out = rep.getWriter();
//			Date date = new Date();
//			SimpleDateFormat format1;
//			
//			format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			
//			out.print("서버 시간" + format1.format(date));
//			out.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@RequestMapping("/gettime")
	public Object gettime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return "서버시간 " + sdf.format(d);
	}
	
	
	@RequestMapping("/checkid")
	public Object checkid(String id) {
		String result = "";
		if(id.equals("aaaa") || id.equals("bbbb") || id.equals("cccc")) {
			//데이터 베이스에 아이디가 중복으로 있는 경우 
			result = "0";
		}else {
			result = "1";
		}
		return result;
	}
	
	
	@RequestMapping("/checkID")
	public Object checkID(String id) {
		String result = "";
		if(id.equals("aaaaa") || id.equals("bbbbb") || id.equals("ccccc")) {
			//데이터 베이스에 아이디가 중복으로 있는 경우 
			result = "0";
		}else {
			result = "1";
		}
		return result;
	}
	

	@RequestMapping("/getdata")
	public Object getdata() {//데이터를 JSON 형식으로 내려준다. 
		JSONArray ja = new JSONArray();
		Random r = new Random();
		int random= 0;
		//JSON : [{},{},{}] 의 형태
		for(int i=0; i<6; i++) {
			JSONObject jo = new JSONObject();
			jo.put("id", "id01"+i);
			random = r.nextInt(10) + 1;
			jo.put("name", "james" + i);
			jo.put("age", random);
			ja.add(jo);
		}
		return ja;
	}
	
	@RequestMapping("/getchart")
	public Object getchart() {
		JSONArray js = new JSONArray();
		Random r = new Random();
		
		
		for(int i=0; i<15; i++) {
			js.add(r.nextInt(50) + 1);
		}
		
		
		return js;
	}
	
	
	@RequestMapping("/getmarkers")
	public Object getmarkers(String loc) {
		JSONArray ja = new JSONArray();
		JSONObject jo1 = new JSONObject();
		JSONObject jo2 = new JSONObject();
		JSONObject jo3 = new JSONObject();
		
		
		if(loc.equals("seoul")) {
			jo1.put("content", "<div>서울1</div>");
			jo1.put("lat", 37.55041692365908);
			jo1.put("lng", 126.91037178013711);
			jo1.put("target", "js01");
			
			jo2.put("content", "<div>서울2</div>");
			jo2.put("lat", 37.56041692365908);
			jo2.put("lng", 126.92037178013711);
			jo2.put("target", "js02");
			
			jo3.put("content", "<div>서울3</div>");
			jo3.put("lat", 37.57041692365908);
			jo3.put("lng", 126.93037178013711);
			jo3.put("target", "js03");

			
		}else if(loc.equals("busan")) {
			jo1.put("content", "<div>부산1</div>");
			jo1.put("lat", 35.17642453774257);
			jo1.put("lng", 129.17669784099807);
			jo1.put("target", "js01");
			
			jo2.put("content", "<div>부산2</div>");
			jo2.put("lat", 35.18642453774257);
			jo2.put("lng", 129.18669784099807);
			jo2.put("target", "js02");
			
			jo3.put("content", "<div>부산3</div>");
			jo3.put("lat", 35.19642453774257);
			jo3.put("lng", 129.19669784099807);
			jo3.put("target", "js03");
			

			
		}else if(loc.equals("kwangju")) {
			jo1.put("content", "<div>광주1</div>");
			jo1.put("lat", 35.16173425533525);
			jo1.put("lng", 126.88758871719189);
			jo1.put("target", "js01");
			
			jo2.put("content", "<div>광주2</div>");
			jo2.put("lat", 35.17173425533525);
			jo2.put("lng",126.89758871719189);
			jo2.put("target", "js02");
			
			jo3.put("content", "<div>광주3</div>");
			jo3.put("lat", 35.18173425533525);
			jo3.put("lng", 126.90758871719189);
			jo3.put("target", "js03");
		}
		
		
		ja.add(jo1);
		ja.add(jo2);
		ja.add(jo3);
		
		
		for(int i=0; i < 5; i++) {
			
			
			
		}

		
		
		return ja;
	}
	
	
}
