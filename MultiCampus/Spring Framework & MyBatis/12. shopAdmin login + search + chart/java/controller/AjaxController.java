package com.multi.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.biz.ProductBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;
import com.multi.vo.ProductAVGVO;

@RestController
public class AjaxController {
	
	
	@Autowired	
	CustBiz cbiz;
	
	@Autowired
	CateBiz cabiz;
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("cust_checkid")
	public String cust_checkid(String id) {
		String result = "";
		CustVO cust = null;
		
		if(id.equals("") || id == null) {
			return "1";
		}
		
		try {
			cust = cbiz.get(id);
			if(cust == null) {//id가 존재하지 않으면 0
				result = "0";
			}else {// id가 존재하면 1
				result = "1";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping("cate_checkid")
	public String cate_checkid(String id) {//int로 받으면 공백이 왔을때 에러남 
		String result = "";
		CateVO cate = null;
		
		if(id.equals("") || id == null) {
			return "1";
		}
		
		try {
			cate = cabiz.get(Integer.parseInt(id));
			if(cate == null) {//id가 존재하지 않으면 0
				result = "0";
			}else {// id가 존재하면 1
				result = "1";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	@RequestMapping("findpid")
	public String checkpid() {
		List<CateVO> list = null;
		JSONObject jo = new JSONObject();
		
		try {
			list = cabiz.get();
			jo.put("", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	@RequestMapping("chart")
	public Object chart() {
		//[{},{}] 의 형태로 만들어야 한다. 
		List<ProductAVGVO> list = null;
		JSONArray ja = new JSONArray();
		
		
		try {
			list = pbiz.getAvg();
			for(ProductAVGVO p : list) {
				JSONObject jo = new JSONObject();
				jo.put("name", p.getCatename());
				jo.put("y", p.getAvgprice());
				
				ja.add(jo);
			}

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ja;
	}
}
