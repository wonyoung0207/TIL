package com.multi.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@RestController// ajax 이용시 사용 
public class AjaxController {

	@Autowired
	ProductBiz pbiz;

	@RequestMapping("/chartimpl")
	public Object chartimpl() {
		JSONObject jo = new JSONObject();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		
		
		List<ProductVO> list = null;
		try {
			list = pbiz.getAll();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		for(ProductVO p : list) {
			ja1.add(p.getName());
			ja2.add(p.getPrice());
			
			
		}
		
		jo.put("cate", ja1);
		jo.put("data", ja2);
		

		return jo;
	}

}
