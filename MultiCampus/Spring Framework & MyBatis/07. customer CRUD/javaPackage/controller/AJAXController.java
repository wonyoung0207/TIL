package com.multi.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@RestController
public class AJAXController {

	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/chartimpl")
	public Object chartimpl() {
		//{'cate':['p1','p2','p3','p4','p5'],'data':[10000,30000,20000,40000,15000]}
		JSONObject jo = new JSONObject();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		List<ProductVO> list = null;
		try {
			list = pbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (ProductVO p : list) {
			ja1.add(p.getName());
			ja2.add(p.getPrice());
		}
		jo.put("cate", ja1);
		jo.put("data", ja2);
		return jo;
	}
}






